package com.example.ivideo.state

import com.example.ivideo.entity.ChannelId
import com.example.ivideo.entity.ShortVideo

sealed class VideoTypeState {
    data class Success(var list: List<ChannelId>?):VideoTypeState()
    data class Error(var ex:Throwable?):VideoTypeState()
    data class Fail(var msg:String?):VideoTypeState()
    object Loading:VideoTypeState()
}