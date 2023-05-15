package com.example.ivideo.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.ivideo.R
import com.example.ivideo.adapter.ShortVideoAdapter
import com.example.ivideo.adapter.VideoTypeAdapter
import com.example.ivideo.databinding.FragmentOneBinding
import com.example.ivideo.entity.ChannelId
import com.example.ivideo.entity.ShortVideo
import com.example.ivideo.state.ShortVideoState
import com.example.ivideo.state.VideoTypeState
import com.example.ivideo.ui.MyLayourtManager
import com.example.ivideo.viewmodel.SVideoIntent
import com.example.ivideo.viewmodel.SVideoViewModel
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class OneFragment : Fragment() {
    lateinit var binding:FragmentOneBinding
    lateinit var viewModle:SVideoViewModel
    lateinit var typeAdapter: VideoTypeAdapter
    lateinit var videoAdapter: ShortVideoAdapter
    lateinit var layout:MyLayourtManager
    var list:MutableList<ChannelId> = ArrayList()
    var list2:MutableList<ShortVideo> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOneBinding.inflate(layoutInflater,container,false)
        viewModle = ViewModelProvider(this).get(SVideoViewModel::class.java)
        layout = MyLayourtManager(context)
        videoAdapter = ShortVideoAdapter(context,list2)
        binding.rvSVideo.adapter = videoAdapter
        binding.rvSVideo.layoutManager = layout

        getType()
        getVideo("94349546935")

        typeAdapter = VideoTypeAdapter(context,list, object :VideoTypeAdapter.ClickListener{
            override fun onClick(position: Int, entity: ChannelId) {
                val channelId = entity.channelid
                getVideo(channelId)
                val tid = entity.id
                for (channel in list) {
                    channel.textC = channel.id == tid
                }
                typeAdapter.notifyDataSetChanged()
            }
        })

        binding.rvSVideo.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val player = recyclerView.findViewById<StandardGSYVideoPlayer>(R.id.gsy)
                player.startPlayLogic()
            }

        })

        binding.rvType.adapter = typeAdapter
        binding.rvType.layoutManager = StaggeredGridLayoutManager(1,RecyclerView.HORIZONTAL)

        lifecycleScope.launch {
            viewModle.tyState.collect { tyState ->
                tyState
                when(tyState){
                    is VideoTypeState.Loading -> {
                        Log.i("xxx", "onCreateView:11111 ")
                    }

                    is VideoTypeState.Success ->{
                        updataUI(tyState)
                    }
                }
            }
        }

        lifecycleScope.launch {
            viewModle.svState.collect{ svState ->
                when(svState){
                    is ShortVideoState.Loading -> {
                        Log.i("xxx", "onCreateView:222 ")
                    }

                    is ShortVideoState.Success ->{
                        updataVideo(svState)
                    }
                }
            }
        }

        return binding.root
    }

    private fun updataVideo(svState: ShortVideoState.Success) {
        videoAdapter.data = svState.list!!
        videoAdapter.notifyDataSetChanged()
    }

    fun getType(){
        lifecycleScope.launch {
            viewModle.channel.send(SVideoIntent.getSimpleType)
        }
    }

    fun getVideo(channelid:String){
        lifecycleScope.launch {
            viewModle.channel.send(SVideoIntent.getSVideo(channelid,1,10))
        }
    }

    private fun updataUI(tyState: VideoTypeState.Success) {
        list.addAll(tyState.list!!)
        typeAdapter.notifyDataSetChanged()
    }

}