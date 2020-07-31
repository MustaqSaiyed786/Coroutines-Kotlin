package com.mustaq.coroutines.helper

import com.mustaq.coroutines.services.ApiService

class ApiHelper(private val apiService: ApiService) {

    suspend fun getUsers() = apiService.getUsers()

}