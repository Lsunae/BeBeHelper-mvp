package com.leesunae.bebehelper_mvp.data.repository

import com.leesunae.bebehelper_mvp.data.room.entity.User
import com.leesunae.bebehelper_mvp.data.source.local.UserLocalDataSource

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

    override fun login(email: String, password: String, callback: Callback<Boolean>) {

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
        ageOfChildren: String,
        area: String,
        image: String,
        callback: Callback<Boolean>
    ) {

    }

    override fun getUser(callback: Callback<User>) {
        localDataSource.getUser(callback)
    }

    override fun deleteUser(id: Int, callback: Callback<String>) {

    }

    companion object {
        fun getInstance(localDataSource: UserLocalDataSource): UserRepository =
            UserRepositoryImpl(localDataSource)
    }
}