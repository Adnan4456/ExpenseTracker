package com.example.expensetracker.pdf.data

// data/util/PdfGenerator.kt
import android.content.Context
import android.graphics.pdf.PdfDocument
import android.net.Uri
import androidx.core.content.FileProvider
import com.example.expensetracker.data.local.entity.AccountDto
import com.example.expensetracker.data.local.entity.TranscationDto
import com.itextpdf.kernel.colors.ColorConstants
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.properties.TextAlignment
import com.itextpdf.layout.properties.UnitValue
import com.itextpdf.layout.*
import com.itextpdf.layout.element.Table

import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PdfGenerator(private val context: Context) {

    private fun createPdfFile(fileName: String): File {
        val folder = context.getExternalFilesDir("reports")?.apply { mkdirs() }
            ?: context.filesDir.apply { mkdirs() }
        return File(folder, fileName)
    }

    fun generateTransactionsReport(transactions: List<TranscationDto>): Uri {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val fileName = "Transactions_Report_$timeStamp.pdf"
        val file = createPdfFile(fileName)

        PdfWriter(file).use { writer ->
            com.itextpdf.kernel.pdf.PdfDocument(writer).use { pdfDocument ->
                Document(pdfDocument).use { document ->
                    // Title
                    document.add(
                        Paragraph("TRANSACTIONS REPORT")
                            .setTextAlignment(TextAlignment.CENTER)
                            .setFontSize(18f)
                            .setBold()
                    )

                    // Generation date
                    document.add(
                        Paragraph("Generated on: ${SimpleDateFormat("MMMM dd, yyyy").format(Date())}")
                            .setTextAlignment(TextAlignment.CENTER)
                            .setFontSize(10f)
                    )
                    document.add(Paragraph("\n"))

                    // Transactions table
                    val table = Table(UnitValue.createPercentArray(floatArrayOf(10f, 15f, 10f, 15f, 10f, 15f, 15f)))
                        .useAllAvailableWidth()

                    // Table headers
                    table.addHeaderCell(createHeaderCell("Date"))
                    table.addHeaderCell(createHeaderCell("Entry Date"))
                    table.addHeaderCell(createHeaderCell("Amount"))
                    table.addHeaderCell(createHeaderCell("Category"))
                    table.addHeaderCell(createHeaderCell("Account"))
                    table.addHeaderCell(createHeaderCell("Type"))
                    table.addHeaderCell(createHeaderCell("Title"))

                    // Table data
                    val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
                    transactions.forEach { transaction ->
                        table.addCell(Paragraph(dateFormat.format(transaction.date)))
                        table.addCell(Paragraph(transaction.dateOfEntry))
                        table.addCell(Paragraph(transaction.amount.toString()))
                        table.addCell(Paragraph(transaction.category))
                        table.addCell(Paragraph(transaction.account))
                        table.addCell(Paragraph(transaction.transcationType))
                        table.addCell(Paragraph(transaction.title))
                    }

                    document.add(table)

                    // Summary
                    val totalIncome = transactions.filter { it.transcationType == "Income" }.sumOf { it.amount }
                    val totalExpense = transactions.filter { it.transcationType == "Expense" }.sumOf { it.amount }

                    document.add(Paragraph("\n"))
                    document.add(
                        Paragraph("TOTAL INCOME: $totalIncome")
                            .setTextAlignment(TextAlignment.RIGHT)
                    )
                    document.add(
                        Paragraph("TOTAL EXPENSE: $totalExpense")
                            .setTextAlignment(TextAlignment.RIGHT)
                    )
                    document.add(
                        Paragraph("NET BALANCE: ${totalIncome - totalExpense}")
                            .setTextAlignment(TextAlignment.RIGHT)
                            .setBold()
                    )
                }
            }
        }

        return FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            file
        )
    }

    fun generateAccountsReport(accounts: List<AccountDto>): Uri {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val fileName = "Accounts_Report_$timeStamp.pdf"
        val file = createPdfFile(fileName)

        PdfWriter(file).use { writer ->
            com.itextpdf.kernel.pdf.PdfDocument(writer).use { pdfDocument ->
                Document(pdfDocument).use { document ->
                    // Title
                    document.add(
                        Paragraph("ACCOUNTS SUMMARY")
                            .setTextAlignment(TextAlignment.CENTER)
                            .setFontSize(18f)
                            .setBold()
                    )

                    document.add(Paragraph("\n"))

                    // Accounts table
                    val table = Table(UnitValue.createPercentArray(floatArrayOf(20f, 15f, 15f, 15f, 15f)))
                        .useAllAvailableWidth()

                    // Table headers
                    table.addHeaderCell(createHeaderCell("Account Type"))
                    table.addHeaderCell(createHeaderCell("Balance"))
                    table.addHeaderCell(createHeaderCell("Income"))
                    table.addHeaderCell(createHeaderCell("Expense"))
                    table.addHeaderCell(createHeaderCell("Net"))

                    // Table data
                    accounts.forEach { account ->
                        table.addCell(Paragraph(account.accountType))
                        table.addCell(Paragraph(account.balance.toString()))
                        table.addCell(Paragraph(account.income.toString()))
                        table.addCell(Paragraph(account.expense.toString()))
                        table.addCell(Paragraph((account.income - account.expense).toString()))
                    }

                    document.add(table)

                    // Summary
                    document.add(Paragraph("\n"))
                    document.add(
                        Paragraph("TOTAL BALANCE: ${accounts.sumOf { it.balance }}")
                            .setTextAlignment(TextAlignment.RIGHT)
                            .setBold()
                    )
                }
            }
        }

        return FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            file
        )
    }

    private fun createHeaderCell(text: String): Paragraph {
        return Paragraph(text)
            .setBold()
            .setBackgroundColor(ColorConstants.LIGHT_GRAY)
    }
}