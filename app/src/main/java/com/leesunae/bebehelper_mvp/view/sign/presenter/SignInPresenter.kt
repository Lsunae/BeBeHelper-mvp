package com.leesunae.bebehelper_mvp.view.sign.presenter

import android.util.Log
import com.leesunae.bebehelper_mvp.data.model.UserItem
import com.leesunae.bebehelper_mvp.data.repository.Callback
import com.leesunae.bebehelper_mvp.data.repository.UserRepository
import com.leesunae.bebehelper_mvp.data.room.entity.User

class SignInPresenter(
    private val userRepository: UserRepository,
    private var view: SignInContract.View
) : SignInContract.Presenter {
    override fun login(user: UserItem) {
        userRepository.login(user.email, user.password, object : Callback<User> {
            override fun onSuccess(response: User) {
                println("presenter_sign_in_success_response_login_ $response")
                Log.i("[${javaClass.name}]", "$response")
            }

            override fun onFailure(message: String) {
                println("presenter_sign_in_failure_response_login_ $message")
                Log.e("[${javaClass.name}]", message)
            }
        })
    }
}