package com.example.expensetracker.presentation.home_screen.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expensetracker.R
import com.example.expensetracker.presentation.home_screen.Account
import com.example.expensetracker.presentation.home_screen.HomeViewModel
import com.example.expensetracker.utils.spacing


@Composable
fun AccountTag(
    account: Account,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val selectedAccount by homeViewModel.account.collectAsState()
    val isSelected = selectedAccount == account

    TextButton(
        onClick = {
            homeViewModel.selectAccount(account)
        },
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(
            horizontal = MaterialTheme.spacing.medium,
            vertical = MaterialTheme.spacing.small
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected)
                MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
            else Color.Transparent,
            contentColor = if (isSelected)
                Color.White else
                MaterialTheme.colorScheme.primary
        ),
    ) {
        Icon(
            painter = painterResource(
                id = if (isSelected)
                    R.drawable.checked else account.iconRes
            ),
            contentDescription = account.title,
        )
        Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
        Text(
            text = account.title,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold,
            letterSpacing = TextUnit(1.1f, TextUnitType.Sp)
        )
    }
}
@ExperimentalUnitApi
@Preview(showBackground = true)
@Composable
fun AccountTagPreview() {
    AccountTag(account = Account.CARD)
}