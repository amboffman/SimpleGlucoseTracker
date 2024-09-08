package com.example.simpleglucosetracker.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class DataUtils {
    @RequiresApi(Build.VERSION_CODES.O)
    fun formatInstant(instant: Instant): String {
        val zoneId = ZoneId.systemDefault()
        val localDateTime = LocalDateTime.ofInstant(instant, zoneId)
        val formatter = DateTimeFormatter.ofPattern("MM-dd-YYYY hh:mm a")
            return localDateTime.format(formatter)
    }
    fun testFunction (){

    }
}