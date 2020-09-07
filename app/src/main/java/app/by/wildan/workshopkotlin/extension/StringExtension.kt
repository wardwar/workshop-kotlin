package app.by.wildan.workshopkotlin.extension

import java.text.SimpleDateFormat
import java.util.*

fun String.toDefaultDateFormat(): String {
    val curDate = Calendar.getInstance().time
    curDate.time = this.ifBlank { "1598908136284" }.toLong()
    val format = SimpleDateFormat("EEE, d MMM yyyy", Locale.getDefault())
    return format.format(curDate)
}


fun String.timeMillisToCalendar(): Calendar {
    val curDate = Calendar.getInstance()
    val date = Date()
    date.time = this.ifBlank { "1598908136284" }.toLong()
    curDate.time = date
    return curDate
}