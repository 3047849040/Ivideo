package com.example.ivideo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.ivideo.databinding.ItemSvideoBinding
import com.example.ivideo.entity.ShortVideo
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer

class ShortVideoAdapter(var context:Context?, var data:List<ShortVideo>): RecyclerView.Adapter<ShortVideoAdapter.MyVideoHolder>() {

    lateinit var gsy:StandardGSYVideoPlayer
    lateinit var binding:ItemSvideoBinding

    inner class MyVideoHolder(view:View):RecyclerView.ViewHolder(view){
        val avatarUrl = binding.ivAvatarUrl
        val aname = binding.tvName
        val title = binding.tvTitle
        val ctime = binding.tvCtime
        val gsy = binding.gsy
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVideoHolder {
        binding = ItemSvideoBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyVideoHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MyVideoHolder, position: Int) {
        val video = data.get(position)
        holder.aname.text = video.name
        Glide.with(context!!).load(video.avatar_url)
            .apply(RequestOptions().circleCrop()).into(holder.avatarUrl)
        holder.title.text = video.title
        holder.ctime.text = video.ctime
        gsy = holder.gsy
        gsy.setUp(video.videopath,false,"")
        gsy.backButton.visibility =View.GONE

    }

    fun getViewByPosition():View{
        return gsy
    }

    override fun getItemCount(): Int {
        return data.size
    }

}