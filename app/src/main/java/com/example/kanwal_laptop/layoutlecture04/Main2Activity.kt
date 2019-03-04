package com.example.kanwal_laptop.layoutlecture04

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import kotlin.system.exitProcess

import kotlinx.android.synthetic.main.activity_main2.*


class Main2Activity : AppCompatActivity() {
    private lateinit var UserNameET: EditText
    private lateinit var UserPwdET: EditText
    private lateinit var noneRadioButton: RadioButton
    private lateinit var imageViewCharacter: ImageView

    private lateinit var donRadioButton: RadioButton
    private lateinit var leoRadioButton: RadioButton
    private lateinit var mikeRadioButton: RadioButton
    private lateinit var raphRadioButton: RadioButton

    private var rankArrayToList: MutableList<String>? = null
    private var spinnerAdapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        noneRadioButton = findViewById(R.id.none_rb)
        noneRadioButton.isChecked = true

        imageViewCharacter = findViewById(R.id.display_tmnt_character)
        imageViewCharacter.visibility = View.GONE

        rate_tmnt.setOnRatingBarChangeListener { _, rating, _ ->
            rate_tmnt.rating = rating
        }

        //convert string array data into mutable list
        rankArrayToList = resources.getStringArray(R.array.Rank).toMutableList()

        //use array adapter to set spinner adapter
        spinnerAdapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_dropdown_item,
            rankArrayToList
        )

        //attach adapter to spinner
        rank_spinner.adapter = spinnerAdapter
        //introduce anonymous inner class with two methods to use spinner onItemSelected
        rank_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var itemSelected: String = ""
                itemSelected = parent?.getItemAtPosition(position).toString()
                if(parent?.selectedItemPosition!=0) {
                    Toast.makeText(parent?.context, "Item is : $itemSelected", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    fun submitButtonClicked(view: View) {
        UserNameET = findViewById(R.id.UserName)
        UserPwdET = findViewById(R.id.UserPwd)

        if (UserPwdET.text.toString().trim().isEmpty() ||
            UserNameET.text.toString().trim().isEmpty()
        ) {

            Toast.makeText(
                this@Main2Activity,
                "Enter User name and password",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(
                this@Main2Activity,
                "User name: ${UserNameET.text}" +
                        "Password: ${UserPwdET.text}" +
                        "Rating: ${rate_tmnt.rating}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    fun tmntSelectionFunction(view: View) {
        donRadioButton = findViewById(R.id.don_rb)
        leoRadioButton = findViewById(R.id.leo_rb)
        mikeRadioButton = findViewById(R.id.mike_rb)
        raphRadioButton = findViewById(R.id.raph_rb)


        if (donRadioButton.isChecked || leoRadioButton.isChecked || mikeRadioButton.isChecked
            || raphRadioButton.isChecked
        ) {
            imageViewCharacter.visibility = View.VISIBLE
        }

        when (view) {
            donRadioButton ->
                imageViewCharacter.setImageResource(R.drawable.tmntdon)
            leoRadioButton ->
                imageViewCharacter.setImageResource(R.drawable.tmntleo)
            mikeRadioButton ->
                imageViewCharacter.setImageResource(R.drawable.tmntmike)
            raphRadioButton ->
                imageViewCharacter.setImageResource(R.drawable.tmntraph)
            noneRadioButton ->
                imageViewCharacter.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity2, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        var selectedItem: String = ""
        when (item?.itemId) {
            R.id.home -> selectedItem = "Home"
            R.id.settings -> selectedItem = "Settings"
            R.id.help -> selectedItem = "Help"
            R.id.exit -> exitProcess(0)
        }

        Toast.makeText(
            this,
            "The menu option is $selectedItem", Toast.LENGTH_SHORT
        ).show()
        return super.onOptionsItemSelected(item)
    }


}
