package app.by.wildan.workshopkotlin.main.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import app.by.wildan.workshopkotlin.R
import app.by.wildan.workshopkotlin.common.HorizontalMarginDecorator
import app.by.wildan.workshopkotlin.main.ui.home.domain.BudgetControl
import app.by.wildan.workshopkotlin.main.ui.home.domain.Transaction
import app.by.wildan.workshopkotlin.main.ui.transaction.dialog.Category
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    private lateinit var budgetControlAdapter: BudgetControlAdapter
    private lateinit var filterAdapter: FilterAdapter
    private lateinit var transactionAdapter: TransactionAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupBudgetList()
        setupFilterList()
        setupTransactionList()


    }

    private fun setupBudgetList() {
        val dummyData = listOf(
            BudgetControl(
                "🍔",
                "Food",
                250000,
                60000
            ),
            BudgetControl(
                "📡",
                "Internet",
                250000,
                200000
            ),
            BudgetControl(
                "🥳",
                "Hangout",
                1250000,
                1500000
            )
        )
        budgetControlAdapter = BudgetControlAdapter(dummyData)
        listBudgetControl.apply {
            adapter = budgetControlAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            val firstLast = resources.getDimension(R.dimen.dp16).toInt()
            val rightLeftDefault = resources.getDimension(R.dimen.dp4).toInt()
            val topBottomDefault = resources.getDimension(R.dimen.dp8).toInt()

            val decorator = HorizontalMarginDecorator(firstLast, rightLeftDefault, topBottomDefault)
            addItemDecoration(decorator)
        }

        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(listBudgetControl)
    }

    private fun setupFilterList() {
        val dummyData = listOf(
            Category(name = "All", selected = true, key = "1"),
            Category(name = "Food", key = "2"),
            Category(name = "Internet", key = "3"),
            Category(name = "Gas", key = "4"),
            Category(name = "Party", key = "5")
        )

        filterAdapter = FilterAdapter(dummyData)
        listFilter.apply {
            adapter = filterAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            val firstLast = resources.getDimension(R.dimen.dp16).toInt()
            val rightLeftDefault = resources.getDimension(R.dimen.dp4).toInt()
            val topBottomDefault = resources.getDimension(R.dimen.dp8).toInt()

            val decorator = HorizontalMarginDecorator(firstLast, rightLeftDefault, topBottomDefault)
            addItemDecoration(decorator)
        }

    }

    private fun setupTransactionList() {
        val dummyData = listOf(
            Transaction(10000, "1598908136284", Category(name = "Food", key = "2",type = "expense")),
            Transaction(100000, "1598908136284", Category(name = "Internet", key = "3",type = "income")),
            Transaction(50000, "1598908136284", Category(name = "Gas", key = "4",type = "expense")),
            Transaction(800000, "1598908136284", Category(name = "Party", key = "5",type = "expense"))
        )

        transactionAdapter = TransactionAdapter(dummyData)
        listTransaction.apply {
            adapter = transactionAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

}