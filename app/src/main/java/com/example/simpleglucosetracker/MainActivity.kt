package com.example.simpleglucosetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Spinner

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputHelper = InputHelper()

        val glucoseUnitsDropdown: Spinner = findViewById(R.id.glucoseUnitsDropdown)
        inputHelper.setupGlucoseUnitsDropdown(glucoseUnitsDropdown, this)

    }
}