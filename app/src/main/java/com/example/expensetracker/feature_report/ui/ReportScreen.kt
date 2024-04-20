package com.example.expensetracker.feature_report.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.dp
import com.example.expensetracker.common.topBar
import com.example.expensetracker.ui.theme.*
import kotlin.math.roundToInt


@Composable
fun ReportScreen() {

    Scaffold(
        topBar = {
            topBar(title = "Reports")
        }
    ) {
        var showDescription by remember {
            mutableStateOf(false)
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp),
            contentAlignment = Alignment.TopCenter
        ){

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                BarChart(
                    listOf(
                        BarchartInput(28,"Kotlin", Purple200),
                        BarchartInput(15,"Swift", Purple700),
                        BarchartInput(11,"Ruby", Teal200),
                        BarchartInput(7,"Cobol", TextPrimary),
                        BarchartInput(14,"C++", Primary),
                        BarchartInput(9,"C", LabelSecondary),
                        BarchartInput(21,"Python", Destructive)
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                    showDescription = showDescription
                )
            }
        }
    }
}

@Composable
fun BarChart(
    inputList: List<BarchartInput>,
    modifier: Modifier = Modifier,
    showDescription: Boolean,
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val listSum by remember {
            mutableStateOf(inputList.sumOf { it.value })
        }

        inputList.forEach {input ->

            val percentage = input.value/listSum.toFloat()
            Bar(
                modifier = Modifier
                    .height(120.dp * percentage * inputList.size)
                    .width(40.dp),
                primaryColor = input.color,
                percentage = percentage,
                description = input.description ,
                showDescription = showDescription
            )
        }
    }
}

@Composable
fun Bar(
    modifier: Modifier = Modifier,
    primaryColor: Color,
    percentage: Float,
    description: String,
    showDescription: Boolean
){
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Canvas(
            modifier = Modifier.fillMaxSize()
        ){
            val width = size.width
            val height = size.height

            val barWidth = width/ 5*3
            val barHeight = height / 8* 7

            val barHeight3DPart =  height - barHeight
            val barWidth3DPart  = (width - barWidth) * (height * 0.002f)

            var path = Path().apply {
                moveTo(0f, height)
                lineTo(barWidth,height)
                lineTo(barWidth,height-barHeight)
                lineTo(0f,height-barHeight)
                close()
            }

            // Draw rectangle around the complete bar chart
//            drawRect(
//                color = Color.White,
//                topLeft = Offset(0f, 0f),
//                size = Size(width, height),
//                style = Stroke(width = 2f)
//            )

            drawPath(
                path,
                brush = Brush.linearGradient(
                    colors = listOf(Color.White , Color.LightGray)
                )
            )

            path = Path().apply {
                moveTo(barWidth , height-barHeight)
                lineTo(barWidth3DPart+barWidth,0f)
                lineTo(barWidth3DPart+barWidth ,  barHeight)
                lineTo(barWidth , height)
                close()
            }
            drawPath(
                path,
                brush = Brush.linearGradient(
                    colors = listOf(Color.White , Color.LightGray)
                )
            )

            path = Path().apply {
                moveTo(0f, barHeight3DPart)
                lineTo(barWidth , barWidth3DPart)
                lineTo(barWidth+ barWidth3DPart , 0f)
                lineTo(barWidth3DPart , 0f)
                close()
            }

            drawPath(
                path,
                brush = Brush.linearGradient(
                    colors = listOf(Color.White , Color.LightGray)
                )
            )

            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    "${(percentage * 100).roundToInt()} %",
                    barWidth / 5f,
                    height + 55f,
                    android.graphics.Paint().apply {
                        color= Color.White.toArgb()
                        textSize = 11.dp.toPx()
                        isFakeBoldText = true
                    }
                )
            }

        }
    }

}
data class BarchartInput(
    val value :Int,
    val description : String,
    val color: Color
)