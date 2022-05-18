package com.example.personalrecords.service.repository.local

import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.personalrecords.model.RecordModel


@Dao
interface RecordDAO {

    @Insert
    fun save(record: RecordModel) : Long

    @Update
    fun convertRecords(record: List<RecordModel>)

    @Update
    fun update(record: RecordModel) : Int

    @Delete
    fun delete(record: RecordModel)

    @Query("DELETE FROM Record")
    fun resetRecord()

    @Query("SELECT * FROM record ")
    fun getExerciseRecord(): List<RecordModel>

    @Query("SELECT * FROM Record WHERE id = :id")
    fun get(id: Int) : RecordModel

    @Query("SELECT * FROM Record")
    fun getRecord(): List<RecordModel>

}