package com.example.kazi.presentation.home

import com.example.kazi.data.local.Work
import kotlinx.coroutines.flow.Flow


data class HomeScreenState(
    val allWork : Flow<List<Work>>,
    val isLoading : Boolean,
    val error: String? =null
)
