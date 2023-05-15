package com.example.ivideo.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.ivideo.R
import com.example.ivideo.adapter.LiShiAdapter
import com.example.ivideo.adapter.ReShouAdapter
import com.example.ivideo.databinding.ActivitySearchBinding
import com.example.ivideo.db.DbInstance
import com.example.ivideo.db.LishiEntity
import com.example.ivideo.entity.ShortVideo
import com.example.ivideo.state.ShortVideoState
import com.example.ivideo.viewmodel.SVideoIntent
import com.example.ivideo.viewmodel.SVideoViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Route(path = "/app/SearchActivity")
class SearchActivity : AppCompatActivity() {
    lateinit var binding:ActivitySearchBinding
    lateinit var viewModel:SVideoViewModel
    lateinit var reShouAdapter: ReShouAdapter
    lateinit var adapter: LiShiAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(SVideoViewModel::class.java)

        getReShou()

        adapter = LiShiAdapter(R.layout.item_lishi)
        binding.rvLiShi.adapter = adapter
        binding.rvLiShi.layoutManager = GridLayoutManager(this,2)
        getLishi()

        reShouAdapter = ReShouAdapter(R.layout.item_reshou)
        binding.rvRs.adapter = reShouAdapter
        binding.rvRs.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            viewModel.svState.collect { svState->
                when(svState){
                    is ShortVideoState.Loading -> {
                        Log.i("xxx", "onCreateView:222 ")
                    }

                    is ShortVideoState.Success ->{
                        Log.i("xxx", "onCreateView:333 ")
                        updataVideo(svState)
                    }
                }
            }
        }

        binding.ivBack.setOnClickListener {
            finish()
        }

        binding.tvSearch.setOnClickListener {
            addLishi()
        }

        binding.ivQc.setOnClickListener {
            DbInstance.getDataBase().lishiDao().deleteAll()
            getLishi()
        }

    }

    private fun updataVideo(svState: ShortVideoState.Success) {
        reShouAdapter.data = svState.list as MutableList<ShortVideo>
        reShouAdapter.notifyDataSetChanged()
    }

    fun getReShou(){
        lifecycleScope.launch {
            viewModel.channel.send(SVideoIntent.getSVideo("94349546935",1,10))
        }
    }

    fun addLishi(){
        var flag = true
        val str = binding.etSearch.text.toString()
        if (str!="") {
            var entity = LishiEntity(str)
            val queryLs = DbInstance.getDataBase().lishiDao().queryAll()
            for (queryL in queryLs) {
                if (queryL.liShi == str) {
                    flag = false
                }
            }
            if (flag) {
                DbInstance.getDataBase().lishiDao().insertLs(entity)
            }
        }
        getLishi()
    }

    fun getLishi(){
        val list = DbInstance.getDataBase().lishiDao().queryAll()
        if (list!=null){
            adapter.data.clear()
            adapter.data.addAll(list)
            adapter.notifyDataSetChanged()
        }
    }
}