package com.leesunae.bebehelper_mvp.view.sign.presenter

import android.util.Log
import com.leesunae.bebehelper_mvp.data.model.UserItem
import com.leesunae.bebehelper_mvp.data.repository.Callback
import com.leesunae.bebehelper_mvp.data.repository.user.UserRepository
import com.leesunae.bebehelper_mvp.data.room.entity.User

class SignUpPresenter(
    private val userRepository: UserRepository,
    private val view: SignUpContract.View
) :
    SignUpContract.Presenter {
    override fun createUser(user: UserItem) {
        userRepository.createUser(
            user.email,
            user.password,
            user.nickname,
            user.gender,
            user.childGender,
            user.ageOfChildren,
            user.area,
            null,
            object : Callback<Boolean> {
                override fun onSuccess(response: Boolean) {
                    Log.i("[${javaClass.name}]", "$response")
                    view.createUserSuccess(response)
                }

                override fun onFailure(message: String) {
                    Log.e("[${javaClass.name}]", message)
                }
            })
    }

    override fun getUserAll() {
        userRepository.getUserAll(object : Callback<List<User>> {
            override fun onSuccess(response: List<User>) {
                Log.i("[${javaClass.name}]", "$response")
            }

            override fun onFailure(message: String) {
                Log.e("[${javaClass.name}]", message)
            }
        })
    }

    override fun checkEmail(email: String) {
        userRepository.checkEmail(email, object : Callback<Boolean> {
            override fun onSuccess(response: Boolean) {
                Log.i("[${javaClass.name}]", "$response")
                view.checkedEmail(email, response)
            }

            override fun onFailure(message: String) {
                Log.e("[${javaClass.name}]", message)
                view.checkedEmail(email, false)
            }
        })
    }

    override fun checkNickname(nickName: String) {
        userRepository.checkNickname(nickName, object : Callback<Boolean> {
            override fun onSuccess(response: Boolean) {
                Log.i("[${javaClass.name}]", "$response")
                view.checkedNickname(nickName, response)
            }

            override fun onFailure(message: String) {
                Log.e("[${javaClass.name}]", message)
                view.checkedNickname(nickName, false)
            }
        })
    }
}