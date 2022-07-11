package com.leesunae.bebehelper_mvp.data.room.dao

import androidx.room.*
import com.leesunae.bebehelper_mvp.data.room.entity.User


@Dao
interface UserDao {

    @Insert
    fun insertAll(vararg user: User?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User): Long

    @Update
    fun updateUser(user: User)

    @Query("DELETE FROM User WHERE email = :email")
    fun deleteUser(email: String)

    @Query("SELECT * FROM User WHERE email = :email")
    fun getUser(email: String): User

    @Query("SELECT * FROM User")
    fun getAll(): List<User>
}