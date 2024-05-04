package com.example.expensetracker.feature_showExpense.presentation.ui

import android.widget.Space
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expensetracker.common.topBar
import com.example.expensetracker.R
import com.example.expensetracker.feature_showExpense.presentation.ui.component.EmptyPlaceholder
import com.example.expensetracker.feature_showExpense.presentation.ui.component.Tabs
import com.example.expensetracker.feature_showExpense.presentation.ui.component.TabsButton
import com.example.expensetracker.ui.theme.expenseGradient
import com.example.expensetracker.ui.theme.incomeGradient
import com.example.expensetracker.utils.spacing
import kotlin.math.exp

@Composable
fun  ExpensesScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val currentTabButton by viewModel.tabButton.collectAsState()
    val dailyTransactions by viewModel.dailyTransaction.collectAsState()
//    val monthlyTransactions by viewModel.monthlyTransaction.collectAsState()

    val lazyListState = rememberLazyListState()

    Scaffold(
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
           Header( viewModel)
            //add tabs
            TabsButton()
            //daily expense
            AnimatedVisibility(
                visible = currentTabButton == Tabs.TODAY
            ) {
                dailyTransactions.ifEmpty {
                    EmptyPlaceholder()
                }

                LazyColumn(
                    state = lazyListState,
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background),
                    contentPadding = PaddingValues(
                        start = MaterialTheme.spacing.medium,
                        top = MaterialTheme.spacing.small,
                        end = MaterialTheme.spacing.medium
                    )
                    ){

                }
            }
        }
    }
}

@Composable
fun Header(homeViewModel: HomeViewModel){


    val totalIncome by homeViewModel.totalIncome.collectAsState()
    val totalExpense by homeViewModel.totalExpense.collectAsState()
    val currencyCode by homeViewModel.selectedCurrencyCode.collectAsState()


    val animatedBalance by animateFloatAsState(
        targetValue = totalIncome.toFloat() - totalExpense.toFloat(),
//        targetValue = 100.0f ,
        animationSpec = tween(durationMillis = 3000)
    )


    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ){
            Text(text = "")

            Text(text = homeViewModel.formattedDate.value)

            Icon(
                imageVector = Icons.Filled.Notifications,
                contentDescription = "")
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Account Balance")
        
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text =  "$animatedBalance".amountFormat(),
            style = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp
        )
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .height(70.dp)
                    .width(150.dp)
                    .clip(
                        RoundedCornerShape(20)
                    )
                    .background(
                        brush = incomeGradient
                    )
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ){

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)

                ) {
                    Column(
                        modifier = Modifier
                            .wrapContentHeight()
                            .width(40.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                color = Color.White
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            modifier= Modifier.size(10.dp),
                            painter = painterResource(id = R.drawable.arrow_downward),
                            contentDescription = "",
                        )
                        Icon(
                            modifier= Modifier.size(20.dp),
                            painter = painterResource(id = R.drawable.camera),
                            contentDescription = "",
                        )
                    }

                    Column(
                        modifier = Modifier.wrapContentSize()
                    ) {
                        Text(text = "income",
                        style = TextStyle(
                            fontSize = 12.sp,
                        )
                        )
                        Text(text = "$totalIncome")
                    }
                }
            }

            Box(
                modifier = Modifier
                    .height(70.dp)
                    .width(150.dp)
                    .clip(
                        RoundedCornerShape(20)
                    )
                    .background(
                        brush = expenseGradient
                    )
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ){
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .wrapContentHeight()
                            .width(40.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                color = Color.White
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            modifier= Modifier.size(10.dp),
                            painter = painterResource(id = R.drawable.arrow_upward),
                            contentDescription = "",
                        )
                        Icon(
                            modifier= Modifier.size(20.dp),
                            painter = painterResource(id = R.drawable.camera),
                            contentDescription = "",
                        )
                    }

                    Column(
                        modifier = Modifier.wrapContentSize()
                    ) {
                        Text(text = "Expense",
                            style = TextStyle(
                                fontSize = 12.sp,
                            )
                        )
                        Text(text = "$totalExpense")
                    }
                }

            }
        }
    }
}

@Preview
@Composable
fun Preview(){

}