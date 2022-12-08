package com.leesunae.bebehelper_mvp.view.myPage.presenter

import android.content.Context
import com.leesunae.bebehelper_mvp.data.room.entity.Grouping

interface MyWroteGroupingContract {
    interface View {
        fun getGroupingListData(groupingList: MutableList<Grouping>)
    }

    interface Presenter {
        fun getGroupingList()
    }
}