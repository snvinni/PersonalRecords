package com.example.personalrecords.di

import android.app.Application
import android.content.Context
import com.example.personalrecords.service.repository.local.RecordDAO
import com.example.personalrecords.service.repository.local.RecordDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RecordModule {

    @Singleton
    @Provides
    fun getRecordDataBase(context: Application): RecordDataBase {
        return RecordDataBase.getDataBase(context)

    }

    @Singleton
    @Provides
    fun getDao(recordDataBase: RecordDataBase): RecordDAO {
        return recordDataBase.recordDAO()

    }
}