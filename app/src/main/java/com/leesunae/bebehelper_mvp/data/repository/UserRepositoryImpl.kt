package com.leesunae.bebehelper_mvp.data.repository

import com.leesunae.bebehelper_mvp.data.repository.user.UserRepository
import com.leesunae.bebehelper_mvp.data.room.entity.User
import com.leesunae.bebehelper_mvp.data.source.local.user.UserLocalDataSource

class UserRepositoryImpl(private val localDataSource: UserLocalDataSource) : UserRepository {
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

    }

    override fun getUser(email: String, callback: Callback<User>) {
        TODO("Not yet implemented")
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
        TODO("Not yet implemented")
    }

    override fun deleteUser(id: Int, callback: Callback<String>) {
        TODO("Not yet implemented")
    }

    override fun login(email: String, password: String, callback: Callback<User?>) {
        TODO("Not yet implemented")
    }

    override fun logout(callback: Callback<String>) {
        TODO("Not yet implemented")
    }

    override fun getUserAll(callback: Callback<List<User>>) {
        TODO("Not yet implemented")
    }

    override fun checkEmail(email: String, callback: Callback<Boolean>) {
        TODO("Not yet implemented")
    }

    override fun checkNickname(nickname: String, callback: Callback<Boolean>) {
        TODO("Not yet implemented")
    }
}