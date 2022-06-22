package com.example.lesson10_k

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {

    private lateinit var radioTen: RadioButton
    private lateinit var radioTwenty: RadioButton
    private lateinit var radioThirty: RadioButton

    private lateinit var radioBlack: RadioButton
    private lateinit var radioGreen: RadioButton
    private lateinit var radioPurple: RadioButton

    private var sizeText: Int = 10
    private lateinit var colorText: String

    private fun findViewById() {
        radioTen = findViewById(R.id.tenSize)
        radioTwenty = findViewById(R.id.twentySize)
        radioThirty = findViewById(R.id.thirtySize)

        radioBlack = findViewById(R.id.blackColor)
        radioGreen = findViewById(R.id.greenColor)
        radioPurple = findViewById(R.id.purpleColor)
    }

    private fun changeAppBar() {
        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setOnClickListener(allButton: View.OnClickListener?) {
        radioTen.setOnClickListener(allButton)
        radioTwenty.setOnClickListener(allButton)
        radioThirty.setOnClickListener(allButton)
        radioBlack.setOnClickListener(allButton)
        radioGreen.setOnClickListener(allButton)
        radioPurple.setOnClickListener(allButton)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        findViewById()

        changeAppBar()

        val allButton = View.OnClickListener { v ->
            when (v.id) {
                R.id.tenSize -> {
                    sizeText = Integer.parseInt(radioTen.text.toString())
                }
                R.id.twentySize -> {
                    sizeText = Integer.parseInt(radioTwenty.text.toString())
                }
                R.id.thirtySize -> {
                    sizeText = Integer.parseInt(radioThirty.text.toString())
                }

                R.id.blackColor -> {
                    colorText = radioBlack.text.toString()
                }
                R.id.greenColor -> {
                    colorText = radioGreen.text.toString()
                }
                R.id.purpleColor -> {
                    colorText = radioPurple.text.toString()
                }
            }
        }

        setOnClickListener(allButton)
    }

    override fun onPause() {

        super.onPause()
    }

    //????
    override fun onDestroy() {
        super.onDestroy()
    }

}