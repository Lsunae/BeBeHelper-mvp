package com.leesunae.bebehelper_mvp

import com.leesunae.bebehelper_mvp.constant.PreferenceType
import com.leesunae.bebehelper_mvp.data.room.entity.User

object Session {
    private var loginId: Int = 0
    private var user: User? = null

    /** 로그인 체크 */
    fun checkLogin(listener: ((Boolean) -> Unit)) {
        println("checkLoginId_ $loginId")
        if (loginId != 0) {
            if (user == null) {
                listener(false)
            } else listener(true)
        } else {
            resetId()
            listener(false)
        }
    }

    /** 로그인 유저 아이디 리셋 */
    fun resetId() {
        PreferenceManager.setInt(
            App.instance.context(),
            PreferenceType.LOGIN_ID.key,
            0)
    }

    /** 로그인 유저 아이디 가져오기 */
    fun getLoginId(): Int {
        if (loginId == 0) {
            loginId = PreferenceManager.getInt(
                App.instance.context(),
                PreferenceType.LOGIN_ID.key
            )
        }
        return loginId
    }

    /** 로그인 유저 아이디 셋팅 */
    fun setLoginId(loginId: Int) {
        PreferenceManager.setInt(
            App.instance.context(),
            PreferenceType.LOGIN_ID.key,
            loginId.also {
                this.loginId = it
            })
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

    init {
        loginId =
            PreferenceManager.getInt(App.instance.context(), PreferenceType.LOGIN_ID.key)
    }
}