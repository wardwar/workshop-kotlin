package app.by.wildan.workshopkotlin.main.ui.transaction.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import app.by.wildan.workshopkotlin.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.vanniktech.emoji.EmojiPopup
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.dialog_create_category.*


class CreateCategoryDialog : DialogFragment() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }

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

        buttonCreateCategory.setOnClickListener {
            val type = if (radioIncome.isChecked) "income" else "expense"
            val payload = Category(
                emoji.text.toString(),
                inputLimit.text.toString().ifBlank { "0" }.toInt(),
                inputName.text.toString(),
                type
            )
            saveToDatabase(payload)
        }
    }

    private fun saveToDatabase(payload: Category) {
        performLoading(true)
        //mendapatkan id dari user saat register
        val uid = auth.uid
        //menginisiasi instance database
        val database = Firebase.database
        //menambahkan referensi
        val myRef = database.getReference("users/$uid/categories")

        myRef.push().setValue(payload).addOnCompleteListener {
            if (!it.isSuccessful) {
                Toast.makeText(
                    requireContext(), "Error Created. ${it.exception?.localizedMessage}",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    requireContext(), "Category Created.",
                    Toast.LENGTH_SHORT
                ).show()
                dialog?.dismiss()
            }
            performLoading(false)
        }
    }

    private fun performLoading(isLoading: Boolean) {
        if (isLoading) {
            buttonCreateCategory.visibility = View.INVISIBLE
            progress.visibility = View.VISIBLE
            buttonCreateCategory.isEnabled = false
        } else {
            buttonCreateCategory.visibility = View.VISIBLE
            progress.visibility = View.INVISIBLE
            buttonCreateCategory.isEnabled = true
        }
    }

    override fun onResume() {
        super.onResume()

        val width = resources.getDimensionPixelSize(R.dimen.dialog_width)
        val height = resources.getDimensionPixelSize(R.dimen.dialog_height)
        dialog?.window?.setLayout(width, height)
    }

}