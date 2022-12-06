package com.leesunae.bebehelper_mvp.view.recommendedActivity.presenter.adapter

import com.leesunae.bebehelper_mvp.data.model.PlayItem

interface PlayImgAdapterContract {
    interface View {
        fun notifyAdapter()
    }

    interface Model {
        /** 놀이 이미지 목록 데이터 추가 */
        fun addData(addDataList: MutableList<PlayItem>)
    }
}