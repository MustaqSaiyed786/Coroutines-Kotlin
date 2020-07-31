package com.mustaq.coroutines.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mustaq.coroutines.R
import com.mustaq.coroutines.adapter.UserAdapter
import com.mustaq.coroutines.enum.Status
import com.mustaq.coroutines.helper.ApiHelper
import com.mustaq.coroutines.model.CoroutinesModel
import com.mustaq.coroutines.network.RetrofitClient
import com.mustaq.coroutines.viewmodel.CoroutinesViewModel
import com.mustaq.coroutines.viewmodelfactory.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var coroutinesViewModel: CoroutinesViewModel
    private lateinit var adapter: UserAdapter
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var process: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setupUI()
        setupObservers()
    }

    private fun setupViewModel() {
        coroutinesViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                ApiHelper(
                    RetrofitClient.apiService
                )
            )
        ).get(CoroutinesViewModel::class.java)

    }

    private fun setupUI() {

        mRecyclerView = findViewById(R.id.recyclerView)
        process = findViewById(R.id.progressBar)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = UserAdapter(arrayListOf())
        mRecyclerView.addItemDecoration(
            DividerItemDecoration(
                mRecyclerView.context,
                (mRecyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        mRecyclerView.adapter = adapter
    }

    private fun setupObservers() {
        coroutinesViewModel.getUsers().observe(this, Observer {
            it?.let { response ->
                when (response.status) {
                    Status.SUCCESS -> {
                        Log.e(TAG, "You enter the ${Status.SUCCESS}")
                        mRecyclerView.visibility = View.VISIBLE
                        process.visibility = View.GONE
                        response.data?.let { user -> readList(user) }
                    }
                    Status.LOADING -> {
                        Log.e(TAG, "You enter the ${Status.LOADING}")
                        process.visibility = View.VISIBLE
                    }
                    Status.ERROR -> {
                        Log.e(TAG, "You enter the ${Status.ERROR}")
                        mRecyclerView.visibility = View.GONE
                        process.visibility = View.GONE
                    }
                }
            }
        })

    }

    private fun readList(user: List<CoroutinesModel>) {
        adapter.apply {
            addUsers(user)
            notifyDataSetChanged()
        }
    }

    companion object {
        const val TAG = "MainActivity"
    }
}
