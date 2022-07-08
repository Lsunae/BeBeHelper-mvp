package com.leesunae.bebehelper_mvp.view.sign.presenter

import android.util.Log
import com.leesunae.bebehelper_mvp.data.repository.Callback
import com.leesunae.bebehelper_mvp.data.repository.UserRepository

class SignUpPresenter(
    private val userRepository: UserRepository,
    private val view: SignUpContract.View
) :
    SignUpContract.Presenter {
    override fun createUser(
        email: String,
        password: String,
        nickName: String
    ) {
        println("presenter_sign_up_ $userRepository")
        userRepository.createUser(
            email,
            password,
            nickName,
            null,
            null,
            null,
            null,
            null,
            object : Callback<Boolean> {
                override fun onSuccess(response: Boolean) {
                    println("presenter_sign_up_success_response $response")
                    Log.i("[${javaClass.name}]", "$response")
                    view.showMessage(response)
                }

                override fun onFailure(message: String) {
                    println("presenter_sign_up_failure_response $message")
                    Log.e("[${javaClass.name}]", message)
                }
            })
    }

    override fun checkEmail(email: String) {

    }

    override fun checkNickname(nickName: String) {

    }

    override fun getUser() {

    }
}