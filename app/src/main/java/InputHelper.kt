package com.example.simpleglucosetracker

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner


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