package com.example.simpleglucosetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val glucoseUnitsDropdown: Spinner = findViewById(R.id.glucoseUnitsDropdown)
        ArrayAdapter.createFromResource(
            this, // Context
            R.array.glucose_units,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            glucoseUnitsDropdown.adapter = adapter
        }
        setContentView(R.layout.activity_main)
    }
}