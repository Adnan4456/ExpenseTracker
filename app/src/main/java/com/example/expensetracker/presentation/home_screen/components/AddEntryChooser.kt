package com.example.expensetracker.presentation.home_screen.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.expensetracker.R
import com.example.expensetracker.presentation.navigation.Screen
import com.example.expensetracker.ui.theme.expenseGradient
import com.example.expensetracker.ui.theme.incomeGradient
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalUnitApi
@Composable
fun AddEntryChooser(
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    navController: NavHostController
) {

    val scope = rememberCoroutineScope()
//    val progress = bottomSheetScaffoldState.bottomSheetState.progress.fraction
//    val progress = bottomSheetScaffoldState.bottomSheetState.currentValue.
//    val expandRotation by animateFloatAsState(
//        targetValue = -360f * progress,
//        animationSpec = spring(dampingRatio = 0.75f, stiffness = Spring.StiffnessLow)
//    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(
                onClick = {
                    scope.launch { bottomSheetScaffoldState.bottomSheetState.hide() }
                    navController.navigate("${Screen.TransactionScreen.route}/0")
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.income),
                    contentDescription = "add entry",
                    tint = MaterialTheme.colorScheme.surface,
                    modifier = Modifier
                        .size(48.dp, 48.dp)
//                        .rotate(expandRotation)
                        .background(
                            incomeGradient,
                            CircleShape
                        )
                        .padding(8.dp)
                )
            }

            Text(
                text = "ADD INCOME",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 4.dp),
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(
                onClick = {
                    scope.launch { bottomSheetScaffoldState.bottomSheetState.hide() }
                    navController.navigate("${Screen.TransactionScreen.route}/1")
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.expense),
                    contentDescription = "expense",
                    tint = MaterialTheme.colorScheme.surface,
                    modifier = Modifier
                        .size(48.dp, 48.dp)
//                        .rotate(expandRotation)
                        .background(
                            expenseGradient,
                            CircleShape
                        )
                        .padding(8.dp)
                )
            }

            Text(
                text = "ADD EXPENSE",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 4.dp),
                color = MaterialTheme.colorScheme.onSurface
            )
        }

    }
}