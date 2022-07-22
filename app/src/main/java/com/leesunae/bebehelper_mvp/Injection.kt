package com.leesunae.bebehelper_mvp

import com.leesunae.bebehelper_mvp.data.repository.grouping.GroupingRepository
import com.leesunae.bebehelper_mvp.data.repository.grouping.GroupingRepositoryImpl
import com.leesunae.bebehelper_mvp.data.repository.user.UserRepository
import com.leesunae.bebehelper_mvp.data.repository.user.UserRepositoryImpl
import com.leesunae.bebehelper_mvp.data.room.database.GroupingDB
import com.leesunae.bebehelper_mvp.data.room.database.UserDB
import com.leesunae.bebehelper_mvp.data.source.local.grouping.GroupingLocalDataSourceImpl
import com.leesunae.bebehelper_mvp.data.source.local.user.UserLocalDataSourceImpl
import com.leesunae.bebehelper_mvp.util.AppExecutors

object Injection {
    fun userRepository(): UserRepository {
        return UserRepositoryImpl.getInstance(
            UserLocalDataSourceImpl.getInstance(
                AppExecutors(),
                UserDB.getInstance(App.instance.context())
            )
        )
    }

    fun groupingRepository(): GroupingRepository {
        return GroupingRepositoryImpl.getInstance(
            GroupingLocalDataSourceImpl.getInstance(
                AppExecutors(),
                GroupingDB.getInstance(App.instance.context())
            )
        )
    }
}