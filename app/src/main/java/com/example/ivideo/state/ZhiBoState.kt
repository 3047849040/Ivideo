package com.example.ivideo.state

import com.example.ivideo.entity.ZhiBoEntity

sealed class ZhiBoState {
    data class Success(var list: List<ZhiBoEntity>):ZhiBoState()
    data class Error(var ex:Throwable?):ZhiBoState()
    data class Fail(var msg:String?):ZhiBoState()
    object Loading:ZhiBoState()
}