package com.example.compose.chart

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.IAxisValueFormatter

class CustomXAxisValueFormatter(private val mLabels: Array<String>) :
    IAxisValueFormatter {
    override fun getFormattedValue(value: Float, axis: AxisBase?): String {
        return mLabels[value.toInt()]
    }
}