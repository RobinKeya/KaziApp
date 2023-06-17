package com.example.kazi.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kazi.data.WorkRepository
import com.example.kazi.data.local.Work
import com.example.kazi.di.MainDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(
    @MainDispatcher private val dispatcher: CoroutineDispatcher,
    private val repository: WorkRepository
) : ViewModel() {
    private val _state = mutableStateOf(
        HomeScreenState(
           allWork =  emptyFlow(),
           isLoading =  true
        )
    )
    val state get()=_state
    init {
        viewModelScope.launch(dispatcher) {
           val work = getAllWork()
           _state.value = _state.value.copy(
               allWork = work,
           )
        }
    }
    private fun getAllWork(): Flow<List<Work>>{
        return repository.getAllWorks()
    }

     fun addWork(work: Work) = viewModelScope.launch(dispatcher) {
         repository.addWork(work = work)
     }
}