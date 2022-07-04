package com.leesunae.bebehelper_mvp.data.source.local

import com.leesunae.bebehelper_mvp.data.repository.Callback
import com.leesunae.bebehelper_mvp.data.room.database.UserDB
import com.leesunae.bebehelper_mvp.data.room.entity.User
import com.leesunae.bebehelper_mvp.util.AppExecutors

class UserLocalDataSourceImpl(
    private val appExecutors: AppExecutors,
    private val userDB: UserDB
) : UserLocalDataSource {
    override fun login(email: String, password: String, callback: Callback<Boolean>) {
        TODO("Not yet implemented")
    }

    override fun logout(callback: Callback<String>) {
        TODO("Not yet implemented")
    }

    override fun isLogin(callback: Callback<Boolean>) {
        TODO("Not yet implemented")
    }

    override fun deleteUser(callback: Callback<Boolean>) {
        TODO("Not yet implemented")
    }

    override fun updateUser(
        nickname: String,
        gender: String,
        childGender: String,
        image: String,
        callback: Callback<Boolean>
    ) {
        TODO("Not yet implemented")
    }

    override fun getUser(callback: Callback<User>) {
        TODO("Not yet implemented")
    }

    companion object {
        fun getInstance(
            appExecutors: AppExecutors,
            userDatabase: UserDB
        ): UserLocalDataSource =
            UserLocalDataSourceImpl(
                appExecutors,
                userDatabase
            )
    }
}