package com.example.ivideo.model

import com.example.ivideo.entity.ChannelId
import com.example.ivideo.entity.RequestData
import com.example.ivideo.entity.ShortVideo
import com.example.ivideo.entity.ZhiBoEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServer {
    @GET("/videosimple/getSimpleVideoByChannelId")
    suspend fun getSimpleVideoByChannelId(@Query("channelId") channelId:String, @Query("page")page:Int, @Query("pagesize") pagesize:Int):RequestData<List<ShortVideo>>

    @GET("/videotype/getSimpleType")
    suspend fun getSimpleType():RequestData<List<ChannelId>>

    @GET("/videosimple/getRecommendSimpleVideo")
    suspend fun getZhiBo(@Query("page") page: Int,@Query("pagesize") pagesize:Int):RequestData<List<ZhiBoEntity>>
}