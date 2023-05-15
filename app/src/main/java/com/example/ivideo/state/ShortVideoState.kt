package com.example.ivideo.state

import com.example.ivideo.entity.ShortVideo

sealed class ShortVideoState {
    data class Success(var list: List<ShortVideo>?):ShortVideoState()
    data class Error(var ex:Throwable?):ShortVideoState()
    data class Fail(var msg:String?):ShortVideoState()
    object Loading:ShortVideoState()
}