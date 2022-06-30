package com.leesunae.bebehelper_mvp.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import com.leesunae.bebehelper_mvp.data.room.entity.UserEntity


@Dao
interface UserDao {
    @Insert
    fun insertAll(vararg user: UserEntity?)

    @Delete
    fun delete(user: UserEntity?)
}