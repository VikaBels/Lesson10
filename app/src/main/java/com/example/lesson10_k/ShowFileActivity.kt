package com.example.lesson10_k

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.lesson10_k.MainActivity.Companion.FILE_NAME
import java.io.BufferedReader
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
        return sp.getFloat(SettingsActivity.KEY_SIZE_VALUE, 20F)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun getValueColor(): Int {
        val sp = getSharedPreferences(EMPTY_LINE, Context.MODE_PRIVATE)
        return sp.getInt(SettingsActivity.KEY_COLOR_VALUE, resources.getColor(R.color.black, null))
    }

    private fun readLineByLine() {
        val br = BufferedReader(
            InputStreamReader(
                openFileInput(FILE_NAME)
            )
        )
        var str: String?
        var txtForTextEdit = EMPTY_LINE
        var i = 0
        while (br.readLine().also { str = it } != null) {
            i++
            txtForTextEdit += "$i. $str\n"

        }
        txtViewTextFile?.setText(txtForTextEdit, TextView.BufferType.EDITABLE);
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
    @SuppressLint("ResourceAsColor")
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