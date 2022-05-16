package com.example.personalrecords.service.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.personalrecords.model.RecordModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [RecordModel::class], version = 1, exportSchema = false)
abstract class RecordDataBase : RoomDatabase() {

    abstract fun recordDAO(): RecordDAO

    companion object {

        private lateinit var INSTANCE: RecordDataBase

        @OptIn(InternalCoroutinesApi::class)
        fun getDataBase(context: Context): RecordDataBase {

            if (!::INSTANCE.isInitialized) {
                synchronized(RecordDataBase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context, RecordDataBase::class.java,
                        "RecordDB"
                    )
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

    }

}