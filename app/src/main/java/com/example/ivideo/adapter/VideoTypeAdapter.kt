package com.example.ivideo.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ivideo.R
import com.example.ivideo.databinding.ItemTypeBinding
import com.example.ivideo.entity.ChannelId

class VideoTypeAdapter(val context: Context?, var data:List<ChannelId>,val clickListener: ClickListener): RecyclerView.Adapter<VideoTypeAdapter.MyVideHolder>() {

    lateinit var binding:ItemTypeBinding
    inner class MyVideHolder(view:View):RecyclerView.ViewHolder(view){
        val typeName = binding.tvType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVideHolder {
        binding = ItemTypeBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyVideHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MyVideHolder, position: Int) {
        val type = data.get(position)
        holder.typeName.text = type.typename
        val textC = type.textC
        if (textC){
            holder.typeName.setTextColor(context!!.resources.getColor(R.color.white))
        }else{
            holder.typeName.setTextColor(context!!.resources.getColor(R.color.grey))
        }

        holder.itemView.setOnClickListener {
            clickListener.onClick(position,type)
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface ClickListener{
        fun onClick(position: Int,entity:ChannelId)
    }

}