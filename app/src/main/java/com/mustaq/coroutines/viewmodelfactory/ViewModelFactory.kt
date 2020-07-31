package com.mustaq.coroutines.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mustaq.coroutines.helper.ApiHelper
import com.mustaq.coroutines.repository.CoroutinesRepository
import com.mustaq.coroutines.viewmodel.CoroutinesViewModel




class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CoroutinesViewModel::class.java)) {
            return CoroutinesViewModel(
                CoroutinesRepository(apiHelper)
            ) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}