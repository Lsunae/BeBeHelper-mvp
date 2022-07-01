package com.leesunae.bebehelper_mvp.data.source.local

import com.leesunae.bebehelper_mvp.data.repository.Callback
import com.leesunae.bebehelper_mvp.data.room.entity.User

interface UserLocalDataSource {
    fun login(callback: Callback<Boolean>)
    fun logout(callback: Callback<String>)
    fun isLogin(callback: Callback<Boolean>)
    fun deleteUser(callback: Callback<Boolean>)
    fun updateUser(callback: Callback<Boolean>)
    fun getUser(callback: Callback<User>)
}