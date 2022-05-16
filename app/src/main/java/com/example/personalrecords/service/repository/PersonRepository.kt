package com.example.personalrecords.service.repository

import com.example.personalrecords.service.HeaderModel
import com.example.personalrecords.service.repository.remote.PersonService
import javax.inject.Inject

class PersonRepository @Inject constructor(private val remote: PersonService) {

    suspend fun login(email: String, password: String): ResultLogin<HeaderModel> {

        return try {
            val call: HeaderModel = remote.login(email, password)
            ResultLogin.Success(call)

        } catch (exception: Exception) {
            ResultLogin.Error(exception)
        }
    }
    suspend fun create(name: String, email: String, password: String): ResultResgister<HeaderModel> {

        return try {
            val call: HeaderModel = remote.create(name, email, password)
            ResultResgister.Success(call)

        } catch (exception: Exception) {
            ResultResgister.Error(exception)
        }
    }

}