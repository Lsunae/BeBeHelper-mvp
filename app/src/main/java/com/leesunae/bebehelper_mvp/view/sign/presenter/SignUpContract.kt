package com.leesunae.bebehelper_mvp.view.sign.presenter

interface SignUpContract {
    interface View {
        fun showMessage(message: Boolean)
        fun checkedEmail(isChecked: Boolean)
        fun checkedNickname(isChecked: Boolean)
    }

    interface Presenter {
        fun createUser(
            email: String,
            password: String,
            nickName: String
        )

        fun getUserAll()

        /** 이메일 체크 */
        fun checkEmail(email: String)

        /** 닉네임 체크 */
        fun checkNickname(nickName: String)
    }
}