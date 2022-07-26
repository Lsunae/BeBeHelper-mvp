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
        childCount: Int,
        content: String,
        writerId: Int,
        writerNickname: String,
        callback: Callback<Boolean>
    ) {
        localDataSource.createGrouping(title, area, ageLimit, childCount, content, writerId, writerNickname, callback)
    }

    override fun updateGrouping(
        id: Int,
        title: String,
        area: String,
        ageLimit: String,
        childCount: String,
        content: String,
        callback: Callback<Boolean>
    ) {

    }

    override fun getGrouping(email: String, callback: Callback<Grouping>) {
        localDataSource.getGrouping(email, callback)
    }

    override fun getGroupingList(callback: Callback<List<Grouping>>) {
        localDataSource.getGroupingList(callback)
    }

    override fun deleteGrouping(id: Int, callback: Callback<String>) {
        localDataSource.deleteGrouping(id, callback)
    }

    companion object {
        fun getInstance(localDataSource: GroupingLocalDataSource): GroupingRepository =
            GroupingRepositoryImpl(localDataSource)
    }
}