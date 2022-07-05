package com.leesunae.bebehelper_mvp.view.sign.presenter

interface SignUpContract {
    interface View {
        fun showMessage(message: Boolean)
    }

    interface Presenter {
        fun createUser(
            email: String,
            password: String,
            nickName: String
        )

        fun checkEmail(email: String)
        fun checkNickname(nickName: String)

        fun getUser()
    }
}