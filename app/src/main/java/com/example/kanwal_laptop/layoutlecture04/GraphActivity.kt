package com.example.kanwal_laptop.layoutlecture04

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.activity_graph.*
import java.util.*



class GraphActivity : AppCompatActivity() {
    private var x: Double = 0.0
    private var y: Double = 0.0
    private var lineSeries: LineGraphSeries<DataPoint>? = null
    private var receivedUserName: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph)

        //display user name
        receivedUserName = intent.extras?.getString("User").toString()
        userName.text = receivedUserName
        displayGraphSeries()
    }

    fun displayGraphSeries() {
        lineSeries = LineGraphSeries()

        for (i in 0..1000) {
            x = x + 0.1
            y = Math.cos(x)
            lineSeries?.appendData(DataPoint(x, y), true, 1000)
        }
        graphDisplay.addSeries(lineSeries)
    }

    fun onBackClick(view: View) {
        val r = Random().nextInt(1000)
        intent = Intent(this, Main2Activity::class.java)
        intent.putExtra("key", r)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
