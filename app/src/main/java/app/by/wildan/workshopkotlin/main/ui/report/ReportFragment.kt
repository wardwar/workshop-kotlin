package app.by.wildan.workshopkotlin.main.ui.report

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import app.by.wildan.workshopkotlin.R
import app.by.wildan.workshopkotlin.main.ui.home.domain.Transaction
import app.by.wildan.workshopkotlin.main.ui.home.domain.monthlyExpense
import app.by.wildan.workshopkotlin.main.ui.home.domain.monthlyIncome
import app.by.wildan.workshopkotlin.main.ui.home.domain.reportMontly
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.MPPointF
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_report.*


class ReportFragment : Fragment() {

    private lateinit var spendingAdapter: SpendingAdapter

    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth

    private val transactions: MutableList<Transaction> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = Firebase.database.reference
        auth = Firebase.auth
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_report, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        chartReport.setUsePercentValues(true)
        chartReport.description.isEnabled = false
        setupSpendingList()

        getData()

    }

    private fun getData() {
        switcher.displayedChild = 0

        val transactionRef = database.child("users").child(auth.uid ?: "").child("transactions")

        val transactionListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.d(TAG, "loadPost:dataChange $dataSnapshot")

                transactions.clear()
                for (snapshot in dataSnapshot.children) {
                    val transaction = snapshot.getValue<Transaction>()
                    transaction?.key = snapshot.key
                    transaction?.let {
                        transactions.add(it)
                    }
                }
                updateUI()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }
        }

        transactionRef.addListenerForSingleValueEvent(transactionListener)
    }

    private fun updateUI() {
        updateChart()
        updateList()
        switcher.displayedChild = 1
    }

    private fun updateChart() {
        val expense = transactions.monthlyExpense
        val income = transactions.monthlyIncome
        val total = income + expense

        val incomePercent = (income.toFloat() / total) * 100
        val expensePercent = (expense.toFloat() / total) * 100

        val entries: ArrayList<PieEntry> = ArrayList()

        entries.add(PieEntry(incomePercent, "Income"))
        entries.add(PieEntry(expensePercent, "Expense"))

        val dataSet = PieDataSet(entries, "Monthly Report")
        dataSet.setDrawIcons(false)
        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0f, 40f)
        dataSet.selectionShift = 5f

        val colors: ArrayList<Int> = ArrayList()
        colors.add(Color.rgb(33, 150, 83))
        colors.add(Color.rgb(242, 153, 74))

        dataSet.colors = colors
        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter(chartReport))
        data.setValueTextSize(11f)
        data.setValueTextColor(Color.WHITE)
        chartReport.data = data
        chartReport.highlightValues(null)
        chartReport.invalidate()
    }

    private fun updateList() {
        spendingAdapter.updateData(transactions.reportMontly)
    }


    private fun setupSpendingList() {

        spendingAdapter = SpendingAdapter()
        listSpending.apply {
            adapter = spendingAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }


    companion object {
        private const val TAG = "ReportFragment"
    }

}