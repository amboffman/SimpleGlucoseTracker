package com.example.simpleglucosetracker.model

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant

@Entity
data class GlucoseReading @RequiresApi(Build.VERSION_CODES.O) constructor(
    @PrimaryKey(autoGenerate = true) val id: Int= 0,
    @ColumnInfo(name = "value") val value: Double,
    @ColumnInfo(name = "units") val units: String,
    @ColumnInfo(name = "timestamp") val timestamp: Instant = Instant.now()
)