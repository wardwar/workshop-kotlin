package app.by.wildan.workshopkotlin.main.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import app.by.wildan.workshopkotlin.R
import app.by.wildan.workshopkotlin.extension.toDefaultDateFormat
import app.by.wildan.workshopkotlin.extension.toRupiahFormat
import app.by.wildan.workshopkotlin.main.ui.home.domain.Transaction
import kotlinx.android.synthetic.main.item_transaction.view.*

class TransactionAdapter :
    RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {
    private val items: MutableList<Transaction> = mutableListOf()

    fun updateData(updateList: List<Transaction>) {
        this.items.apply {
            clear()
            addAll(updateList)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_transaction, parent, false)
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
            textDate.text = item.date?.toDefaultDateFormat()

            val color = when (item.category?.type) {
                "expense" -> ContextCompat.getColor(context, R.color.red)
                else -> ContextCompat.getColor(context, R.color.green)
            }
            textAmount.setTextColor(color)

        }

    }
}