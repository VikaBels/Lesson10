package com.example.lesson10_k

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var btnShowFile: Button
    private lateinit var btnWorkWithFile: Button
    private lateinit var btnSettings: Button

    private var txtEditFile: String = "Редактировать файл"
    private var txtCreateFile: String = "Создать файл"

    private fun findViewById() {
        btnShowFile = findViewById(R.id.btnShowFile)
        btnWorkWithFile = findViewById(R.id.btnWorkWithFile)
        btnSettings = findViewById(R.id.btnSettings)
    }

    private fun setOnClickListener(allButton: View.OnClickListener?) {
        btnShowFile.setOnClickListener(allButton)
        btnWorkWithFile.setOnClickListener(allButton)
        btnSettings.setOnClickListener(allButton)
    }

    private fun showToOrThreeButtons() {
        val fileExist: Boolean = true

        if (fileExist) {
            btnWorkWithFile.text = txtEditFile
        } else {
            btnShowFile.visibility = View.GONE;
            btnWorkWithFile.text = txtCreateFile
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById()

        showToOrThreeButtons()

        val allButton = View.OnClickListener { v ->
            when (v.id) {
                R.id.btnShowFile -> {
                    val intent = Intent(this, ShowFileActivity::class.java)
                    startActivity(intent)
                }

                R.id.btnWorkWithFile -> {
                    val intent = Intent(this, WorkWithFIleActivity::class.java)
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

    //?????
    override fun onDestroy() {
        super.onDestroy()
    }
}