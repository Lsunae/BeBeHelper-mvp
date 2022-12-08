package com.leesunae.bebehelper_mvp.data.source.local.grouping

import com.leesunae.bebehelper_mvp.data.repository.Callback
import com.leesunae.bebehelper_mvp.data.room.database.GroupingDB
import com.leesunae.bebehelper_mvp.data.room.entity.Grouping
import com.leesunae.bebehelper_mvp.data.room.entity.User
import com.leesunae.bebehelper_mvp.util.AppExecutors

class GroupingLocalDataSourceImpl(
    private val appExecutors: AppExecutors,
    private val groupingDB: GroupingDB
) : GroupingLocalDataSource {

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
        appExecutors.diskIO.execute {
            val newGrouping = Grouping(
                title = title,
                area = area,
                ageLimit = ageLimit,
                childCount = childCount,
                content = content,
                writerId = writerId,
                writerNickname = writerNickname
            )
            val insertedPk = groupingDB.groupingDao().insertGrouping(newGrouping)
            appExecutors.mainThread.execute {
                callback.onSuccess(true)
            }
        }
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

    }

    override fun getGroupingList(callback: Callback<List<Grouping>>) {
        appExecutors.diskIO.execute {
            val groupings = groupingDB.groupingDao().getAll()
            appExecutors.mainThread.execute {
                callback.onSuccess(groupings)
            }
        }
    }

    override fun deleteGrouping(id: Int, callback: Callback<String>) {

    }

    companion object {
        fun getInstance(
            appExecutors: AppExecutors,
            groupingDatabase: GroupingDB
        ): GroupingLocalDataSource =
            GroupingLocalDataSourceImpl(
                appExecutors,
                groupingDatabase
            )
    }
}