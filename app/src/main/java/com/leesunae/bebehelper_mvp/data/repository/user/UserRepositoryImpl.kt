package com.leesunae.bebehelper_mvp.data.repository.user

import com.leesunae.bebehelper_mvp.data.repository.Callback
import com.leesunae.bebehelper_mvp.data.room.entity.User
import com.leesunae.bebehelper_mvp.data.source.local.user.UserLocalDataSource

class UserRepositoryImpl private constructor(
    private val localDataSource: UserLocalDataSource
) : UserRepository {
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
        localDataSource.createUser(email, password, nickName, gender, childGender, ageOfChildren, area, image, callback)
    }

    override fun login(email: String, password: String, callback: Callback<User?>) {
        localDataSource.login(email, password, callback)
    }

    override fun logout(callback: Callback<String>) {

    }

    override fun updateUser(
        id: Int,
        email: String,
        password: String,
        nickName: String,
        gender: String,
        childGender: String,
        ageOfChildren: String?,
        area: String,
        image: String,
        callback: Callback<Boolean>
    ) {

    }

    override fun getUser(email:String, callback: Callback<User>) {
        localDataSource.getUser(email, callback)
    }

    override fun getUserAll(callback: Callback<List<User>>) {
        localDataSource.getUserAll(callback)
    }

    override fun deleteUser(id: Int, callback: Callback<String>) {

    }

    override fun checkEmail(email: String, callback: Callback<Boolean>) {
        localDataSource.checkEmail(email, callback)
    }

    override fun checkNickname(nickname: String, callback: Callback<Boolean>) {
        localDataSource.checkNickname(nickname, callback)
    }

    companion object {
        fun getInstance(localDataSource: UserLocalDataSource): UserRepository =
            UserRepositoryImpl(localDataSource)
    }
}