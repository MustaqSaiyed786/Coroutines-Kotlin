package com.mustaq.coroutines.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mustaq.coroutines.repository.CoroutinesRepository
import com.mustaq.coroutines.resource.Resource
import kotlinx.coroutines.Dispatchers

class CoroutinesViewModel(private val mainRepository: CoroutinesRepository) : ViewModel() {

    fun getUsers() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getUsers()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}
