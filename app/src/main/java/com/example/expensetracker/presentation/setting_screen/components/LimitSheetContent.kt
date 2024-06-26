package com.example.expensetracker.presentation.setting_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.expensetracker.R
import com.example.expensetracker.presentation.setting_screen.SettingViewModel
import com.example.expensetracker.presentation.setting_screen.service.LimitResetWorker
import com.example.expensetracker.utils.spacing
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalMaterial3Api::class)
@DelicateCoroutinesApi
@Composable
fun LimitContent(
//    modalBottomSheetState: ModalBottomSheetState,
    scope: CoroutineScope,
    settingViewModel: SettingViewModel = hiltViewModel()
) {
    val MILLISECS = 86_400_000L
    val limitDuration = listOf(1 * MILLISECS, 7 * MILLISECS, 30 * MILLISECS)
    val limitDurationText by remember {
        mutableStateOf(
            listOf("Daily", "Weekly", "Monthly")
        )
    }
    var selectedIndex by remember { mutableStateOf(0) }
    val expenseLimitAmount by settingViewModel.expenseLimit.collectAsState()
    val expenseLimitDuration by settingViewModel.expenseLimitDuration.collectAsState()
    var selectedLimit by remember { mutableStateOf(limitDurationText[expenseLimitDuration]) }
    var isAmountEmpty by remember { mutableStateOf(false) }
    var limitTextFieldValue by remember { mutableStateOf(TextFieldValue(String())) }
    var expandedState by remember { mutableStateOf(false) }
    var size by remember { mutableStateOf(Size.Zero) }

    val context = LocalContext.current
    var resetWorkRequest = PeriodicWorkRequestBuilder<LimitResetWorker>(
        limitDuration.first(),
        TimeUnit.MILLISECONDS,
        (limitDuration.first() * 0.95).toLong(),
        TimeUnit.MILLISECONDS
    ).build()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.medium)
    ) {
        Text(
            text = "SET LIMIT",
            style = MaterialTheme.typography.titleMedium
        )

        TextField(
            value = limitTextFieldValue,
            onValueChange = { field ->
                isAmountEmpty = false
                limitTextFieldValue = field
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = MaterialTheme.spacing.small,
                    bottom = MaterialTheme.spacing.medium
                ),
            maxLines = 1,
            singleLine = true,
            placeholder = {
                Text(
                    text = if (expenseLimitAmount == 0.0) "Amount" else expenseLimitAmount.toString(),
                    style = MaterialTheme.typography.titleMedium
                )
            },
            isError = isAmountEmpty,
            textStyle = MaterialTheme.typography.titleMedium,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                cursorColor = MaterialTheme.colorScheme.primary,
                containerColor = Color.LightGray
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    expandedState = !expandedState
                }
                .background(Color.LightGray)
                .padding(MaterialTheme.spacing.medium)
                .onGloballyPositioned {
                    size = it.size.toSize()
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = selectedLimit,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f)
            )

            Icon(
                painter = painterResource(R.drawable.pop_up),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface
            )

            DropdownMenu(
                expanded = expandedState,
                onDismissRequest = { expandedState = false },
                modifier = Modifier.width(
                    with(LocalDensity.current) {
                        size.width.toDp()
                    }
                )
            ) {
//                limitDurationText.forEachIndexed { index, label ->
//                    DropdownMenuItem(onClick = {
//                        selectedLimit = label
//                        expandedState = false
//                        selectedIndex = index
//                        resetWorkRequest = PeriodicWorkRequestBuilder<LimitResetWorker>(
//                            limitDuration[index],
//                            TimeUnit.MILLISECONDS,
//                            (limitDuration[index] * 0.95).toLong(),
//                            TimeUnit.MILLISECONDS
//                        ).build()
//                    }) {
//                        Text(
//                            text = label,
//                            style = MaterialTheme.typography.titleMedium,
//                            color = Color.Gray
//                        )
//                    }
//                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        TextButton(
            onClick = {
//                scope.launch { modalBottomSheetState.hide() }
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
                style = MaterialTheme.typography.titleMedium
            )
        }

        TextButton(
            onClick = {
                scope.launch {
                    val amount = limitTextFieldValue.text
                    if (amount.isBlank())
                        isAmountEmpty = true
                    else {
                        isAmountEmpty = false
                        settingViewModel.editExpenseLimit(limitTextFieldValue.text.toDouble())
//                        modalBottomSheetState.hide()
                        settingViewModel.editLimitDuration(selectedIndex)
                        val workManager = WorkManager.getInstance(context)
                        workManager.enqueueUniquePeriodicWork(
                            "RESET",
                            ExistingPeriodicWorkPolicy.REPLACE,
                            resetWorkRequest
                        )
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = MaterialTheme.spacing.medium),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = contentColorFor(backgroundColor = MaterialTheme.colorScheme.primary)
            ),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            Text(
                text = "SET",
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}