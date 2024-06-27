package com.example.expensetracker.presentation.setting_screen.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.expensetracker.R
import com.example.expensetracker.ui.theme.Red500
import com.example.expensetracker.utils.spacing
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun EraseSetting(
//    modalBottomSheetState: ModalBottomSheetState,
    scope: CoroutineScope, onItemClick: (Int) -> Unit) {

    Text(text = "EraseSetting  screen ",)

//    TextButton(
//        onClick = {
//            scope.launch {
//                onItemClick(2)
//                modalBottomSheetState.show()
//            }
//        },
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(
//                horizontal = MaterialTheme.spacing.medium,
//                vertical = MaterialTheme.spacing.small
//            ),
//        colors = ButtonDefaults.buttonColors(
//            containerColor = Red500,
//            contentColor = MaterialTheme.colorScheme.surface
//        ),
//        shape = RoundedCornerShape(12.dp),
//        contentPadding = PaddingValues(
//            horizontal = MaterialTheme.spacing.medium,
//            vertical = 20.dp
//        )
//    ) {
//        Text(
//            text = "Erase Data",
//            style = MaterialTheme.typography.titleLarge,
//            modifier = Modifier.weight(2f),
//            textAlign = TextAlign.Start
//        )
//
//        CompositionLocalProvider(
////            LocalContentAlpha provides ContentAlpha.high
//        ) {
//            Icon(
//                painter = painterResource(R.drawable.edit),
//                contentDescription = null,
//                modifier = Modifier.then(Modifier.size(16.dp))
//            )
//        }
//    }
}