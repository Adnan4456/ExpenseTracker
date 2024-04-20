package com.example.expensetracker.feature_addexpenses.ui


import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.expensetracker.common.topBar
import com.example.expensetracker.ui.theme.*
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExpenseScreen() {

    var amount by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    val keyboard = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    val showKeyboard = remember { mutableStateOf(true) }

    val datePickerState = rememberDatePickerState(
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return utcTimeMillis > System.currentTimeMillis()
            }
        }
    )

    var showDatePickerDialog by remember{
        mutableStateOf(false)
    }

    var dateInString  = "Select Date"

    LaunchedEffect(focusRequester) {
        if (showKeyboard.equals(true)) {
            focusRequester.requestFocus()
            delay(100)
            keyboard?.show()
        }else
        {
            focusRequester.freeFocus()
            keyboard?.hide()
        }
    }
    Scaffold(
        topBar = {
            topBar(title = "Add")
        }
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            ElevatedCard(
                modifier = Modifier
                    .padding(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = BackgroundElevated
                ) ,
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp,
                    pressedElevation = 20.dp
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.Start
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Amount",
                            style = Typography.bodyLarge,
                            color = TextPrimary)

                        TextField(
                            modifier = Modifier
                                .padding(3.dp)
                                .defaultMinSize(minWidth = 80.dp, minHeight = 44.dp)
                                .wrapContentSize(align = Alignment.Center)
                                .focusRequester(focusRequester),
                            value = amount,
                            onValueChange = {
                                amount = it
                        },
                            singleLine = true,
                            maxLines = 1,
                            colors = TextFieldDefaults.colors(
                                cursorColor = Primary,
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor =  Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledContainerColor = Color.Transparent
                            ),
                            textStyle  = TextStyle(
                                color = TextPrimary,
                                textAlign = TextAlign.End
                            ),
                            keyboardOptions = KeyboardOptions(
                                autoCorrect = false,
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next
                            )
                        )
                    }


                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 1.dp,
                        color = Color.White.copy(.1f)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = "Recurrance" ,
                            style = Typography.bodyMedium,
                            color = TextPrimary
                        )

                        var recurranceMenuOpened by remember {
                            mutableStateOf(false)
                        }
                        var selectedMenuOption by remember {
                            mutableStateOf("None")
                        }

                        TextButton(
                            onClick = {
                            recurranceMenuOpened = true
                        }) {

                            DropdownMenu(
                                expanded = recurranceMenuOpened,
                                onDismissRequest = { recurranceMenuOpened = false }) {

                                DropdownMenuItem(
                                    leadingIcon = {
                                        if(selectedMenuOption == "None")    {
                                            Icon(imageVector =Icons.Filled.Check ,
                                                contentDescription = "",
                                                tint = Color.White
                                            )
                                        }
                                    },
                                    text = { Text(text = "None") },
                                    onClick = {
                                        Toast.makeText(context , "Please select option", Toast.LENGTH_SHORT).show()
                                    })

                                DropdownMenuItem(
                                    leadingIcon = {
                                        if(selectedMenuOption == "Daily")    {
                                            Icon(imageVector =Icons.Filled.Check ,
                                                contentDescription = "",
                                                tint = Color.White)
                                        }
                                    },
                                    text = { Text(text = "Daily") },
                                    onClick = {
                                        selectedMenuOption= "Daily"
                                        recurranceMenuOpened = false
                                    })
                                DropdownMenuItem(
                                    leadingIcon = {
                                        if(selectedMenuOption == "Weekly")    {
                                            Icon(imageVector =Icons.Filled.Check ,
                                                contentDescription = "",
                                                tint = Color.White)
                                        }
                                    },
                                    text = { Text(text = "Weekly") },
                                    onClick = {
                                        selectedMenuOption= "Weekly"
                                        recurranceMenuOpened = false
                                    })
                                DropdownMenuItem(
                                    leadingIcon = {
                                        if(selectedMenuOption == "Monthly")    {
                                            Icon(imageVector =Icons.Filled.Check ,
                                                contentDescription = "",
                                                tint = Color.White)
                                        }
                                    },
                                    text = { Text(text = "Monthly") },
                                    onClick = {
                                        selectedMenuOption= "Monthly"
                                        recurranceMenuOpened = false
                                    })
                            }
                            Text(text = selectedMenuOption)
                        }
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 1.dp,
                        color = Color.White.copy(.1f)
                    )

                    Spacer(modifier = Modifier.height(15.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                //open Date dialog
                                showDatePickerDialog = true
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = "Date" ,
                            style = Typography.bodyMedium,
                            color = TextPrimary
                        )

                        Text(text = dateInString)
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 1.dp,
                        color = Color.White.copy(.1f)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = "Note" ,
                            style = Typography.bodyMedium,
                            color = TextPrimary
                        )

                        TextField(
                            modifier = Modifier
                                .padding(3.dp)
                                .defaultMinSize(minWidth = 80.dp, minHeight = 44.dp)
                                .wrapContentSize(align = Alignment.Center)
                                .focusRequester(focusRequester),
                            value = "",
                            onValueChange ={

                            } ,
                            singleLine = true,
                            maxLines = 1,
                            colors = TextFieldDefaults.colors(
                                cursorColor = Primary,
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor =  Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledContainerColor = Color.Transparent
                            ),
                            textStyle  = TextStyle(
                                color = TextPrimary,
                                textAlign = TextAlign.End
                            ),
                            keyboardOptions = KeyboardOptions(
                                autoCorrect = false,
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Next
                            )

                        )
                    }

                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 1.dp,
                        color = Color.White.copy(.1f)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        var categoryMenuOpened by remember {
                            mutableStateOf(false)
                        }
                        var selectedMenuOption by remember {
                            mutableStateOf("None")
                        }


                        Text(
                            text = "Category" ,
                            style = Typography.bodyMedium,
                            color = TextPrimary
                        )

                        TextButton(
                            onClick = {
                                categoryMenuOpened = true
                            }) {

                            DropdownMenu(
                                expanded = categoryMenuOpened,
                                onDismissRequest = { categoryMenuOpened = false }) {

                                DropdownMenuItem(
                                    leadingIcon = {
                                        if(selectedMenuOption == "None")    {
                                            Icon(imageVector =Icons.Filled.Check ,
                                                contentDescription = "",
                                                tint = Color.White
                                            )
                                        }
                                    },
                                    text = { Text(text = "None") },
                                    onClick = {
                                        Toast.makeText(context , "Please select option", Toast.LENGTH_SHORT).show()
                                    })

                                DropdownMenuItem(
                                    leadingIcon = {
                                        if(selectedMenuOption == "Hobbies")    {
                                            Icon(imageVector =Icons.Filled.Check ,
                                                contentDescription = "",
                                                tint = Color.White)
                                        }
                                    },
                                    text = { Text(text = "Hobbies") },
                                    onClick = {
                                        selectedMenuOption= "Hobbies"
                                        categoryMenuOpened = false
                                    })
                                DropdownMenuItem(
                                    leadingIcon = {
                                        if(selectedMenuOption == "Family")    {
                                            Icon(imageVector =Icons.Filled.Check ,
                                                contentDescription = "",
                                                tint = Color.White)
                                        }
                                    },
                                    text = { Text(text = "Family") },
                                    onClick = {
                                        selectedMenuOption= "Family"
                                        categoryMenuOpened = false
                                    })
                                DropdownMenuItem(
                                    leadingIcon = {
                                        if(selectedMenuOption == "Bills")    {
                                            Icon(imageVector =Icons.Filled.Check ,
                                                contentDescription = "",
                                                tint = Color.White)
                                        }
                                    },
                                    text = { Text(text = "Bills") },
                                    onClick = {
                                        selectedMenuOption= "Bills"
                                        categoryMenuOpened = false
                                    })
                            }
                            Text(text = selectedMenuOption)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                modifier = Modifier.padding(16.dp),
                shape = Shapes.large,
                onClick = { }) {
                Text(text = "Add Expense")
            }


            if(showDatePickerDialog){
                DatePickerDialog(
                    onDismissRequest = {

                    }, confirmButton = {
                        TextButton(onClick = {
                            showDatePickerDialog = false

                            dateInString = convertLongToTime(datePickerState.selectedDateMillis!!)

                        }) {
                            Text(text = "Confirm")
                        }

                }) {
                    DatePicker(
                        state = datePickerState,
                        showModeToggle = true
                    )
                }
            }
        }
    }
}

@SuppressLint("SimpleDateFormat")
fun convertLongToTime(
    time: Long
):String{
    val date = Date(time)
    val format = SimpleDateFormat("dd/MM/yyyy")
    return format.format(date)
}