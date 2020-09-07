package app.by.wildan.workshopkotlin.main.ui.home.domain

import app.by.wildan.workshopkotlin.extension.timeMillisToCalendar
import app.by.wildan.workshopkotlin.main.ui.transaction.dialog.Category
import com.google.firebase.database.IgnoreExtraProperties
import java.util.*

@IgnoreExtraProperties
data class Transaction(
    val amount: Int? = 0,
    val date: String? = "",
    val category: Category? = null,
    var key: String? = ""
)

val List<Transaction>.thisMonth: List<Transaction>
    get() = filter {
        val curDate = Calendar.getInstance()
        val dateCalender = it.date?.timeMillisToCalendar()
        dateCalender?.get(Calendar.MONTH) == curDate.get(Calendar.MONTH) && dateCalender.get(
            Calendar.YEAR
        ) == curDate.get(Calendar.YEAR)
    }

val List<Transaction>.thisWeek: List<Transaction>
    get() = filter {
        val curDate = Calendar.getInstance()
        it.date?.timeMillisToCalendar()
            ?.get(Calendar.WEEK_OF_YEAR) == curDate.get(Calendar.WEEK_OF_YEAR)
    }


val List<Transaction>.monthlyIncome: Int
    get() = thisMonth.filter { it.category?.type == "income" }.sumBy { it.amount ?: 0 }

val List<Transaction>.monthlyExpense: Int
    get() = thisMonth.filter { it.category?.type == "expense" }.sumBy { it.amount ?: 0 }

val List<Transaction>.budgetControlMonthly: List<BudgetControl>
    get() = thisMonth
        .filter { it.category?.type == "expense" }
        .distinctBy { it.category?.key }
        .groupBy {
            it.category
        }.map {
            BudgetControl(
                it.key?.emoji ?: "",
                it.key?.name ?: "",
                it.key?.limit ?: 0,
                it.value.sumBy { it.amount ?: 0 }
            )
        }

val List<Transaction>.reportMontly: List<Transaction>
    get() = thisMonth
        .distinctBy { it.category?.key }
        .groupBy {
            it.category
        }.map {
            Transaction(
                it.value.sumBy { it.amount ?: 0 },
                "",
                it.key
            )
        }.sortedByDescending { it.amount }

val List<Transaction>.filterMonthly: List<Category>
    get() = thisMonth.distinctBy { it.category?.key }
        .groupBy {
            it.category
        }
        .map {
            it.key?.selected = false
            it.key ?: Category()
        }
