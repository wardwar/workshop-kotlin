package app.by.wildan.workshopkotlin.main.ui.transaction.dialog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import app.by.wildan.workshopkotlin.R
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter(val items: List<Category>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var listener: AdapterView.OnItemClickListener? = null

    fun addOnItemSelected(selected: (Category) -> Unit) {
        listener = AdapterView.OnItemClickListener { parent, view, position, id ->
            selected(items[position])
        }
    }

    private fun select(item: Category) {
        items.map { it.selected = false }
        val change = items.withIndex().find { item == it.value }
        change?.value?.selected = true
        listener?.onItemClick(null, null, change?.index ?: 0, 0L)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
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
        fun bind(item: Category) = with(view) {
            emoji.text = item.emoji
            textName.text = item.name

            if (item.selected) {
                imageCheck.visibility = View.VISIBLE
            } else {
                imageCheck.visibility = View.INVISIBLE
            }

            setOnClickListener { select(item) }
        }

    }
}