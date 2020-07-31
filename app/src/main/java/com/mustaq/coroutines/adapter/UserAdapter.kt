package com.mustaq.coroutines.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mustaq.coroutines.R
import com.mustaq.coroutines.model.CoroutinesModel
import kotlinx.android.synthetic.main.item_layout.view.*


class UserAdapter(private val userCoroutinesModels: ArrayList<CoroutinesModel>) :
    RecyclerView.Adapter<UserAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(userCouroutinModel: CoroutinesModel) {

            itemView.apply {
                tvUserId.text = userCouroutinModel.id
                tvTitle.text = userCouroutinModel.title
                tvBody.text = userCouroutinModel.body
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        )

    override fun getItemCount(): Int = userCoroutinesModels.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(userCoroutinesModels[position])
    }

    fun addUsers(userCouroutinModels: List<CoroutinesModel>) {
        this.userCoroutinesModels.apply {
            clear()
            addAll(userCouroutinModels)
        }

    }
}