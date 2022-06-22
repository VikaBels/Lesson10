package com.example.lesson10_k

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.*

class WorkWithFIleActivity : AppCompatActivity() {
    private lateinit var editTextFile: EditText

    private val FILE_NAME = "data.txt"

    private fun findViewById() {
        editTextFile = findViewById(R.id.editTextFile)
    }

    private fun changeAppBar() {
        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun saveText() {
        var fos: FileOutputStream? = null

        try {
            val text = editTextFile.text.toString()

            fos = openFileOutput(FILE_NAME, MODE_PRIVATE)
            fos.write(text.toByteArray())
            Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show()

        } catch (ex: IOException) {
            Toast.makeText(this, ex.message, Toast.LENGTH_SHORT).show()
        } finally {
            try {
                fos?.close()
            } catch (ex: IOException) {
                Toast.makeText(this, ex.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("RestrictedApi")
    private fun openText() {
        var fin: FileInputStream? = null
        try {
            fin = openFileInput(FILE_NAME)
            val bytes = ByteArray(fin.available())
            fin.read(bytes)
            val text = String(bytes)
            editTextFile.setText(text, TextView.BufferType.EDITABLE);
        } catch (ex: IOException) {
            Toast.makeText(this, ex.message, Toast.LENGTH_SHORT).show()
        } finally {
            try {
                fin?.close()
            } catch (ex: IOException) {
                Toast.makeText(this, ex.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_with_file)

        findViewById()

        changeAppBar()

        openText()
    }


    override fun onPause() {
        saveText()
        super.onPause()
    }

    //?????
    override fun onDestroy() {
        super.onDestroy()
    }
}