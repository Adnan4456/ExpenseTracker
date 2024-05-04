package com.example.expensetracker.feature_showExpense.presentation.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expensetracker.feature_showExpense.presentation.ui.HomeViewModel
import com.example.expensetracker.ui.theme.customColor1ContainerDarkMediumContrast
import com.example.expensetracker.ui.theme.onCustomColor1ContainerDark
import com.example.expensetracker.utils.spacing


@Composable
fun TabsButton(
    tabs: Array<Tabs> = Tabs.values(),
    cornerRadius: Dp = 24.dp,
    onButtonClick: () -> Unit = { },
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val selectedTab by homeViewModel.tabButton.collectAsState()


    Surface(
            modifier = Modifier.padding(
                16.dp
            ),
        color = Color.DarkGray.copy(alpha = 0.1f),
        shape = RoundedCornerShape(cornerRadius)
    ) {
        Row {
            tabs.forEach {tab ->

                val backgroundColor by animateColorAsState(
                    targetValue =  if (selectedTab == tab)onCustomColor1ContainerDark
                    else Color.Transparent,
                    animationSpec = tween(durationMillis = 500, easing = LinearOutSlowInEasing)
                )

                val textColor by animateColorAsState(
                    if (selectedTab == tab)customColor1ContainerDarkMediumContrast
                    else MaterialTheme.colorScheme.onSurface,
                    animationSpec = tween(durationMillis = 500, easing = LinearOutSlowInEasing)
                )

                TextButton(
                    onClick = {
                        homeViewModel.selectTab(tab)
                        onButtonClick()
                    },
                    modifier = Modifier
                        .padding(vertical = MaterialTheme.spacing.extraSmall)
                        .weight(1f),
                    shape = RoundedCornerShape(cornerRadius),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = backgroundColor,
                        contentColor = textColor
                    )
                    ) {
                    Text(
                        text = tab.title,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier
                            .padding(
                                horizontal = MaterialTheme.spacing.small,
                                vertical = MaterialTheme.spacing.extraSmall
                            )
                            .align(Alignment.CenterVertically)
                    )
                }
            }
        }
    }
}