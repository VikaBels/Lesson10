package com.example.lesson10_k

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class MainActivity : AppCompatActivity() {
    companion object {
        const val FILE_NAME = "data.txt"
    }

    private var btnShowFile: Button? = null
    private var btnWorkWithFile: Button? = null
    private var btnSettings: Button? = null

    private var fileExist: Boolean = true
    private lateinit var pathFile: String

    private fun findViewsById() {
        btnShowFile = findViewById(R.id.btnShowFile)
        btnWorkWithFile = findViewById(R.id.btnWorkWithFile)
        btnSettings = findViewById(R.id.btnSettings)
    }

    private fun setOnClickListener(allButton: View.OnClickListener?) {
        btnShowFile?.setOnClickListener(allButton)
        btnWorkWithFile?.setOnClickListener(allButton)
        btnSettings?.setOnClickListener(allButton)
    }

    private fun showTwoOrThreeButtons() {
        if (File(getFileStreamPath(FILE_NAME).toString()).exists()) {
            btnWorkWithFile?.text = resources.getString(R.string.editFile)
            fileExist = true
            btnShowFile?.visibility = View.VISIBLE
        } else {
            btnShowFile?.visibility = View.GONE
            btnWorkWithFile?.text = resources.getString(R.string.addFile)
            fileExist = false
        }
    }

    override fun onStart() {
        super.onStart()
        showTwoOrThreeButtons()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewsById()

        val allButton = View.OnClickListener { v ->
            when (v.id) {
                R.id.btnShowFile -> {
                    val intent = Intent(this, ShowFileActivity::class.java)
                    startActivity(intent)
                }

                R.id.btnWorkWithFile -> {
                    if (!fileExist) {
                        File(pathFile).createNewFile()
                    }

                    val intent = Intent(this, EditFIleActivity::class.java)
                    startActivity(intent)
                }

                R.id.btnSettings -> {
                    val intent = Intent(this, SettingsActivity::class.java)
                    startActivity(intent)
                }
            }
        }
        setOnClickListener(allButton)
    }

    override fun onDestroy() {
        super.onDestroy()

        btnShowFile = null
        btnWorkWithFile = null
        btnSettings = null
    }
}