package com.leesunae.bebehelper_mvp.view.sign.presenter

import android.util.Log
import com.leesunae.bebehelper_mvp.Session
import com.leesunae.bebehelper_mvp.data.repository.Callback
import com.leesunae.bebehelper_mvp.data.repository.user.UserRepository
import com.leesunae.bebehelper_mvp.data.room.entity.User

class SignInPresenter(
    private val userRepository: UserRepository,
    private var view: SignInContract.View
) : SignInContract.Presenter {
    override fun login(email: String, password: String) {
        userRepository.login(email, password, object : Callback<User?> {
            override fun onSuccess(response: User?) {
                Log.i("[${javaClass.name}]", "$response")
                val isLogin: Boolean = response != null && response.id > 0 && email == response.email && password == response.password
                if (isLogin) {
                    response?.let { Session.setLoginId(it.id) }
                    response?.let { Session.setLogin(it.email, it.password) }
                    Session.setUser(response)
                }   // 유저 정보 저장
                view.loginSuccess(isLogin, response)
            }

            override fun onFailure(message: String) {
                Log.e("[${javaClass.name}]", message)
            }
        })
    }
}