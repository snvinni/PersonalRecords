package com.example.personalrecords.service.repository

sealed class ResultLogin<T> {
    data class Success<T>(val data: T) : ResultLogin<T>()
    data class Error<T>(val throwable: Throwable) : ResultLogin<T>() {
        companion object {
            const val GENERIC_MESSAGE = "Something went wrong"
        }
    }
}
sealed class ResultResgister<T> {
    data class Success<T>(val data: T) : ResultResgister<T>()
    data class Error<T>(val throwable: Throwable) : ResultResgister<T>() {
        companion object {
            const val GENERIC_MESSAGE = "Something went wrong"
        }
    }
}