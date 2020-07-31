package com.mustaq.coroutines.services


import com.mustaq.coroutines.model.CoroutinesModel
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getUsers(): List<CoroutinesModel>

}