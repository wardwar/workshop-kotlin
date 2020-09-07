package app.by.wildan.workshopkotlin.main.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.by.wildan.workshopkotlin.R
import app.by.wildan.workshopkotlin.extension.percentOf
import app.by.wildan.workshopkotlin.extension.toRupiahFormat
import app.by.wildan.workshopkotlin.main.ui.home.domain.BudgetControl
import kotlinx.android.synthetic.main.item_budget_control.view.*

class BudgetControlAdapter :
    RecyclerView.Adapter<BudgetControlAdapter.ViewHolder>() {
    private val items: MutableList<BudgetControl> = mutableListOf()


    fun updateData(updateList: List<BudgetControl>) {
        this.items.apply {
            clear()
            addAll(updateList)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_budget_control, parent, false)
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
        fun bind(item: BudgetControl) = with(view) {
            emoji.text = item.emoji
            textBudgetTitle.text = item.name
            textBudgetAmount.text = item.amount.toRupiahFormat()
            progressBudget.progress = item.curAmount.percentOf(item.amount)
        }

    }
}