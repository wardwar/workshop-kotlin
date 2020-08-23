package app.by.wildan.workshopkotlin.extension

import java.text.NumberFormat
import java.util.*

fun Int.toRupiahFormat(): String {
    val localeID = Locale("in", "ID")
    val format: NumberFormat = NumberFormat.getCurrencyInstance(localeID)
    format.maximumFractionDigits = 0
    return format.format(this)
}

fun Int.percentOf(value:Int):Int{
    return ((this.toFloat()/value.toFloat()) * 100).toInt()
}