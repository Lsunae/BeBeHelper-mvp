package com.leesunae.bebehelper_mvp.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "email")
    var email: String = "",

    @ColumnInfo(name = "password")
    var password: String = "",

    @ColumnInfo(name = "nickname")
    var nickname: String = "",

    @ColumnInfo(name = "gender")
    var gender: String? = null,

    @ColumnInfo(name = "childGender")
    var childGender: String? = null,

    @ColumnInfo(name = "ageOfChildren")
    var ageOfChildren: String? = "",

    @ColumnInfo(name = "area")
    var area: String? = null,

    @ColumnInfo(name = "image")
    var image: String? = null
)