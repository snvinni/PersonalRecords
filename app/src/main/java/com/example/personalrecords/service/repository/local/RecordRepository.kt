package com.example.personalrecords.service.repository.local

import androidx.lifecycle.MutableLiveData
import com.example.personalrecords.model.RecordModel
import javax.inject.Inject

class RecordRepository @Inject constructor (
    private val securityPreferences: SecurityPreferences,
    private val recordDAO: RecordDAO) {

    fun get(id: Int): RecordModel {
        return recordDAO.get(id)
    }
    fun save(record: RecordModel): Boolean {
        return recordDAO.save(record) > 0
    }
    fun delete(record: RecordModel) {
        return recordDAO.delete(record)
    }
    fun update(record: RecordModel) : Boolean {
        return recordDAO.update(record) > 0
    }
    fun getRecord(): List<RecordModel> {
        return recordDAO.getRecord().map { record ->
            if (!getExerciseRecord()) {
                record.exerciseRecord /= 2
            }
            record
        }
    }
    fun resetRecords() {
        return recordDAO.resetRecord()
    }
    fun convertRecords(record: List<RecordModel>) {
        return recordDAO.convertRecords(record)
    }
    fun getExerciseRecord(): Boolean {
        return securityPreferences.getRecordType()
    }
    fun setRecordType(isLbs: Boolean) {
        securityPreferences.setRecordType(isLbs)
    }
}