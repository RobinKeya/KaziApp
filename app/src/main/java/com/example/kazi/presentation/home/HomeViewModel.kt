package com.example.kazi.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kazi.data.WorkRepository
import com.example.kazi.data.local.PartialWork
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
    private val _work = mutableStateOf(Work(0,"",""))
    val work get() = _work
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

    fun deleteAll() = viewModelScope.launch(dispatcher) {
        repository.deleteAll()
    }

    fun updateWork(work: PartialWork)= viewModelScope.launch(dispatcher) {
        repository.updateWork(work)
    }
    fun deleteWork(id: Int)= viewModelScope.launch(dispatcher) {
        repository.deleteWork(id)
    }
    fun getSingleWork(id: Int) = viewModelScope.launch (dispatcher){
        val singleWork = repository.getSingleWork(id)
        _work.value = _work.value.copy(
            id= singleWork.id,
            title = singleWork.title,
            description = singleWork.description
        )
    }
}