package com.example.kazi.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "work")
data class Work  constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description : String,
    val date: LocalDateTime? = LocalDateTime.now()
)
