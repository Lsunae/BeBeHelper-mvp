package com.leesunae.bebehelper_mvp

import com.leesunae.bebehelper_mvp.data.room.entity.User

object Session {
    private var user: User? = null

    /** 로그인 체크 */
    fun checkLogin(listener: ((Boolean) -> Unit)) {
        if (user == null) {

        } else listener(true)
    }

    /** 로그인 유저 정보 가져오기 */
    fun getUser(): User? {
        return user
    }

    /** 로그인한 유저 정보 셋팅 */
    fun setUser(user: User?) {
        this.user = user
    }

    /** 로그인 여부 */
    fun getIsLogin(): Boolean {
        return user != null
    }

    /** 로그아웃 */
    fun logout(listener: ((Boolean) -> Unit)) {

    }
}