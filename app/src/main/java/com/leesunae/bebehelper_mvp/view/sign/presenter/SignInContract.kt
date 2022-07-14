package com.leesunae.bebehelper_mvp.view.sign.presenter

import com.leesunae.bebehelper_mvp.data.model.UserItem

interface SignInContract {
    interface View {
        fun loginSuccess(message: Boolean)
    }

    interface Presenter {

        fun login(user: UserItem)

    }
}