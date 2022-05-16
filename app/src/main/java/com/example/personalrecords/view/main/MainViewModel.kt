package com.example.personalrecords.view.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.personalrecords.model.RecordModel
import com.example.personalrecords.service.repository.local.RecordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val recordRepository: RecordRepository): ViewModel() {

    val recordList = MutableLiveData<List<RecordModel>>()

    fun load() {
        recordList.value = recordRepository.getRecord()
    }

    fun delete(id: Int) {
        val record = recordRepository.get(id)
        recordRepository.delete(record)
    }

}