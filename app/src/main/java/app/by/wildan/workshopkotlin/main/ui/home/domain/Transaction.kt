package app.by.wildan.workshopkotlin.main.ui.home.domain

import app.by.wildan.workshopkotlin.main.ui.transaction.dialog.Category
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Transaction(val amount: Int?=0,
                       val date: String?="",
                       val category: Category?)