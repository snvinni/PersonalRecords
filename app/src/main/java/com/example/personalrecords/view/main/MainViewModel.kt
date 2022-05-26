package com.example.personalrecords.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.personalrecords.model.RecordModel
import com.example.personalrecords.service.repository.local.RecordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val recordRepository: RecordRepository): ViewModel() {

    val recordList = MutableLiveData<List<RecordModel>>()

    init {
        load()
    }

    fun load() {
        recordRepository.getExerciseRecords().onEach {
            recordList.value = it
        }.launchIn(viewModelScope)
    }

    fun delete(id: Int) {
        val record = recordRepository.get(id)
        recordRepository.delete(record)
    }

}