package com.example.kazi.core.utils

import androidx.room.TypeConverter
import java.time.LocalDateTime

class Converters {
    @TypeConverter
    fun fromStringToTimeStamp(value: String?): LocalDateTime?{
        return value?.let {
            LocalDateTime.parse(it)
        }
    }

    @TypeConverter
    fun fromTimeStampToString(time: LocalDateTime?): String?{
        return time?.toString()
    }
}