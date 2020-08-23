package app.by.wildan.workshopkotlin.main.ui.report

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import app.by.wildan.workshopkotlin.R
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.MPPointF
import kotlinx.android.synthetic.main.fragment_report.*


class ReportFragment : Fragment() {

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

        setData()
    }

    private fun setData() {
        val entries: ArrayList<PieEntry> = ArrayList()

        entries.add(PieEntry(50f, "Income"))
        entries.add(PieEntry(50f, "Expense"))

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
}