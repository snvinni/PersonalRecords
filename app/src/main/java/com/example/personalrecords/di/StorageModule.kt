package com.example.personalrecords.di

import android.content.Context
import com.example.personalrecords.service.repository.local.SecurityPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    @Provides
    @Singleton
    fun providesSecurityPreferences(@ApplicationContext context: Context): SecurityPreferences {
        return  SecurityPreferences(context)

    }

}