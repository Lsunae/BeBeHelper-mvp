package com.leesunae.bebehelper_mvp.view.sign.presenter

import com.leesunae.bebehelper_mvp.data.room.entity.User

interface SignInContract {
    interface View {
        fun loginSuccess(isLogin: Boolean, user: User?)
    }

    interface Presenter {

        fun login(email: String, password: String)

    }
}