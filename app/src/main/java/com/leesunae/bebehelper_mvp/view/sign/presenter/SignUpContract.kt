package com.leesunae.bebehelper_mvp.view.sign.presenter

import com.leesunae.bebehelper_mvp.data.model.UserItem

interface SignUpContract {
    interface View {
        fun createUserSuccess(message: Boolean)
        fun checkedEmail(email: String, isChecked: Boolean)
        fun checkedNickname(nickname: String, isChecked: Boolean)
    }

    interface Presenter {
        fun createUser(user: UserItem)

        fun getUserAll()

        /** 이메일 체크 */
        fun checkEmail(email: String)

        /** 닉네임 체크 */
        fun checkNickname(nickName: String)
    }
}