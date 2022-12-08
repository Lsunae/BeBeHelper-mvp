package com.leesunae.bebehelper_mvp.view.grouping.presenter

import android.content.Context

interface GroupingContract {
    interface View {}

    interface Presenter {
        fun getGroupingList(context: Context)
    }
}