package com.example.personalrecords.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Record")
data class RecordModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "exerciseName")
    var exerciseName: String = "",

    @ColumnInfo(name = "exerciseRecord")
    var exerciseRecord: Int = 0,

    @ColumnInfo(name = "haveRepetitions")
    var haveRepetitions: String = "",

    @ColumnInfo(name = "date")
    var date: String = "",

    @ColumnInfo(name = "measurement")
    var measurement: String = "",

    @ColumnInfo(name = "repetition")
    var repetition: String = ""
)
