package app.by.wildan.workshopkotlin.main.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.by.wildan.workshopkotlin.R
import app.by.wildan.workshopkotlin.main.ui.transaction.dialog.Category
import kotlinx.android.synthetic.main.item_filter.view.*
import kotlinx.android.synthetic.main.item_onboarding.view.*

class FilterAdapter(val items: List<Category>) : RecyclerView.Adapter<FilterAdapter.ViewHolder>() {

    private fun select(item:Category){
        items.map { it.selected = false;it }
            .firstOrNull { it.key == item.key }
            .also { it?.selected = true }
            .also { notifyDataSetChanged()  }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.item_filter,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            bind(items[position])
        }
    }


    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Category) = with(view){
            textFilter.text = item.name

            textFilter.isSelected =item.selected

            setOnClickListener {
                select(item)
            }
        }

    }
}