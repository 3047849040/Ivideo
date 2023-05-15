package com.example.ivideo.reporitory

import com.example.ivideo.entity.ChannelId
import com.example.ivideo.entity.RequestData
import com.example.ivideo.entity.ShortVideo
import com.example.ivideo.model.ApiServer
import com.example.ivideo.model.RetrofitManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.http.Query

class ShortVideoRepository {
    var apiServer = RetrofitManager.instance!!.retrofit!!.create(ApiServer::class.java)

    suspend fun doGetSimpleVideoByChannelId(channelId:String,page:Int,pagesize:Int): RequestData<List<ShortVideo>> {
        return withContext(Dispatchers.IO){
            val simpleVideoByChannelId =
                apiServer.getSimpleVideoByChannelId(channelId, page, pagesize)
            simpleVideoByChannelId
        }
    }

    suspend fun doGetSimpleType():RequestData<List<ChannelId>>{
        return withContext(Dispatchers.IO){
            val simpleType = apiServer.getSimpleType()
            simpleType
        }
    }
}