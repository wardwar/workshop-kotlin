package app.by.wildan.workshopkotlin.main.ui.transaction.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import app.by.wildan.workshopkotlin.R
import kotlinx.android.synthetic.main.dialog_select_category.*


class SelectCategoryDialog : DialogFragment() {

    var listener: SelectorDialogInteraction? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_select_category, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val dummy = listOf(
            Category(
                "🍔",
                "Food",
                false
            ),
            Category(
                "☕️",
                "Coffee",
                false
            ),
            Category(
                "📡",
                "Internet",
                false
            ), Category(
                "😎",
                "Nongkrong",
                false
            )
        )
        val categoryAdapter = CategoryAdapter(dummy)
        listCategory.apply {
            adapter = categoryAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        categoryAdapter.addOnItemSelected {
            listener?.onCategorySelected(it)
            dialog?.dismiss()
        }

    }


    override fun onResume() {
        super.onResume()

        val params: ViewGroup.LayoutParams? = dialog?.window?.attributes
        val width = resources.getDimensionPixelSize(R.dimen.dialog_width)
        params?.width = width
        params?.height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog?.window?.attributes = params as WindowManager.LayoutParams
    }

}