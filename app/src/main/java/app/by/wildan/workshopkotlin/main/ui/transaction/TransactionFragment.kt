package app.by.wildan.workshopkotlin.main.ui.transaction

import android.content.res.AssetManager
import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import app.by.wildan.workshopkotlin.R
import app.by.wildan.workshopkotlin.extension.toRupiahFormat
import app.by.wildan.workshopkotlin.main.ui.transaction.dialog.Category
import app.by.wildan.workshopkotlin.main.ui.transaction.dialog.CreateCategoryDialog
import app.by.wildan.workshopkotlin.main.ui.transaction.dialog.SelectCategoryDialog
import app.by.wildan.workshopkotlin.main.ui.transaction.dialog.SelectorDialogInteraction
import kotlinx.android.synthetic.main.fragment_transaction.*


class TransactionFragment : Fragment(), SelectorDialogInteraction {

    private var inputAmount = "0"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transaction, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        buttonCreate.setOnClickListener {
            showCreateDialog()
        }

        selectorCategory.setOnClickListener {
            showCategorySelectorDialog()
        }

        setupCustomInput()
    }

    private fun showCreateDialog() {
        val categoryDialog = CreateCategoryDialog()
        categoryDialog.show(childFragmentManager, "CreateDialog")

    }

    private fun showCategorySelectorDialog() {
        val categorySelectorDialog = SelectCategoryDialog()
        categorySelectorDialog.listener = this
        categorySelectorDialog.show(childFragmentManager, "SelectorDialog")

    }

    override fun onCategorySelected(category: Category) {
        selectorCategory.typeface = ResourcesCompat.getFont(requireContext(),R.font.raleway_bold)
        selectorCategory.setTextSize(TypedValue.COMPLEX_UNIT_SP,18f)
        selectorCategory.setTextColor(ContextCompat.getColor(requireContext(),R.color.textSecondary))
        selectorCategory.text = "${category.emoji}   ${category.name}"
    }

    private fun setupCustomInput() {
        button0.setOnClickListener {
            if (inputAmount != "0") {
                inputAmount += "0"
            }
            updateAmount()
        }

        button1.setOnClickListener {
            if (inputAmount == "0") {
                inputAmount = "1"
            } else {
                inputAmount += "1"
            }
            updateAmount()
        }

        button2.setOnClickListener {
            if (inputAmount == "0") {
                inputAmount = "2"
            } else {
                inputAmount += "2"
            }
            updateAmount()
        }
        button3.setOnClickListener {
            if (inputAmount == "0") {
                inputAmount = "3"
            } else {
                inputAmount += "3"
            }
            updateAmount()
        }

        button4.setOnClickListener {
            if (inputAmount == "0") {
                inputAmount = "4"
            } else {
                inputAmount += "4"
            }
            updateAmount()
        }
        button5.setOnClickListener {
            if (inputAmount == "0") {
                inputAmount = "5"
            } else {
                inputAmount += "5"
            }
            updateAmount()
        }
        button6.setOnClickListener {
            if (inputAmount == "0") {
                inputAmount = "6"
            } else {
                inputAmount += "6"
            }
            updateAmount()
        }
        button7.setOnClickListener {
            if (inputAmount == "0") {
                inputAmount = "7"
            } else {
                inputAmount += "7"
            }
            updateAmount()
        }

        button8.setOnClickListener {
            if (inputAmount == "0") {
                inputAmount = "8"
            } else {
                inputAmount += "8"
            }
            updateAmount()
        }

        button9.setOnClickListener {
            if (inputAmount == "0") {
                inputAmount = "9"
            } else {
                inputAmount += "9"
            }
            updateAmount()
        }

        button000.setOnClickListener {
            if (inputAmount != "0") {
                inputAmount += "000"
            }
            updateAmount()
        }

        buttonBack.setOnClickListener {
            if (inputAmount == "0") {
                inputAmount = "0"
            } else {
                if (inputAmount.length == 1) {
                    inputAmount = "0"
                } else {
                    inputAmount = inputAmount.dropLast(1)
                }
            }
            updateAmount()
        }
    }

    private fun updateAmount() {
        textAmount.text = inputAmount.toInt().toRupiahFormat()
    }
}