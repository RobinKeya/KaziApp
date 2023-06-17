package com.example.kazi.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
@Dao
interface WorkDao {
    @Query("SELECT * FROM work")
    fun getWork():Flow<List<Work>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addWork(work: Work)
}