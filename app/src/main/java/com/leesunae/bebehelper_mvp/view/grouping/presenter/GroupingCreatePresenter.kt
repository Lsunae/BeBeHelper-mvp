package com.leesunae.bebehelper_mvp.view.grouping.presenter

import com.leesunae.bebehelper_mvp.data.model.GroupingItem
import com.leesunae.bebehelper_mvp.data.repository.grouping.GroupingRepository

class GroupingCreatePresenter(
    private val groupingRepository: GroupingRepository,
    private val view: GroupingCreateContract.View
) : GroupingCreateContract.Presenter {
    override fun createUser(grouping: GroupingItem) {

    }
}