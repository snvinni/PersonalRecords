package com.example.personalrecords.view.login


import androidx.lifecycle.*
import com.example.personalrecords.service.constants.RecordConstants
import com.example.personalrecords.service.repository.PersonRepository
import com.example.personalrecords.service.listener.ValidationListener
import com.example.personalrecords.service.repository.ResultLogin
import com.example.personalrecords.service.repository.local.SecurityPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val personRepository: PersonRepository,
    private val sharedPreferences: SecurityPreferences
) : ViewModel() {

    var login = MutableLiveData<ValidationListener>()
    var loggedUser = MutableLiveData<Boolean>()

    fun doLogin(email: String, password: String) {
        viewModelScope.launch {
            when (val response = personRepository.login(email, password)) {
                is ResultLogin.Error -> {
                    login.value = ValidationListener("Ocorreu um erro inesperado!")
                }
                is ResultLogin.Success -> {
                    val model = response.data
                    sharedPreferences.store(RecordConstants.SHARED.TOKEN_KEY, model.token)
                    sharedPreferences.store(RecordConstants.SHARED.PERSON_KEY, model.personKey)
                    sharedPreferences.store(RecordConstants.SHARED.PERSON_NAME, model.name)

                    login.value = ValidationListener()
                }
            }
        }
    }

    fun verifyLoggedUser() {
        val name = sharedPreferences.get(RecordConstants.SHARED.PERSON_NAME)
        val token = sharedPreferences.get(RecordConstants.SHARED.TOKEN_KEY)

        loggedUser.value = (token != "" && name != "")
    }

}