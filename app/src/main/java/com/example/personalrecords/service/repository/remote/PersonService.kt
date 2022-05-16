package com.example.personalrecords.service.repository.remote

import com.example.personalrecords.service.HeaderModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface PersonService {

    @POST("Authentication/Login")
    @FormUrlEncoded
    suspend fun login(
        @Field("email")email: String,
        @Field("password")password: String,
    ) : HeaderModel

    @POST("Authentication/Create")
    @FormUrlEncoded
    suspend fun create(
        @Field("name")name: String,
        @Field("email")email: String,
        @Field("password")password: String,
    ) : HeaderModel

}