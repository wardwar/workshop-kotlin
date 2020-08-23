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
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    private lateinit var budgetControlAdapter: BudgetControlAdapter


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
//        setupFilterlist()
//        setupTransactionList()


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
            val topBottomDefault  = resources.getDimension(R.dimen.dp8).toInt()

            val decorator = HorizontalMarginDecorator(firstLast,rightLeftDefault,topBottomDefault)
            addItemDecoration(decorator)
        }

        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(listBudgetControl)
    }
}