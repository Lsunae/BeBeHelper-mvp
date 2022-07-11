package com.leesunae.bebehelper_mvp.view.sign.presenter

import com.leesunae.bebehelper_mvp.data.room.entity.User

interface SignUpContract {
    interface View {
        fun showMessage(message: Boolean)
        fun getUserAll(message: List<User>)
    }

    interface Presenter {
        fun createUser(
            email: String,
            password: String,
            nickName: String
        )

        fun checkEmail(email: String)
        fun checkNickname(nickName: String)

        fun getUserAll()
    }
}