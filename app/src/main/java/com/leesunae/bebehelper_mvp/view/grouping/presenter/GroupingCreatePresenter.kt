package com.leesunae.bebehelper_mvp.view.grouping.presenter

import android.util.Log
import com.leesunae.bebehelper_mvp.data.model.GroupingItem
import com.leesunae.bebehelper_mvp.data.repository.Callback
import com.leesunae.bebehelper_mvp.data.repository.grouping.GroupingRepository

class GroupingCreatePresenter(
    private val groupingRepository: GroupingRepository,
    private val view: GroupingCreateContract.View
) : GroupingCreateContract.Presenter {
    override fun createGrouping(grouping: GroupingItem) {
        println("presenter_grouping_ $grouping")
        groupingRepository.createGrouping(
            grouping.title,
            grouping.area,
            grouping.ageLimit,
            grouping.childCount,
            grouping.content,
            grouping.writerId,
            grouping.writerNickname,
            object : Callback<Boolean> {
                override fun onSuccess(response: Boolean) {
                    println("create_grouping_success_response $response")
                    Log.i("[${javaClass.name}]", "$response")
                    view.createGroupingSuccess(response)
                }

                override fun onFailure(message: String) {
                    println("create_grouping_failure_response $message")
                    Log.e("[${javaClass.name}]", message)
                }
            })
    }
}