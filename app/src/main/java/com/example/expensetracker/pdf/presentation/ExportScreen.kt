package com.example.expensetracker.pdf.presentation

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExportScreen(
    viewModel: ExportViewModel = hiltViewModel(),
    navController: NavHostController,
    onBack: () -> Unit,

) {
    val context = LocalContext.current
    val exportState by viewModel.exportState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Export Reports") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = { viewModel.exportTransactions() },
                modifier = Modifier.fillMaxWidth(),
                enabled = exportState != ExportViewModel.ExportState.Loading
            ) {
                Text("Export Transactions")
            }

            Button(
                onClick = { viewModel.exportAccounts() },
                modifier = Modifier.fillMaxWidth(),
                enabled = exportState != ExportViewModel.ExportState.Loading
            ) {
                Text("Export Accounts")
            }

            when (val state = exportState) {
                ExportViewModel.ExportState.Loading -> {
                    CircularProgressIndicator()
                    Text("Generating PDF...")
                }
                is ExportViewModel.ExportState.Success -> {
                    LaunchedEffect(state) {
                        // Show success message
                        Toast.makeText(
                            context,
                            "PDF exported successfully",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    Text("Export completed successfully")
                    Button(
                        onClick = {
                            // Share the PDF
                            val shareIntent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_STREAM, state.fileUri)
                                type = "application/pdf"
                                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                            }
                            context.startActivity(
                                Intent.createChooser(
                                    shareIntent,
                                    "Share PDF Report"
                                )
                            )
                        }
                    ) {
                        Text("Share PDF")
                    }
                }
                is ExportViewModel.ExportState.Error -> {
                    Text("Error: ${state.message}", color = MaterialTheme.colorScheme.error)
                }
                ExportViewModel.ExportState.Idle -> {
                    // Initial state - nothing to show
                }
            }
        }
    }
}