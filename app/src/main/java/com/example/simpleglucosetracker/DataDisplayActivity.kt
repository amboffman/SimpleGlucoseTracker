package com.example.simpleglucosetracker

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.simpleglucosetracker.data.AppDatabase
import com.example.simpleglucosetracker.data.GlucoseReadingDao
import com.example.simpleglucosetracker.model.GlucoseReading
import com.example.simpleglucosetracker.utils.DatabaseHelper
import com.example.simpleglucosetracker.utils.DataUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DataDisplayActivity : AppCompatActivity() {

    private lateinit var readingsDisplay: TextView
    private lateinit var database: AppDatabase
    private lateinit var glucoseReadingDao: GlucoseReadingDao
    private val dataUtils = DataUtils()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_data_display)

        readingsDisplay = findViewById(R.id.textViewDataLog)
        database = DatabaseHelper.getDatabase(this)
        glucoseReadingDao = database.glucoseReadingDao()

        lifecycleScope.launch(Dispatchers.IO){
            val allReadings = glucoseReadingDao.getAll()
            displayAllReadings(allReadings)
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    suspend fun displayAllReadings(readings: List<GlucoseReading>){
        val formattedReading = readings.joinToString("\n") { reading->
        val formattedTimestamp = dataUtils.formatInstant(reading.timestamp)
            "Value: ${reading.value} ${reading.units}, Timestamp: ${formattedTimestamp}"
        }
        withContext(Dispatchers.Main) {
            readingsDisplay.text = formattedReading
        }
    }
}