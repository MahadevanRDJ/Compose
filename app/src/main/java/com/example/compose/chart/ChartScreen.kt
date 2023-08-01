package com.example.compose.chart

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.example.compose.Screen
import com.example.compose.data.dto.Country
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChartScreen(barData: BarData, chartData: List<Country>, navController: NavHostController) {
    Surface {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Chart") },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigate(Screen.CountryList.route)}) {
                            Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Back")
                        }
                    },
                )
            },
        ) {
            AndroidView(modifier = Modifier.fillMaxSize(), factory = { context ->
                val barChart = BarChart(context)
                barChart.apply {
                    initBarChart(barChart, chartData)
                    barChart.data = barData
                    xAxis.textColor = Color.GRAY
                    axisLeft.textColor = Color.GRAY
                    barChart.invalidate()
                }
            })
        }
    }
}

private fun initBarChart(barChart: BarChart, chartData: List<Country>) {

    barChart.setDrawGridBackground(false)

    barChart.setDrawBarShadow(false)

    barChart.setDrawBorders(false)

    barChart.animateY(1000)

    barChart.animateX(1000)

    var description = barChart.description
    description.isEnabled = false

    val xAxis = barChart.xAxis

    xAxis.position = XAxis.XAxisPosition.BOTTOM

    xAxis.setDrawAxisLine(false)

    xAxis.setDrawGridLines(false)

    val countryXAxis = mutableListOf<String>()
    for (i in chartData) countryXAxis.add(i.name.common)
    xAxis.valueFormatter = CustomXAxisValueFormatter(countryXAxis.toTypedArray())

    val leftAxis = barChart.axisLeft
    leftAxis.setDrawAxisLine(false)

    val rightAxis = barChart.axisRight
    rightAxis.isEnabled = false


    rightAxis.setDrawAxisLine(false)
    val legend = barChart.legend

    legend.form = Legend.LegendForm.LINE

    legend.textSize = 11f

    legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
    legend.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
    legend.orientation = Legend.LegendOrientation.HORIZONTAL

    legend.textColor = Color.DKGRAY
    legend.setDrawInside(false)
}
