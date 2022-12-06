package com.leesunae.bebehelper_mvp.view.recommendedActivity.presenter

import com.leesunae.bebehelper_mvp.data.model.PlayItem

interface RecommendedActivityContract {
    interface View {
        fun getIsFirstTextVisibility(isVisibility: Boolean, items: MutableList<PlayItem>)
    }

    interface Presenter {
        fun addData(items: MutableList<PlayItem>)
    }
}