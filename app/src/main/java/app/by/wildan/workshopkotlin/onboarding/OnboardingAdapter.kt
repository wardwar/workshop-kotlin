package app.by.wildan.workshopkotlin.onboarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.by.wildan.workshopkotlin.R
import kotlinx.android.synthetic.main.item_onboarding.view.*

class OnboardingAdapter(val items: List<Onboarding>) : RecyclerView.Adapter<OnboardingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_onboarding,parent,false)
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
        fun bind(item: Onboarding) = with(view) {
            imgDisplay.setImageResource(item.image)
            textTitle.setText(item.title)
            textSubtitle.setText(item.subtitle)
        }

    }
}