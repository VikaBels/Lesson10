package com.example.lesson10_k

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import java.io.BufferedReader
import java.io.InputStreamReader

class ShowFileActivity : AppCompatActivity() {
    private lateinit var txtViewTextFile: TextView

    private fun findViewById() {
        txtViewTextFile = findViewById(R.id.txtViewTextFile)
    }

    private fun readLineByLine() {
        val br = BufferedReader(
            InputStreamReader(
                openFileInput("data.txt")
            )
        )

        var str: String?
        var txtForTextEdit = ""
        var i = 0
        while (br.readLine().also { str = it } != null) {
            i++
            txtForTextEdit += "$i. $str\n"
        }
        txtViewTextFile.setText(txtForTextEdit, TextView.BufferType.EDITABLE);
    }

    private fun changeAppBar() {
        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_file)

        findViewById()

        changeAppBar()

        readLineByLine()
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}