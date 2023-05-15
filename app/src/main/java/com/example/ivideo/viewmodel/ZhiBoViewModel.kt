package com.example.ivideo.viewmodel

import androidx.lifecycle.ViewModel

sealed class ZhiBoIntent{
    data class getZhiBo(var page:Int,var pagesize:Int):ZhiBoIntent()
}

class ZhiBoViewModel:ViewModel() {



}