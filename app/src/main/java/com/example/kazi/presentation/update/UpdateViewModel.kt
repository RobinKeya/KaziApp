package com.example.kazi.presentation.update

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kazi.data.WorkRepository
import com.example.kazi.data.local.PartialWork
import com.example.kazi.data.local.Work
import com.example.kazi.di.MainDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: WorkRepository,
    @MainDispatcher private val dispatcher: CoroutineDispatcher
): ViewModel() {
    private val _work = mutableStateOf(Work(0,"",""))
    val work get() = _work


    var title by mutableStateOf("${work.value.title}")
    var description by mutableStateOf("${work.value.description}")

    init {
        val id = savedStateHandle.get<Int?>("id")?:0
        viewModelScope.launch(dispatcher) {
            val work = repository.getSingleWork(id)
            _work.value = _work.value.copy(
                id= work.id,
                title = work.title,
                description = work.description
            )
            title = work.title
            description = work.description
        }
    }
    fun updateWork(work: PartialWork)= viewModelScope.launch(dispatcher) {
        repository.updateWork(work)
    }
}