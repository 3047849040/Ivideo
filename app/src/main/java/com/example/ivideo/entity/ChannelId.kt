package com.example.ivideo.entity

data class ChannelId(
    val channelid: String,
    val haschild: Int,
    val id: Int,
    val pid: Int,
    val pinyin: String,
    val typename: String,
    var textC:Boolean = false
)