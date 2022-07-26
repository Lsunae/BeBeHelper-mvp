package com.leesunae.bebehelper_mvp.view.grouping.presenter

import com.leesunae.bebehelper_mvp.data.model.GroupingItem

interface GroupingCreateContract {
    interface View {
        fun createGroupingSuccess(message: Boolean)
    }

    interface Presenter {
        fun createGrouping(grouping: GroupingItem)
    }
}