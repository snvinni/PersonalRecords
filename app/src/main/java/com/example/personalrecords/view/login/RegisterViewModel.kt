package com.example.personalrecords.view.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.personalrecords.service.constants.RecordConstants
import com.example.personalrecords.service.listener.ValidationListener
import com.example.personalrecords.service.repository.PersonRepository
import com.example.personalrecords.service.repository.ResultResgister
import com.example.personalrecords.service.repository.local.SecurityPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val personRepository: PersonRepository,
    private val sharedPreferences: SecurityPreferences
) : ViewModel() {

    var create = MutableLiveData<ValidationListener>()

    fun doRegister(name: String, email: String, password: String) {
            viewModelScope.launch {
                when (val response = personRepository.create(name, email, password)) {
                    is ResultResgister.Error -> {
                        create.value = ValidationListener("Ocorreu um erro inesperado!")
                    }
                    is ResultResgister.Success -> {
                        val model = response.data
                        sharedPreferences.store(RecordConstants.SHARED.TOKEN_KEY, model.token)
                        sharedPreferences.store(RecordConstants.SHARED.PERSON_KEY, model.personKey)
                        sharedPreferences.store(RecordConstants.SHARED.PERSON_NAME, model.name)

                        create.value = ValidationListener()
                    }
                }
            }
        }
    }
