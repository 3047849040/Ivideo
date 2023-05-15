package com.example.ivideo.entity

data class RequestData<T>(
    var code:Int,
    var msg:String,
    var data:T?
)