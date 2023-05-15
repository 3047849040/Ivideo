package com.example.ivideo.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.ivideo.R
import com.example.ivideo.entity.ShortVideo

class ReShouAdapter(layoutResId: Int):BaseQuickAdapter<ShortVideo,BaseViewHolder>(layoutResId) {
    override fun convert(helper: BaseViewHolder, item: ShortVideo) {
        helper.setText(R.id.tv_rs,item.title)
    }
}