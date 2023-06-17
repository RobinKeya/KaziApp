package com.example.kazi.di

import android.content.Context
import androidx.room.Room
import com.example.kazi.data.local.WorkDao
import com.example.kazi.data.local.WorkDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideRoomDb(@ApplicationContext context: Context): WorkDb {
        return Room.databaseBuilder(
            context,
            WorkDb::class.java,
            "WorkDb"
        ).fallbackToDestructiveMigration()
            .build()
    }
    @Provides
    fun provideWorkDao(workDb: WorkDb): WorkDao{
        return workDb.workDao
    }
}