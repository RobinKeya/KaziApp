package com.example.kazi.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow
@Dao
interface WorkDao {
    @Query("SELECT * FROM work")
    fun getWork():Flow<List<Work>>

    @Query("SELECT * FROM work WHERE id = :id")
    suspend fun getSingleWork(id: Int): Work
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addWork(work: Work)
    @Query("DELETE FROM work WHERE id = :id")
    suspend fun deleteWork(id: Int)

    @Query("DELETE FROM work")
    suspend fun deleteAll()

    @Update(Work::class)
    suspend fun update(work: PartialWork)
}