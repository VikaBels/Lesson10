package com.example.lesson10_k

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.lesson10_k.MainActivity.Companion.FILE_NAME
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.InputStreamReader


class ShowFileActivity : AppCompatActivity() {
    companion object {
        const val EMPTY_LINE = ""
    }

    private var txtViewTextFile: TextView? = null

    private fun findViewById() {
        txtViewTextFile = findViewById(R.id.txtViewTextFile)
    }

    private fun getValueSize(): Float {
        val sp = getSharedPreferences(EMPTY_LINE, Context.MODE_PRIVATE)
        return sp.getFloat(
            SettingsActivity.KEY_SIZE_VALUE,
            resources.getDimension(R.dimen.text_twenty)
        )
    }

    private fun getValueColor(): Int {
        val sp = getSharedPreferences(EMPTY_LINE, Context.MODE_PRIVATE)
        return sp.getInt(
            SettingsActivity.KEY_COLOR_VALUE,
            ContextCompat.getColor(applicationContext, R.color.black)
        )
    }

    private fun readLineByLine() {
        try {
            BufferedReader(InputStreamReader(openFileInput(FILE_NAME))).use { br ->
                var str: String?
                val txtForTextEdit = StringBuilder()
                var i = 0
                while (br.readLine().also { str = it } != null) {
                    i++
                    txtForTextEdit.append("$i.$str\n")
                }
                txtViewTextFile?.text = txtForTextEdit.toString()
            }
        } catch (noFile: FileNotFoundException) {
            noFile.printStackTrace()
        }
    }

    private fun changeAppBar() {
        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_file)

        findViewById()

        changeAppBar()

        txtViewTextFile?.setTextColor(getValueColor());
        txtViewTextFile?.textSize = getValueSize()

        readLineByLine()
    }

    override fun onDestroy() {
        super.onDestroy()

        txtViewTextFile = null
    }
}