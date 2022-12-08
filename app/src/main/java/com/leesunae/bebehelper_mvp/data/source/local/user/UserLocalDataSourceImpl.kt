package com.leesunae.bebehelper_mvp.data.source.local.user

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
                gender = gender,
                childGender = childGender,
                ageOfChildren = ageOfChildren,
                area = area,
                image = null
            )
            val insertedPk = userDB.userDao().insertUser(newUser)
            appExecutors.mainThread.execute {
                callback.onSuccess(true)
            }
        }
    }

    override fun login(email: String, password: String, callback: Callback<User?>) {
        appExecutors.diskIO.execute {
            val loginUser = userDB.userDao().getLoginUser(email)
            appExecutors.mainThread.execute {
                callback.onSuccess(loginUser)
            }
        }
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

    override fun getUser(email: String, callback: Callback<User>) {
        appExecutors.diskIO.execute {
            val user = userDB.userDao().getUser(email)
            appExecutors.mainThread.execute {
                callback.onSuccess(user)
            }
        }
    }

    override fun getUserAll(callback: Callback<List<User>>) {
        appExecutors.diskIO.execute {
            val user = userDB.userDao().getAll()
            appExecutors.mainThread.execute {
                callback.onSuccess(user)
            }
        }
    }

    override fun checkEmail(email: String, callback: Callback<Boolean>) {
        appExecutors.diskIO.execute {
            val userList = userDB.userDao().checkEmail(email)
            val isChecked = userList.isNullOrEmpty()
            appExecutors.mainThread.execute {
                callback.onSuccess(isChecked)
            }
        }
    }

    override fun checkNickname(nickname: String, callback: Callback<Boolean>) {
        appExecutors.diskIO.execute {
            val userList = userDB.userDao().checkNickname(nickname)
            val isChecked = userList.isNullOrEmpty()
            appExecutors.mainThread.execute {
                callback.onSuccess(isChecked)
            }
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