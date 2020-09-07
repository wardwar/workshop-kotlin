package app.by.wildan.workshopkotlin.main.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import app.by.wildan.workshopkotlin.R
import app.by.wildan.workshopkotlin.main.ui.transaction.dialog.Category
import kotlinx.android.synthetic.main.item_filter.view.*

class FilterAdapter : RecyclerView.Adapter<FilterAdapter.ViewHolder>() {

    private val items: MutableList<Category> = mutableListOf()

    private var listener: AdapterView.OnItemClickListener? = null

    fun addOnItemSelected(selected: (Category) -> Unit) {
        listener = AdapterView.OnItemClickListener { parent, view, position, id ->
            selected(items[position])
        }
    }

    fun updateData(updateList: List<Category>) {
        this.items.apply {
            clear()
            addAll(updateList)
            notifyDataSetChanged()
        }
    }

    private fun select(item: Category) {
        items.withIndex().map { it.value.selected = false;it }
            .firstOrNull { it.value.key == item.key }
            .also { it?.value?.selected = true }
            .also { listener?.onItemClick(null,null,it?.index?:0,0L) }
            .also { notifyDataSetChanged() }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_filter, parent, false)
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