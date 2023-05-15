package com.example.ivideo.reporitory

import com.example.ivideo.entity.RequestData
import com.example.ivideo.entity.ZhiBoEntity
import com.example.ivideo.model.ApiServer
import com.example.ivideo.model.RetrofitManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ZhiBoRepository {
    var apiServer = RetrofitManager.instance!!.retrofit!!.create(ApiServer::class.java)

    suspend fun doGetZhiBo(page:Int,pagesize:Int): RequestData<List<ZhiBoEntity>> {
        return withContext(Dispatchers.IO){
            val zhiBo = apiServer.getZhiBo(page, pagesize)
            zhiBo
        }
    }

}