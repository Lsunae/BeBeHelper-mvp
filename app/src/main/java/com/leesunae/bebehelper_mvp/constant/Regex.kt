package com.leesunae.bebehelper_mvp.constant

annotation class Regex {
    companion object {
        var EMAIL = "^[\\w-.]+@[\\w-]+[.][a-z]{2,8}([.][a-z]{2,3})?$"
        var PASSWORD =
            "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+=-?])[A-Za-z\\d!@#$%^&*()_+=-?]{8,}$"
        var URL =
            "^(?:http(s)?:\\/\\/)?[\\w.-]+(?:\\.[\\w\\.-]+)+[\\w\\-\\._~:\\/?#[\\]@!\\$&'\\(\\)\\*\\+,;=.]+$"
        var NICKNAME = "^[A-Z0-9a-z가-힣ㄱ-ㅎ]*$"
    }
}