package com.example.kazi.data

import com.example.kazi.data.local.PartialWork
import com.example.kazi.data.local.Work
import com.example.kazi.data.local.WorkDao
import com.example.kazi.di.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WorkRepository @Inject constructor(
    @IODispatcher private val dispatcher: CoroutineDispatcher,
    private val dao: WorkDao
) {
    fun getAllWorks(): Flow<List<Work>>{
       return dao.getWork()
    }
    suspend fun addWork(work: Work){
        withContext(dispatcher){
            dao.addWork(work)
        }
    }

    suspend fun deleteAll(){
        withContext(dispatcher){
            dao.deleteAll()
        }
    }

    suspend fun deleteWork(id: Int){
        withContext(dispatcher){
            dao.deleteWork(id)
        }
    }
    suspend fun updateWork(work: PartialWork){
        withContext(dispatcher){
            dao.update(work)
        }
    }
    suspend fun getSingleWork(id: Int): Work{
        return withContext(dispatcher){
            return@withContext dao.getSingleWork(id)
        }
    }
}