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

    /** 유저 정보 조회 */
    fun getUser(email: String, callback: Callback<User>)

    /** 전체 유저 목록 조회 */
    fun getUserAll(callback: Callback<List<User>>)

    /** 이메일 체크 */
    fun checkEmail(email: String, callback: Callback<Boolean>)

    /** 닉네임 체크 */
    fun checkNickname(nickname: String, callback: Callback<Boolean>)
}