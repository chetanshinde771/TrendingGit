package com.csapps.trendinggit.util

sealed class Resource<T>(val data: T? = null, val msg: String? = null){

    class Success<T>(data: T): Resource<T>(data, null)

    class Error<T>(message: String): Resource<T>(null, message)

    class Loading<T>(): Resource<T>()
}
