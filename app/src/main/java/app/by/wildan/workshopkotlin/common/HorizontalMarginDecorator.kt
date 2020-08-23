package app.by.wildan.workshopkotlin.common

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HorizontalMarginDecorator(private val firstLast: Int, private val leftRightDefault: Int,private val topBottomDefault : Int) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        with(outRect) {
            when (parent.getChildAdapterPosition(view)) {
                0 -> {
                    top = topBottomDefault
                    left = firstLast
                    bottom = topBottomDefault
                    right = leftRightDefault
                }
                state.itemCount - 1 -> {
                    top = topBottomDefault
                    left = leftRightDefault
                    bottom = topBottomDefault
                    right = firstLast
                }
                else -> {
                    top = topBottomDefault
                    left = leftRightDefault
                    bottom = topBottomDefault
                    right = leftRightDefault
                }
            }
        }
    }
}