package com.leesunae.bebehelper_mvp.data.model

data class UserItem(
    var email: String = "",
    var password: String = "",
    var passwordConfirm: String = "",
    var nickname: String = "",
    var gender: String = "",
    var childGender: String = "",
    var ageOfChildren: String = "",
    var area: String = "",
    var image: String = "",
    var isCheckedEmail: Boolean = false,
    var isCheckedPassword: Boolean = false,
    var isCheckedNickname: Boolean = false,
)
