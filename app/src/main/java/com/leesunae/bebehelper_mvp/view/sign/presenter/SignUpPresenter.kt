package com.leesunae.bebehelper_mvp.view.sign.presenter

import android.util.Log
import com.leesunae.bebehelper_mvp.data.repository.Callback
import com.leesunae.bebehelper_mvp.data.repository.UserRepository
import com.leesunae.bebehelper_mvp.data.room.entity.User

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

    override fun getUserAll() {
        userRepository.getUserAll(object : Callback<List<User>> {
            override fun onSuccess(response: List<User>) {
                println("presenter_sign_up_success_response_getUserAll_ $response")
                Log.i("[${javaClass.name}]", "$response")
            }

            override fun onFailure(message: String) {
                println("presenter_sign_up_failure_response_getUserAll_ $message")
                Log.e("[${javaClass.name}]", message)
            }
        })
    }

    override fun checkEmail(email: String): Boolean {
        userRepository.checkEmail(email, object : Callback<Boolean> {
            override fun onSuccess(response: Boolean) {
                println("presenter_sign_up_success_checkEmail_ $response")
                Log.i("[${javaClass.name}]", "$response")
                view.checkedEmail(email, response)
            }

            override fun onFailure(message: String) {
                println("presenter_sign_up_failure_checkEmail_ $message")
                Log.e("[${javaClass.name}]", message)
                view.checkedEmail(email, false)
            }
        })
    }

    override fun checkNickname(nickName: String) {
        userRepository.checkNickname(nickName, object : Callback<Boolean> {
            override fun onSuccess(response: Boolean) {
                println("presenter_sign_up_success_checkNickname_ $response")
                Log.i("[${javaClass.name}]", "$response")
                view.checkedNickname(nickName, response)
            }

            override fun onFailure(message: String) {
                println("presenter_sign_up_failure_checkNickname_ $message")
                Log.e("[${javaClass.name}]", message)
                view.checkedNickname(nickName, false)
            }
        })
    }
}