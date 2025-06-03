package com.example.expensetracker.di

import android.content.Context
import com.example.expensetracker.data.TranscationDao
import com.example.expensetracker.pdf.data.PdfGenerator
import com.example.expensetracker.pdf.data.repository.ExportRepositoryImpl
import com.example.expensetracker.pdf.domain.ExportRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PDFModule {


    @Provides
    @Singleton
    fun providePdfGenerator(@ApplicationContext context: Context): PdfGenerator {
        return PdfGenerator(context)
    }

    @Provides
    @Singleton
    fun provideExportRepository(
        @ApplicationContext context: Context,
        transactionDao: TranscationDao,
        pdfGenerator: PdfGenerator
    ): ExportRepository {
        return ExportRepositoryImpl(context, transactionDao, pdfGenerator)
    }
}