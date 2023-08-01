package com.example.compose.chart


import android.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.compose.countrylist.ListCountryViewModel
import com.example.compose.ui.theme.ComposeTheme
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry


@Composable
fun Chart(
    countryListViewModel: ListCountryViewModel,
    navController: NavHostController,
) {
    var chartData = countryListViewModel.getChartData()
    if(chartData == null) navController.navigateUp()
    val entries = mutableListOf<BarEntry>()
    for (i in chartData.indices) {
        val barEntry = BarEntry(i.toFloat(), chartData[i].population.toFloat() / 1000000)
        entries.add(barEntry)
    }

    val barDataSet = BarDataSet(entries, "Population (Millions)")
    initBarDataSet(barDataSet)

    val barData by remember {
        mutableStateOf(BarData(barDataSet))
    }

    ChartScreen(barData, chartData, navController)
}

private fun initBarDataSet(barDataSet: BarDataSet) {
    barDataSet.color = Color.CYAN

    barDataSet.formSize = 20f

    barDataSet.valueTextSize = 18f

    barDataSet.valueTextColor = Color.WHITE

}


@Preview(showBackground = true)
@Composable
fun ChartPreview() {
    ComposeTheme {
        Chart(ListCountryViewModel(), rememberNavController())
    }
}






