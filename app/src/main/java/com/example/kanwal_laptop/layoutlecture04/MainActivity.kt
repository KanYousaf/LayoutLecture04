package com.example.kanwal_laptop.layoutlecture04

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var LeftButton: Button
    private lateinit var RightButton: Button
    private lateinit var scoreBoard: TextView
    private var num1 : Int = 0
    private var num2 : Int =0
    private var points : Int =0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        LeftButton= findViewById(R.id.left_button)
        RightButton= findViewById(R.id.right_button)
        scoreBoard = findViewById(R.id.score_board)
        //use set data to generate random numbers and text of buttons LEFT and RIGHT
       setData()
    }

    fun leftButtonClick(view: View){
        //increment the score , if greater number chosen else decrement in score
        if(LeftButton.text.toString().toInt() > RightButton.text.toString().toInt()){
            points++
        }else{
            points--
        }
        scoreBoard.text = "$points"
        setData()
    }

    fun rightButtonClick(view: View){
        //increment the score , if greater number chosen else decrement in score
        if(RightButton.text.toString().toInt() > LeftButton.text.toString().toInt()){
            points++
        }else{
            points--
        }
        scoreBoard.text = "$points"
        setData()
    }

    fun randomNumberGenerate(){
        var r = Random()
        num1 = r.nextInt(200)
        num2 = r.nextInt(200)
        if (num1 == num2){
            num1 = r.nextInt(200)
        }
    }

    fun setData(){
        randomNumberGenerate()
        LeftButton.text = "$num1"
        RightButton.text = "$num2"
    }
}
