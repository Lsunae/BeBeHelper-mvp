package com.leesunae.bebehelper_mvp.data.model

data class GroupingItem(
    var id: Int = 0,
    var title: String = "",
    var area: String = "",
    var ageLimit: String = "",
    var childCount: Int = 0,
    var content: String = "",
    var writerId: Int = 0,
    var writerNickname: String = ""
)
