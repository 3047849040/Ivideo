package com.example.ivideo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ivideo.reporitory.ShortVideoRepository
import com.example.ivideo.state.ShortVideoState
import com.example.ivideo.state.VideoTypeState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

sealed class SVideoIntent{
    data class getSVideo(var channelId:String,var page:Int,var pagesize:Int):SVideoIntent()
    object getSimpleType:SVideoIntent()
}

class SVideoViewModel:ViewModel() {
    var shortVideoRepository = ShortVideoRepository()
    val channel = Channel<SVideoIntent>(Channel.UNLIMITED)

    private val _svState = MutableStateFlow<ShortVideoState>(ShortVideoState.Loading)
    val svState:StateFlow<ShortVideoState>
        get() = _svState

    private val _tyState = MutableStateFlow<VideoTypeState>(VideoTypeState.Loading)
    val tyState:StateFlow<VideoTypeState>
        get() = _tyState

    init {
        handlerIntent()
    }

    private fun handlerIntent() {
        viewModelScope.launch {
            channel.consumeAsFlow().collect {
                when(it){
                    is SVideoIntent.getSimpleType -> getTypes()

                    is SVideoIntent.getSVideo -> getVideos(it.channelId,it.page,it.pagesize)
                }
            }
        }
    }

    private fun getTypes() {
        viewModelScope.launch {
            val types = shortVideoRepository.doGetSimpleType()
            if (types.code == 0){
                _tyState.value = VideoTypeState.Success(types?.data)
            }else{
                _tyState.value = VideoTypeState.Fail(types.msg)
            }
        }
    }

    private fun getVideos(channelId:String,page:Int,pagesize:Int) {
        viewModelScope.launch {
            val data = shortVideoRepository.doGetSimpleVideoByChannelId(channelId, page, pagesize)
            if (data.code == 0){
                _svState.value = ShortVideoState.Success(data?.data)
            }else{
                _svState.value = ShortVideoState.Fail(data.msg)
            }
        }
    }

}