package com.mustaq.coroutines.repository

import com.mustaq.coroutines.helper.ApiHelper


class CoroutinesRepository(private val apiHelper: ApiHelper) {

    suspend fun getUsers() = apiHelper.getUsers()

}