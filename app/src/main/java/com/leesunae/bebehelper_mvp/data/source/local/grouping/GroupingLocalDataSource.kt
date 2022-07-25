package com.leesunae.bebehelper_mvp.data.source.local.grouping

import com.leesunae.bebehelper_mvp.data.repository.Callback
import com.leesunae.bebehelper_mvp.data.room.entity.Grouping

interface GroupingLocalDataSource {
    fun createGrouping(
        title: String,
        area: String,
        ageLimit: String,
        childCount: Int,
        ageOfChildren: String,
        content: String,
        writerId: Int,
        writerNickname: String,
        callback: Callback<Boolean>
    )

    fun updateGrouping(
        id: Int,
        title: String,
        area: String,
        ageLimit: String,
        childCount: String,
        ageOfChildren: String,
        content: String,
        callback: Callback<Boolean>
    )

    fun getGrouping(email: String, callback: Callback<Grouping>)

    fun getGroupingList(callback: Callback<List<Grouping>>)

    fun deleteGrouping(id: Int, callback: Callback<String>)
}