package com.leesunae.bebehelper_mvp.view.grouping.presenter

import android.content.Context
import android.util.Log
import com.leesunae.bebehelper_mvp.data.repository.Callback
import com.leesunae.bebehelper_mvp.data.repository.grouping.GroupingRepository
import com.leesunae.bebehelper_mvp.data.room.entity.Grouping
import com.leesunae.bebehelper_mvp.view.grouping.presenter.adapter.GroupingAdapterContract

class GroupingPresenter(
    private val groupingRepository: GroupingRepository,
    private val view: GroupingContract.View,
    private val adapterView: GroupingAdapterContract.View,
    private val adapterModel: GroupingAdapterContract.Model
) : GroupingContract.Presenter {
    override fun getGroupingList(context: Context) {
        groupingRepository.getGroupingList(object : Callback<List<Grouping>> {
            override fun onSuccess(response: List<Grouping>) {
                Log.i("[${javaClass.name}]", "$response")

                val groupings = mutableListOf<Grouping>()
                groupings.addAll(response)
                adapterModel.addData(groupings)
                adapterView.notifyAdapter()
            }

            override fun onFailure(message: String) {
                Log.e("[${javaClass.name}]", message)
            }
        })
    }
}