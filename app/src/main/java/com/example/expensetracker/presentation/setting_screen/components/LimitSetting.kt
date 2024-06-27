package com.example.expensetracker.presentation.setting_screen.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expensetracker.presentation.home_screen.HomeViewModel
import com.example.expensetracker.presentation.home_screen.amountFormat
import com.example.expensetracker.presentation.setting_screen.SettingViewModel
import com.example.expensetracker.utils.spacing
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun LimitSetting(
//    modalBottomSheetState: ModalBottomSheetState,
    scope: CoroutineScope,
    homeViewModel: HomeViewModel = hiltViewModel(),
    settingViewModel: SettingViewModel = hiltViewModel(),
    onItemClick: (Int) -> Unit
) {
    val currencyCode by settingViewModel.currency.collectAsState()
    val expenseLimit by settingViewModel.expenseLimit.collectAsState()

    TextButton(
        onClick = {
            onItemClick(1)
            scope.launch {
//                modalBottomSheetState.show()
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = MaterialTheme.spacing.medium,
                vertical = MaterialTheme.spacing.small
            ),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.DarkGray.copy(alpha = 0.1f),
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(
            horizontal = MaterialTheme.spacing.medium,
            vertical = 20.dp
        )
    ) {
        Text(
            text = "Expense Limit",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.weight(2f),
            textAlign = TextAlign.Start
        )

        CompositionLocalProvider(
//            LocalContentAlpha provides ContentAlpha.medium
        ) {
            Text(
                text = "$currencyCode " + expenseLimit.toString().amountFormat(),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}