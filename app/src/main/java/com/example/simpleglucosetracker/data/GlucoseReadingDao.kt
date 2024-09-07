package com.example.simpleglucosetracker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.simpleglucosetracker.model.GlucoseReading
import java.util.Date

@Dao
interface GlucoseReadingDao{
    @Insert
    fun insert(glucoseReading: GlucoseReading)

    @Query("SELECT * FROM GlucoseReading")
    fun getAll(): List<GlucoseReading>

    @Query("SELECT * FROM GlucoseReading ORDER BY timestamp DESC LIMIT 15")
    fun getLatestReadings(): GlucoseReading?

    @Query("SELECT * FROM GlucoseReading WHERE date(timestamp) = :date")
    fun getReadingsByDate(date: String): List<GlucoseReading>

}
