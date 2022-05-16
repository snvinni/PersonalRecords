package com.example.personalrecords.view.settings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.personalrecords.model.RecordModel
import com.example.personalrecords.service.constants.RecordConstants
import com.example.personalrecords.service.repository.local.RecordRepository
import com.example.personalrecords.service.repository.local.SecurityPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val recordRepository: RecordRepository,
    private val sharedPreferences: SecurityPreferences,
) :
    ViewModel() {

    var saveRecord = MutableLiveData<String>()
    var record = MutableLiveData<RecordModel>()
    var logOut = MutableLiveData<Boolean>()

    fun convertLbsToKgs(
        recordKgs: Boolean
    ) {
        var exerciseRecord = 0
        if (recordKgs) {
            exerciseRecord += 1
        }
        val record = RecordModel().apply {
            this.exerciseRecord = exerciseRecord
        }
            saveRecord.value = recordRepository.save(record).toString()

    }

    fun logOut() {
            sharedPreferences.remove(RecordConstants.SHARED.PERSON_KEY)
            sharedPreferences.remove(RecordConstants.SHARED.TOKEN_KEY)
            sharedPreferences.remove(RecordConstants.SHARED.PERSON_NAME)
            recordRepository.resetRecords()

            logOut.value = true
        }
    }