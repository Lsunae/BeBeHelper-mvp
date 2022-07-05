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
                    Log.i("[${javaClass.name}]", "$response")
                    view.showMessage(response)
                }

                override fun onFailure(message: String) {
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