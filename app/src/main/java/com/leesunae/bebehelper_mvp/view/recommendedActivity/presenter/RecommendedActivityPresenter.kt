package com.leesunae.bebehelper_mvp.view.recommendedActivity.presenter

import com.leesunae.bebehelper_mvp.data.model.PlayItem
import com.leesunae.bebehelper_mvp.view.recommendedActivity.presenter.adapter.PlayImgAdapterContract

class RecommendedActivityPresenter(
    private val view: RecommendedActivityContract.View,
    private val adapterView: PlayImgAdapterContract.View,
    private val adapterModel: PlayImgAdapterContract.Model
) :
    RecommendedActivityContract.Presenter {
    override fun addData(items: MutableList<PlayItem>) {
        adapterModel.addData(items)
        adapterView.notifyAdapter()
        view.getIsFirstTextVisibility(items.isNotEmpty(), items)
    }
}