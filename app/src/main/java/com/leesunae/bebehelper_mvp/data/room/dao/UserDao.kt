package com.leesunae.bebehelper_mvp.data.room.dao

import androidx.room.*
import com.leesunae.bebehelper_mvp.data.room.entity.User


@Dao
interface UserDao {

    @Insert
    fun insertAll(vararg user: User?)

    /** 유저 추가 */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User): Long

    /** 유저 정보 업데이트 */
    @Update
    fun updateUser(user: User)

    /** 유저 삭제 */
    @Query("DELETE FROM User WHERE email = :email")
    fun deleteUser(email: String)

    /** 유저 조회 */
    @Query("SELECT * FROM User WHERE email = :email and password = :password")
    fun getLoginUser(email: String, password: String): User

    /** 유저 조회 */
    @Query("SELECT * FROM User WHERE email = :email")
    fun getUser(email: String): User

    /** 유저 목록 조회 */
    @Query("SELECT * FROM User")
    fun getAll(): List<User>

    /** 이메일 체크 */
    @Query("SELECT * FROM User WHERE email = :email")
    fun checkEmail(email: String): List<User>?

    /** 닉네임 체크 */
    @Query("SELECT * FROM User WHERE nickname = :nickname")
    fun checkNickname(nickname: String): List<User>?
}