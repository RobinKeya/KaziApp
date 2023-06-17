package com.example.kazi.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.kazi.core.utils.Converters

@Database(entities = [Work::class], version = 2)
@TypeConverters(Converters::class)
abstract class WorkDb : RoomDatabase() {
    abstract val workDao : WorkDao
}