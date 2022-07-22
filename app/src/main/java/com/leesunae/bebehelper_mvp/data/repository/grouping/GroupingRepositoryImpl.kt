package com.leesunae.bebehelper_mvp.data.repository.grouping

import com.leesunae.bebehelper_mvp.data.repository.Callback
import com.leesunae.bebehelper_mvp.data.repository.user.UserRepository
import com.leesunae.bebehelper_mvp.data.repository.user.UserRepositoryImpl
import com.leesunae.bebehelper_mvp.data.room.entity.Grouping
import com.leesunae.bebehelper_mvp.data.source.local.grouping.GroupingLocalDataSource
import com.leesunae.bebehelper_mvp.data.source.local.user.UserLocalDataSource

class GroupingRepositoryImpl private constructor(
    private val localDataSource: GroupingLocalDataSource
) : GroupingRepository {
    override fun createGrouping(
        title: String,
        area: String,
        ageLimit: String,
        childCount: String,
        ageOfChildren: String,
        content: String,
        writerId: Int,
        writerNickname: String,
        callback: Callback<Boolean>
    ) {

    }

    override fun updateGrouping(
        id: Int,
        title: String,
        area: String,
        ageLimit: String,
        childCount: String,
        ageOfChildren: String,
        content: String,
        callback: Callback<Boolean>
    ) {

    }

    override fun getGrouping(email: String, callback: Callback<Grouping>) {

    }

    override fun getGroupingList(callback: Callback<List<Grouping>>) {

    }

    override fun deleteGrouping(id: Int, callback: Callback<String>) {

    }

    companion object {
        fun getInstance(localDataSource: GroupingLocalDataSource): GroupingRepository =
            GroupingRepositoryImpl(localDataSource)
    }
}