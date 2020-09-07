package app.by.wildan.workshopkotlin.main.ui.home

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import app.by.wildan.workshopkotlin.R
import app.by.wildan.workshopkotlin.common.HorizontalMarginDecorator
import app.by.wildan.workshopkotlin.extension.toRupiahFormat
import app.by.wildan.workshopkotlin.main.ui.home.domain.*
import app.by.wildan.workshopkotlin.main.ui.transaction.dialog.Category
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    private lateinit var budgetControlAdapter: BudgetControlAdapter
    private lateinit var filterAdapter: FilterAdapter
    private lateinit var transactionAdapter: TransactionAdapter

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
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        textGreeting.text = "Hai, ${auth.currentUser?.displayName}"


        setupBudgetList()
        setupTransactionList()
        setupFilterList()

        getData()

    }

    private fun getData() {
        switcher.displayedChild = 0

        val transactionRef = database.child("users").child(auth.uid ?: "").child("transactions")

        val transactionListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.d(TAG, "loadPost:dataChange ${dataSnapshot.toString()}")

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
        updateMonthlyOverview()
        updateBudgetControl()
        updateFilter()
        updateTransaction()
        switcher.displayedChild = 1
    }

    private fun updateMonthlyOverview() {
        val total = transactions.monthlyIncome - transactions.monthlyExpense

        textMonthlyOverview.text =total.toRupiahFormat()
    }

    private fun updateBudgetControl() {
        budgetControlAdapter.updateData(transactions.budgetControlMonthly)
    }

    private fun updateFilter(){
        val filter :MutableList<Category> = mutableListOf(Category(name = "All",key = "all",selected = true))
        filter.addAll(transactions.filterMonthly)
        filterAdapter.updateData(filter)
    }

    private fun updateTransaction(){
        transactionAdapter.updateData(transactions.thisMonth)
    }


    private fun setupBudgetList() {

        budgetControlAdapter = BudgetControlAdapter()
        listBudgetControl.apply {
            adapter = budgetControlAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

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
        filterAdapter = FilterAdapter()
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

        filterAdapter.addOnItemSelected {filterBy ->
            transactionAdapter.updateData(transactions.filter { if(filterBy.key =="all") true else it.category?.key == filterBy.key })
        }

    }

    private fun setupTransactionList() {
        transactionAdapter = TransactionAdapter()
        listTransaction.apply {
            adapter = transactionAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    companion object {
        private const val TAG = "HomeFragment"
    }

}