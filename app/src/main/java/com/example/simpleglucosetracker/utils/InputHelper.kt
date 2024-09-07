package com.example.simpleglucosetracker.utils

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.simpleglucosetracker.R


class InputHelper {

    fun setupGlucoseUnitsDropdown(spinner: Spinner, context: Context) {
        ArrayAdapter.createFromResource(
            context,
            R.array.glucose_units,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter

        }
    }
}