package app.by.wildan.workshopkotlin.main.ui.transaction.dialog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import app.by.wildan.workshopkotlin.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.dialog_select_category.*


class SelectCategoryDialog : DialogFragment() {

    var listener: SelectorDialogInteraction? = null
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = Firebase.database.reference
        auth = Firebase.auth
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_select_category, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



        val categoryAdapter = CategoryAdapter()
        listCategory.apply {
            adapter = categoryAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        categoryAdapter.addOnItemSelected {
            listener?.onCategorySelected(it)
            dialog?.dismiss()
        }

        val categoryRef = database.child("users").child(auth.uid ?: "").child("categories")

        val categoryListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val listCategory = mutableListOf<Category>()
                for (snapshot in dataSnapshot.children) {
                    val category = snapshot.getValue<Category>()
                    category?.key = snapshot.key
                    category?.let {
                        listCategory.add(it)
                    }
                }

                categoryAdapter.updateData(listCategory)

            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }

        categoryRef.addValueEventListener(categoryListener)
    }


    override fun onResume() {
        super.onResume()

        val params: ViewGroup.LayoutParams? = dialog?.window?.attributes
        val width = resources.getDimensionPixelSize(R.dimen.dialog_width)
        params?.width = width
        params?.height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog?.window?.attributes = params as WindowManager.LayoutParams
    }

    companion object {
        private const val TAG = "SelectCategoryDialog"
    }

}