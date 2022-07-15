package com.leesunae.bebehelper_mvp.view.sign.presenter

import android.util.Log
import com.leesunae.bebehelper_mvp.Session
import com.leesunae.bebehelper_mvp.data.repository.Callback
import com.leesunae.bebehelper_mvp.data.repository.UserRepository
import com.leesunae.bebehelper_mvp.data.room.entity.User

class SignInPresenter(
    private val userRepository: UserRepository,
    private var view: SignInContract.View
) : SignInContract.Presenter {
    override fun login(email: String, password: String) {
        userRepository.login(email, password, object : Callback<User?> {
            override fun onSuccess(response: User?) {
                println("presenter_sign_in_success_response_login_ $response")
                Log.i("[${javaClass.name}]", "$response")
                val isLogin: Boolean = response != null && response.id > 0 && email == response.email && password == response.password
                println("presenter_sign_in_success_response_isLogin_ $isLogin")
                if (isLogin) {
                    response?.let { Session.setLoginId(it.id) }
                    Session.setUser(response)
                }   // 유저 정보 저장
                view.loginSuccess(isLogin, response)
            }

            override fun onFailure(message: String) {
                println("presenter_sign_in_failure_response_login_ $message")
                Log.e("[${javaClass.name}]", message)
            }
        })
    }
}