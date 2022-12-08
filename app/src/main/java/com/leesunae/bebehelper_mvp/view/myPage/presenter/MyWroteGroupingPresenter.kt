package com.leesunae.bebehelper_mvp.view.myPage.presenter

import android.util.Log
import com.leesunae.bebehelper_mvp.Session
import com.leesunae.bebehelper_mvp.data.repository.Callback
import com.leesunae.bebehelper_mvp.data.repository.grouping.GroupingRepository
import com.leesunae.bebehelper_mvp.data.room.entity.Grouping
import com.leesunae.bebehelper_mvp.view.myPage.presenter.adapter.MyWroteGroupingAdapterContract

class MyWroteGroupingPresenter(
    private val groupingRepository: GroupingRepository,
    private val view: MyWroteGroupingContract.View,
    private val adapterView: MyWroteGroupingAdapterContract.View,
    private val adapterModel: MyWroteGroupingAdapterContract.Model
) : MyWroteGroupingContract.Presenter {

    override fun getGroupingList() {
        groupingRepository.getGroupingList(object : Callback<List<Grouping>> {
            override fun onSuccess(response: List<Grouping>) {
                Log.i("[${javaClass.name}]", "$response")

                val user = Session.getUser()
                val groupingList = mutableListOf<Grouping>()
                response.forEach {
                    if (it.writerId == user?.id && it.writerNickname == user?.nickname)
                        groupingList.add(it)
                }
                adapterModel.addData(groupingList)
                adapterView.notifyAdapter()
            }

            override fun onFailure(message: String) {

            }
        })
    }
}