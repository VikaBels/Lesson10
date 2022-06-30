package com.example.lesson10_k

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lesson10_k.MainActivity.Companion.FILE_NAME
import java.io.*

class EditFIleActivity : AppCompatActivity() {
    private var editTextFile: EditText? = null

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
        try {
            openFileOutput(FILE_NAME, MODE_PRIVATE).use { fos ->
                val text = editTextFile?.text.toString()
                fos.write(text.toByteArray())
                Toast.makeText(this, resources.getString(R.string.fileIsSaved), Toast.LENGTH_SHORT)
                    .show()
            }
        } catch (noFile: FileNotFoundException) {
            noFile.printStackTrace()
        }
    }


    private fun openText() {
        try {
            openFileInput(FILE_NAME).use { fin ->
                val bytes = ByteArray(fin.available())
                fin.read(bytes)
                val text = String(bytes)
                editTextFile?.setText(text)
            }
        } catch (noFile: FileNotFoundException) {
            noFile.printStackTrace()
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