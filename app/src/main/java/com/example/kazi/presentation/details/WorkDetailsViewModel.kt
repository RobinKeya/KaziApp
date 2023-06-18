package com.example.kazi.presentation.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kazi.data.WorkRepository
import com.example.kazi.data.local.Work
import com.example.kazi.di.MainDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
@HiltViewModel
class WorkDetailsViewModel @Inject constructor(
    @MainDispatcher private val dispatcher: CoroutineDispatcher,
    private val repository: WorkRepository,
    stateHandle : SavedStateHandle
) : ViewModel(){
    private val _state = mutableStateOf(Work(0,"",""))
    val state get() = _state
    init {
        val id = stateHandle.get<Int?>("id")?:0
        viewModelScope.launch(dispatcher) {
            val work = getSingleCharacter(id)
            _state.value = _state.value.copy(
                title = work.title,
                description = work.description,
                id = work.id
            )
        }

    }
    suspend fun getSingleCharacter(id: Int): Work {
        return repository.getSingleWork(id)
    }
}