package com.example.expensetracker.presentation.setting_screen.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expensetracker.presentation.setting_screen.SettingViewModel
import com.example.expensetracker.ui.theme.Red500
import com.example.expensetracker.utils.spacing
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun EraseContent(
//    modalBottomSheetState: ModalBottomSheetState,
    scope: CoroutineScope,
    settingViewModel: SettingViewModel = hiltViewModel()
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.medium)
    ) {
        val context = LocalContext.current
        Text(
            text = "ERASE DATA",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "You're about to erase all transactions on this app. This cannot be reversed",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = Color.DarkGray.copy(alpha = 0.5f),
            modifier = Modifier.padding(top = MaterialTheme.spacing.medium)
        )

        TextButton(
            onClick = {
                scope.launch {
//                    modalBottomSheetState.hide()
                }
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.LightGray.copy(alpha = 0.4f),
                contentColor = Color.Black
            ),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            Text(
                text = "CANCEL",
                style = MaterialTheme.typography.titleLarge
            )
        }

        TextButton(
            onClick = {
                scope.launch {
                    settingViewModel.eraseTransaction()
//                    modalBottomSheetState.hide()
                    Toast.makeText(context, "Erased!", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Red500,
                contentColor = Color.White
            ),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            Text(
                text = "CONTINUE",
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}