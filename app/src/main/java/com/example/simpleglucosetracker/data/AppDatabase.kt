package com.example.simpleglucosetracker.data
import com.example.simpleglucosetracker.data.Converters
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.simpleglucosetracker.data.GlucoseReadingDao
import com.example.simpleglucosetracker.model.GlucoseReading

@Database(entities = [GlucoseReading::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun glucoseReadingDao(): GlucoseReadingDao
}