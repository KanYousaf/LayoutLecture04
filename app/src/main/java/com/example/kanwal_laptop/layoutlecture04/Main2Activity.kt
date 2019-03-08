package com.example.kanwal_laptop.layoutlecture04

import android.app.Activity
import android.content.Intent
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
    private val REQ_CODE : Int = 1234
    private var adapter: ArrayAdapter<String>? = null
    private var rankArrayToList: MutableList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        none_rb.isChecked = true
        display_tmnt_character.visibility = View.GONE

        //set the rating bar
        rate_tmnt.setOnRatingBarChangeListener { _, rating, _ ->
            rate_tmnt.rating = rating
        }

        //convert string-array in string.xml file to mutable list
        rankArrayToList = resources.getStringArray(R.array.tmnt_rank).toMutableList()

        //set array adapter of spinner with build-in style
        adapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_dropdown_item,
            rankArrayToList
        )
        //attach array adapter to spinner
        spinner.adapter = adapter

        //make anonymous class for spinner
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var item = parent?.getItemAtPosition(position).toString()
                if (parent?.selectedItem != 0) {
                    Toast.makeText(
                        parent?.context,
                        "item is $item",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }
    }

    fun submitButtonClicked(view: View) {
        if (UserName.text.toString().trim().isEmpty() ||
            UserPwd.text.toString().trim().isEmpty()
        ) {
            Toast.makeText(
                this@Main2Activity,
                "Enter User name and password",
                Toast.LENGTH_SHORT
            ).show()
        } else {
//            Toast.makeText(
//                this@Main2Activity,
//                "User name: ${UserName.text}" +
//                        "Password: ${UserPwd.text}" +
//                        "Rating: ${rate_tmnt.rating}",
//                Toast.LENGTH_SHORT
//            ).show()

            //forward user name to next activity

            intent = Intent(this, GraphActivity :: class.java)
            intent.putExtra("User" , UserName.text.toString())
            startActivityForResult(intent, REQ_CODE )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode.equals(REQ_CODE) && resultCode.equals(Activity.RESULT_OK)){
            val fetchedKey = data?.extras?.getInt("key")
            randomKey.text = "$fetchedKey"
        }
    }


    fun tmntSelectionFunction(view: View) {
        if (don_rb.isChecked || leo_rb.isChecked || mike_rb.isChecked || raph_rb.isChecked) {
            display_tmnt_character.visibility = View.VISIBLE
        }
        //select radio button and change the images of characters
        when (view) {
            don_rb ->
                display_tmnt_character.setImageResource(R.drawable.tmntdon)
            leo_rb ->
                display_tmnt_character.setImageResource(R.drawable.tmntleo)
            mike_rb ->
                display_tmnt_character.setImageResource(R.drawable.tmntmike)
            raph_rb ->
                display_tmnt_character.setImageResource(R.drawable.tmntraph)
            none_rb ->
                display_tmnt_character.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity2, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        var selectedItem = ""
        when (item?.itemId) {
            R.id.home -> selectedItem = "Home"
            R.id.setting -> {
                selectedItem = "Settings"
                intent = Intent(this, MainActivity ::class.java)
                startActivity(intent)
            }
            R.id.help -> selectedItem = "Help"
            R.id.exit -> exitProcess(0)
        }
        Toast.makeText(
            this, "You selected $selectedItem",
            Toast.LENGTH_SHORT
        ).show()
        return super.onOptionsItemSelected(item)
    }

}
