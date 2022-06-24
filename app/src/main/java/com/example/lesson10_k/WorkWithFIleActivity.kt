package com.example.lesson10_k

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.TypedValue
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lesson10_k.MainActivity.Companion.FILE_NAME
import java.io.*

class WorkWithFIleActivity : AppCompatActivity() {
    private var editTextFile: EditText? = null

    private fun findViewById() {
        editTextFile = findViewById(R.id.editTextFile)
    }

    private fun changeAppBar() {
        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        return true
    }

    private fun saveText() {
        var fos: FileOutputStream? = null
        try {
            val text = editTextFile?.text.toString()
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE)
            fos.write(text.toByteArray())
            Toast.makeText(this, resources.getString(R.string.fileIsSaved), Toast.LENGTH_SHORT)
                .show()

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
            editTextFile?.setText(text, TextView.BufferType.EDITABLE);
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

    override fun onDestroy() {
        super.onDestroy()
        
        editTextFile = null
    }
}