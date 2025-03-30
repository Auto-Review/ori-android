package com.example.presentation.til.create.model

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import java.time.LocalDate
import java.time.Year
import java.time.ZoneOffset

@OptIn(ExperimentalMaterial3Api::class)
internal  class SelectableDates: SelectableDates {
    override fun isSelectableDate(utcTimeMillis: Long): Boolean {
        return LocalDate.now().atStartOfDay().toInstant(ZoneOffset.UTC).epochSecond * 1000 <= utcTimeMillis
    }

    override fun isSelectableYear(year: Int): Boolean {
        return Year.now().value <= year
    }
}
