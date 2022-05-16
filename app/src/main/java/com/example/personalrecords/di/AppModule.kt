package com.example.personalrecords.di

import com.example.personalrecords.service.repository.remote.PersonService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule  {

    const val baseUrl = "http://devmasterteam.com/CursoAndroidAPI/"

    @Singleton
    @Provides
    fun providesPersonService(retrofit: Retrofit): PersonService {
        return retrofit.create(PersonService::class.java)
    }

    @Singleton
    @Provides
    fun getRetrofitInstance(): Retrofit {
        val httpClient = OkHttpClient.Builder()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}