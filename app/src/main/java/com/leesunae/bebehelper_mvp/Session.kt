package com.leesunae.bebehelper_mvp

import android.util.Log
import com.leesunae.bebehelper_mvp.constant.PreferenceType
import com.leesunae.bebehelper_mvp.data.repository.Callback
import com.leesunae.bebehelper_mvp.data.room.entity.User

object Session {
    private var loginId: Int = 0
    private var user: User? = null
    private var userRepository = Injection.userRepository()

    /** 로그인 체크 */
    fun checkLogin(listener: ((Boolean) -> Unit)) {
        val email = PreferenceManager.getString(App.instance.context(), PreferenceType.LOGIN_EMAIL.key)
        val password = PreferenceManager.getString(App.instance.context(), PreferenceType.LOGIN_PW.key)
        if (loginId != 0 && email.isNotEmpty() && password.isNotEmpty()) {
            if (user == null) {
                login(email, password, listener = { isLogin ->
                    if (isLogin) {
                        listener(true)
                    } else {
                        listener(false)
                    }
                })
            } else {
                listener(true)
            }
        } else {
            resetUserData()
            listener(false)
        }
    }

    /** 로그인 유저 리셋 */
    fun resetUserData() {
        user = null

        PreferenceManager.setInt(
            App.instance.context(),
            PreferenceType.LOGIN_ID.key,
            0)
        PreferenceManager.setString(
            App.instance.context(),
            PreferenceType.LOGIN_EMAIL.key,
            "")
        PreferenceManager.setString(
            App.instance.context(),
            PreferenceType.LOGIN_PW.key,
            "")

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

    /** 로그인 유저 셋팅 */
    fun setLogin(email: String, password: String) {
        PreferenceManager.setString(
            App.instance.context(),
            PreferenceType.LOGIN_EMAIL.key,
            email
        )
        PreferenceManager.setString(
            App.instance.context(),
            PreferenceType.LOGIN_PW.key,
            password
        )
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

    /** 로그인 */
    fun login(email: String, password: String, listener: ((Boolean) -> Unit)) {
        userRepository.login(email, password, object : Callback<User?> {
            override fun onSuccess(response: User?) {
                Log.i("[${javaClass.name}]", "$response")
                val isLogin: Boolean = response != null && response.id > 0 && email == response.email && password == response.password
                if (isLogin) {
                    response?.let { setLoginId(it.id) }
                    response?.let { setLogin(it.email, it.password) }
                    setUser(response)
                }   // 유저 정보 저장
                listener(isLogin)
            }

            override fun onFailure(message: String) {
                Log.e("[${javaClass.name}]", message)
                listener(false)
            }
        })
    }

    /** 로그아웃 */
    fun logout(listener: ((Boolean) -> Unit)) {
        resetUserData()

        val email = PreferenceManager.getString(App.instance.context(), PreferenceType.LOGIN_EMAIL.key)
        val password = PreferenceManager.getString(App.instance.context(), PreferenceType.LOGIN_PW.key)

        val isLogout = user == null && email.isEmpty() && password.isEmpty()
        listener(isLogout)
    }

    init {
        loginId =
            PreferenceManager.getInt(App.instance.context(), PreferenceType.LOGIN_ID.key)
    }
}