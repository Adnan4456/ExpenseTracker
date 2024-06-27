package com.example.expensetracker.presentation.home_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expensetracker.R
import com.example.expensetracker.presentation.home_screen.Category
import com.example.expensetracker.presentation.home_screen.HomeViewModel
import com.example.expensetracker.utils.spacing

@OptIn(ExperimentalLayoutApi::class)
@ExperimentalUnitApi
@Composable
fun Category(
    expenseItems: Array<Category> = Category.values()
) {
    FlowRow(

        modifier = Modifier.padding(
            start = MaterialTheme.spacing.medium,
            top = MaterialTheme.spacing.medium,
            bottom = MaterialTheme.spacing.medium,
        ),
    ) {
        expenseItems.forEach {
            CategoryTag(category = it)
        }
    }
}

@ExperimentalUnitApi
@Composable
fun CategoryTag(category: Category, homeViewModel: HomeViewModel = hiltViewModel()) {
    val selected by homeViewModel.category.collectAsState()
    var isSelected = selected.title == category.title
    TextButton(
        modifier = Modifier.padding(end = MaterialTheme.spacing.small),
        onClick = {
            homeViewModel.selectCategory(category)
            isSelected = selected.title == category.title
        },
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(
            horizontal = MaterialTheme.spacing.medium,
            vertical = MaterialTheme.spacing.small
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) {
                category.bgRes.copy(alpha = 0.95f)
            } else MaterialTheme.colorScheme.surface, contentColor = if (isSelected) {
                category.colorRes
            } else MaterialTheme.colorScheme.onSurface
        ),
    ) {
        Icon(
            painter = if (!isSelected) {
                painterResource(id = R.drawable.add_cat)
            } else painterResource(id = category.iconRes),
            contentDescription = category.title,
        )
        Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
        Text(
            text = category.title,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@ExperimentalUnitApi
@Preview(showBackground = true)
@Composable
fun CategoryPreview() {
    Category()
}