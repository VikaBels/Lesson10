package com.example.lesson10_k

import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.RadioButton
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity


class SettingsActivity : AppCompatActivity() {
    private lateinit var radioTen: RadioButton
    private lateinit var radioTwenty: RadioButton
    private lateinit var radioThirty: RadioButton

    private lateinit var radioBlack: RadioButton
    private lateinit var radioGreen: RadioButton
    private lateinit var radioPurple: RadioButton

    private var sizeText: Float = 20F
    private var colorText: Int = -16777216

    companion object {
        const val KEY_SIZE_VALUE = "sizeValue"
        const val KEY_COLOR_VALUE = "colorValue"
    }

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

    private fun updateValues(firstValue: Float, secondValue: Int) {
        val sp = PreferenceManager
            .getDefaultSharedPreferences(this) // this - контекст
        val editor = sp.edit()
        editor.putFloat(KEY_SIZE_VALUE, firstValue)
        editor.putInt(KEY_COLOR_VALUE, secondValue)

        editor.apply()
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        findViewById()

        radioTwenty.isChecked = true
        radioBlack.isChecked = true

        changeAppBar()

        val allButton = View.OnClickListener { v ->
            when (v.id) {
                R.id.tenSize -> {
                    sizeText = resources.getDimension(R.dimen.text_ten)
                }
                R.id.twentySize -> {
                    sizeText = resources.getDimension(R.dimen.text_twenty)
                }
                R.id.thirtySize -> {
                    sizeText = resources.getDimension(R.dimen.text_thirty)
                }

                R.id.blackColor -> {
                    colorText = resources.getColor(R.color.black, null)
                }
                R.id.greenColor -> {
                    colorText = resources.getColor(R.color.green, null)
                }
                R.id.purpleColor -> {
                    colorText = resources.getColor(R.color.purple_500, null)
                }
            }
        }

        setOnClickListener(allButton)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onPause() {
        updateValues(sizeText, colorText)

        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}