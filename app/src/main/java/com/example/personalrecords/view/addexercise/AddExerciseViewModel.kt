package com.example.personalrecords.view.addexercise

import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.personalrecords.model.RecordModel
import com.example.personalrecords.service.repository.local.RecordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddExerciseViewModel @Inject constructor(private val recordRepository: RecordRepository) :
    ViewModel() {

    var saveRecord = MutableLiveData<Boolean>()
    var record = MutableLiveData<RecordModel>()

    fun save(
        id: Int,
        exerciseName: String,
        exerciseRecord: Int,
        haveRepetitions: String,
        date: String,
        measurement: String,
        repetition: String,
    ) {
            val record = RecordModel().apply {
                this.id = id
                this.exerciseName = exerciseName
                this.exerciseRecord = exerciseRecord
                this.haveRepetitions = haveRepetitions
                this.date = date
                this.measurement = measurement
                this.repetition = repetition

            }

            if (id == 0 && exerciseName != "" && exerciseRecord != 0) {
                saveRecord.value = recordRepository.save(record)
            } else {
                saveRecord.value = recordRepository.update(record)
            }
    }

    fun load(id: Int) {
        record.value = recordRepository.get(id)
    }

}