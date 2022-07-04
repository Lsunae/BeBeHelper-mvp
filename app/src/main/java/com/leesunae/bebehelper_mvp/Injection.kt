package com.leesunae.bebehelper_mvp

import com.leesunae.bebehelper_mvp.data.repository.UserRepository
import com.leesunae.bebehelper_mvp.data.repository.UserRepositoryImpl
import com.leesunae.bebehelper_mvp.data.room.database.UserDB
import com.leesunae.bebehelper_mvp.data.source.local.UserLocalDataSourceImpl
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
}