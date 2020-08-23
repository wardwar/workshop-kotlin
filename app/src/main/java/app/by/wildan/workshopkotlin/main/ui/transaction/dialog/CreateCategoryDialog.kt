package app.by.wildan.workshopkotlin.main.ui.transaction.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import app.by.wildan.workshopkotlin.R
import com.vanniktech.emoji.EmojiPopup
import kotlinx.android.synthetic.main.dialog_create_category.*


class CreateCategoryDialog : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_create_category, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val emojiPopup = EmojiPopup.Builder.fromRootView(view).build(emoji)
        emoji.setOnClickListener {
            emojiPopup.toggle()

        }
    }

    override fun onResume() {
        super.onResume()

        val width = resources.getDimensionPixelSize(R.dimen.dialog_width)
        val height = resources.getDimensionPixelSize(R.dimen.dialog_height)
        dialog?.window?.setLayout(width, height)
    }

}