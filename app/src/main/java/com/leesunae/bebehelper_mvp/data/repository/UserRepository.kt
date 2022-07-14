package com.leesunae.bebehelper_mvp.data.repository

import com.leesunae.bebehelper_mvp.data.room.entity.User

interface UserRepository {
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

    fun login(
        email: String,
        password: String,
        callback: Callback<User>
    )

    fun logout(callback: Callback<String>)

    fun updateUser(
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
    )

    fun getUser(email:String, callback: Callback<User>)

    fun getUserAll(callback: Callback<List<User>>)

    fun deleteUser(id: Int, callback: Callback<String>)

    /** 이메일 체크 */
    fun checkEmail(email: String, callback: Callback<Boolean>)

    /** 닉네임 체크 */
    fun checkNickname(nickname: String, callback: Callback<Boolean>)
}