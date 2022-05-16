package com.example.personalrecords.service.listener

import com.example.personalrecords.service.HeaderModel

interface APIListener {

    fun onSuccess(model: HeaderModel)
    fun onFailure(str: String)

}