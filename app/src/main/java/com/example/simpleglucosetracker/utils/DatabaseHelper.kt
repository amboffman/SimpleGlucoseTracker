package com.example.simpleglucosetracker.utils

import android.content.Context
import androidx.room.Room
import com.example.simpleglucosetracker.data.AppDatabase

object DatabaseHelper {
    private var INSTANCE: AppDatabase? = null
    fun getDatabase(context: Context): AppDatabase {
        if(INSTANCE == null){
            synchronized(AppDatabase::class.java)
            {
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "glucose_readings_database"
                    ).build()
                }
            }
        }
    return INSTANCE!!
    }
}