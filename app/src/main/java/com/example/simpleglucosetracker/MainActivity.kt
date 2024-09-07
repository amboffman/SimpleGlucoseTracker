package com.example.simpleglucosetracker

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.simpleglucosetracker.data.AppDatabase
import com.example.simpleglucosetracker.data.GlucoseReadingDao
import com.example.simpleglucosetracker.model.GlucoseReading
import com.example.simpleglucosetracker.utils.DatabaseHelper
import com.example.simpleglucosetracker.utils.InputHelper
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant

class MainActivity : AppCompatActivity() {
    private lateinit var editGlucoseValue: TextInputEditText
    private lateinit var glucoseUnitsDropdown: Spinner
    private lateinit var readingSubmissionButton: Button
    private lateinit var database: AppDatabase
    private lateinit var glucoseReadingDao: GlucoseReadingDao


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editGlucoseValue = findViewById(R.id.glucoseValueInput)
        glucoseUnitsDropdown = findViewById(R.id.glucoseUnitsDropdown)
        readingSubmissionButton = findViewById(R.id.submitGlucoseLevel)
        database = DatabaseHelper.getDatabase(this)
        glucoseReadingDao = database.glucoseReadingDao()


        val inputHelper = InputHelper()
        inputHelper.setupGlucoseUnitsDropdown(glucoseUnitsDropdown, this)

        readingSubmissionButton.setOnClickListener{
            val glucoseValueText = editGlucoseValue.text.toString()
            val selectedUnits = glucoseUnitsDropdown.selectedItem.toString()
            if (glucoseValueText.isBlank()) {
                Toast.makeText(this, "Please enter a glucose value", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val glucoseValue = glucoseValueText.toDoubleOrNull()
            if (glucoseValue == null || glucoseValue < 0) {
                Toast.makeText(this, "Invalid glucose value", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val reading = GlucoseReading(
                value = glucoseValue,
                units = selectedUnits,
                timestamp = Instant.now()
            )
            CoroutineScope(Dispatchers.IO).launch {
                glucoseReadingDao.insert(reading)
            }
            Toast.makeText(this, "Glucose reading saved!", Toast.LENGTH_SHORT).show()

            editGlucoseValue.text?.clear()

        }




    }
}