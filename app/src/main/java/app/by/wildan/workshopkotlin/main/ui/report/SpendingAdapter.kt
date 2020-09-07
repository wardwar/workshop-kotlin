package app.by.wildan.workshopkotlin.main.ui.report

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import app.by.wildan.workshopkotlin.R
import app.by.wildan.workshopkotlin.extension.toRupiahFormat
import app.by.wildan.workshopkotlin.main.ui.home.domain.Transaction
import kotlinx.android.synthetic.main.item_spending.view.*
import kotlinx.android.synthetic.main.item_transaction.view.emoji
import kotlinx.android.synthetic.main.item_transaction.view.textAmount
import kotlinx.android.synthetic.main.item_transaction.view.textBudgetTitle

class SpendingAdapter(val items: List<Transaction>) :
    RecyclerView.Adapter<SpendingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_spending, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            bind(items[position])
        }
    }


    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Transaction) = with(view) {

            emoji.text = item.category?.emoji
            textBudgetTitle.text = item.category?.name
            textAmount.text = item.amount?.toRupiahFormat()

            val colorText = when (item.category?.type) {
                "expense" -> ContextCompat.getColor(context, R.color.red)
                else -> ContextCompat.getColor(context, R.color.green)
            }

            val colorBg = when (item.category?.type) {
                "expense" -> ContextCompat.getColor(context, R.color.softRed)
                else -> ContextCompat.getColor(context, R.color.softGreen)
            }

            textAmount.setTextColor(colorText)
            textBudgetTitle.setTextColor(colorText)
            cardTransaction.setCardBackgroundColor(colorBg)

        }

    }
}