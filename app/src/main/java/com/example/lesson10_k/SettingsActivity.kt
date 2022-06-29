package com.example.lesson10_k

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat


class SettingsActivity : AppCompatActivity() {
    companion object {
        const val KEY_SIZE_VALUE = "sizeValue"
        const val KEY_COLOR_VALUE = "colorValue"
    }

    private var radioTen: RadioButton? = null
    private var radioBlack: RadioButton? = null

    private var radioGroupSize: RadioGroup? = null
    private var radioGroupColor: RadioGroup? = null

    private var sizeText: Float? = null
    private var colorText: Int? = null

    private fun findViewsById() {
        radioTen = findViewById(R.id.tenSize)
        radioBlack = findViewById(R.id.blackColor)

        radioGroupSize = findViewById(R.id.radioGroupSize)
        radioGroupColor = findViewById(R.id.radioGroupColor)
    }

    private fun changeAppBar() {
        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun updateSizeColorTxt(firstValue: Float?, secondValue: Int?) {
        val sp = getSharedPreferences("", Context.MODE_PRIVATE)
        val editor = sp.edit()
        if (firstValue != null) {
            editor.putFloat(KEY_SIZE_VALUE, firstValue)
        } else {
            editor.putFloat(KEY_SIZE_VALUE, resources.getDimension(R.dimen.text_ten))
        }
        if (secondValue != null) {
            editor.putInt(KEY_COLOR_VALUE, secondValue)
        } else {
            editor.putInt(
                KEY_COLOR_VALUE,
                ContextCompat.getColor(applicationContext, R.color.black)
            )
        }
        editor.apply()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setOnCheckedChangeListeners() {
        radioGroupSize?.setOnCheckedChangeListener { _, checkedId -> // checkedId is the RadioButton selected
            val rb = findViewById<View>(checkedId) as RadioButton
            when (rb.id) {
                R.id.tenSize -> {
                    sizeText = resources.getDimension(R.dimen.text_ten)
                }
                R.id.twentySize -> {
                    sizeText = resources.getDimension(R.dimen.text_twenty)
                }
                R.id.thirtySize -> {
                    sizeText = resources.getDimension(R.dimen.text_thirty)
                }
            }
        }

        radioGroupColor?.setOnCheckedChangeListener { _, checkedId ->
            val rb = findViewById<View>(checkedId) as RadioButton
            when (rb.id) {
                R.id.blackColor -> {
                    colorText = ContextCompat.getColor(applicationContext, R.color.black)
                }
                R.id.greenColor -> {
                    colorText = ContextCompat.getColor(applicationContext, R.color.green)
                }
                R.id.purpleColor -> {
                    colorText = ContextCompat.getColor(applicationContext, R.color.purple_500)
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        findViewsById()

        radioTen?.isChecked = true
        radioBlack?.isChecked = true

        changeAppBar()

        setOnCheckedChangeListeners()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onPause() {
        updateSizeColorTxt(sizeText, colorText)
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()

        radioTen = null
        radioBlack = null

        radioGroupSize = null
        radioGroupColor = null
    }
}