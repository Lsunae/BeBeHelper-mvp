package com.leesunae.bebehelper_mvp.data.source.local

import com.leesunae.bebehelper_mvp.data.repository.Callback
import com.leesunae.bebehelper_mvp.data.room.database.UserDB
import com.leesunae.bebehelper_mvp.data.room.entity.User
import com.leesunae.bebehelper_mvp.util.AppExecutors

class UserLocalDataSourceImpl(
    private val appExecutors: AppExecutors,
    private val userDB: UserDB
) : UserLocalDataSource {
    override fun createUser(
        email: String,
        password: String,
        nickName: String,
        gender: String?,
        childGender: String?,
        ageOfChildren: String?,
        area: String?,
        image: String?,
        callback: Callback<Boolean>
    ) {
        appExecutors.diskIO.execute {
            val newUser = User(
                email = email,
                password = password,
                nickname = nickName,
                gender = null,
                childGender = null,
                ageOfChildren = null,
                area = null,
                image = null
            )
            val insertedPk = userDB.userDao().insertUser(newUser)
            if (insertedPk == 0L) {
                appExecutors.mainThread.execute {
                    callback.onSuccess(true)
                }
            }
        }
    }

    override fun login(email: String, password: String, callback: Callback<Boolean>) {

    }

    override fun logout(callback: Callback<String>) {

    }

    override fun isLogin(callback: Callback<Boolean>) {

    }

    override fun deleteUser(callback: Callback<Boolean>) {

    }

    override fun updateUser(
        nickname: String,
        gender: String,
        childGender: String,
        image: String,
        callback: Callback<Boolean>
    ) {

    }

    override fun getUser(callback: Callback<User>) {
        appExecutors.diskIO.execute {
            val user = userDB.userDao().getAll()
            println("local_user_ $user")
            appExecutors.mainThread.execute {
                callback.onSuccess(user)
            }

//            if (userDB.userDao().getUserCount() == 1) {
//                val user = userDatabase.userDao().getUser()
//                appExecutors.mainThread.execute {
//                    callback.onSuccess(user)
//                }
//            } else {
//                callback.onFailure("없음")
//            }
        }
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