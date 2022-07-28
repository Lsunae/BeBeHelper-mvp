package com.leesunae.bebehelper_mvp.view.grouping.presenter.adapter

import com.leesunae.bebehelper_mvp.data.room.entity.Grouping

interface GroupingAdapterContract {
    interface View {

        fun notifyAdapter()

    }

    interface Model {

        /** 그룹핑 목록 데이터 추가 */
        fun addData(addDataList: MutableList<Grouping>)

    }
}