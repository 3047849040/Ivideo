package com.example.ivideo.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.ivideo.R
import com.example.ivideo.db.LishiEntity

class LiShiAdapter(layoutResId: Int) : BaseQuickAdapter<LishiEntity?, BaseViewHolder>(layoutResId) {
    override fun convert(baseViewHolder: BaseViewHolder, entity: LishiEntity?) {
        baseViewHolder.setText(R.id.tv_lishi,entity!!.liShi)
    }
}