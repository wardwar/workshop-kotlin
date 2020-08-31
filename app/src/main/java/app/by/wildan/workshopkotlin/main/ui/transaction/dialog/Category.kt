package app.by.wildan.workshopkotlin.main.ui.transaction.dialog

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class Category(
    val emoji: String? = "",
    val limit: Int? = 0,
    val name: String? = "",
    val type: String? = "",
    var selected: Boolean = false,
    var key: String? = "" )