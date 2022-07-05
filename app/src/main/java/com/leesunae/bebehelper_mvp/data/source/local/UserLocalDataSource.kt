package com.leesunae.bebehelper_mvp.data.source.local

import com.leesunae.bebehelper_mvp.data.repository.Callback
import com.leesunae.bebehelper_mvp.data.room.entity.User

interface UserLocalDataSource {
    fun createUser(
        email: String,
        password: String,
        nickName: String,
        gender: String?,
        childGender: String?,
        ageOfChildren: String?,
        area: String?,
        image: String?,
        callback: Callback<Boolean>
    )
    fun login(email: String, password: String, callback: Callback<Boolean>)
    fun logout(callback: Callback<String>)
    fun isLogin(callback: Callback<Boolean>)
    fun deleteUser(callback: Callback<Boolean>)
    fun updateUser(
        nickname: String,
        gender: String,
        childGender: String,
        image: String,
        callback: Callback<Boolean>
    )

    fun getUser(callback: Callback<User>)
}