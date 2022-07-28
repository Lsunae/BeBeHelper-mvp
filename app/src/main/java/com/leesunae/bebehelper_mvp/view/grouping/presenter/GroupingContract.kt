package com.leesunae.bebehelper_mvp.view.grouping.presenter

import android.content.Context
import com.leesunae.bebehelper_mvp.data.room.entity.Grouping

interface GroupingContract {
    interface View {
        fun getGroupingListData(groupings: MutableList<Grouping>)
    }

    interface Presenter {
        fun getGroupingList(context: Context)
    }
}