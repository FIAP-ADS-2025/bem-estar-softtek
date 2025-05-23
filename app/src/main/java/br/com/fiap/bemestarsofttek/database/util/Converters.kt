package br.com.fiap.bemestarsofttek.database.util

import androidx.room.TypeConverter
import java.time.LocalDate

class Converters {
    @TypeConverter
    fun fromStringToLocalDate(value: String): LocalDate {
        return LocalDate.parse(value)
    }

    @TypeConverter
    fun fromLocalDateToString(date: LocalDate): String {
        return date.toString()
    }
}
