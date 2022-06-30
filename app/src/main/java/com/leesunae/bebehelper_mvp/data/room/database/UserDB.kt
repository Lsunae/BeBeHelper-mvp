package com.leesunae.bebehelper_mvp.data.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.leesunae.bebehelper_mvp.data.room.dao.UserDao
import com.leesunae.bebehelper_mvp.data.room.entity.UserEntity


@Database(entities = [UserEntity::class], version = 1)
abstract class UserDB : RoomDatabase() {
    abstract fun userDao(): UserDao?

    companion object {
        private var INSTANCE: UserDB? = null
        fun getInstance(context: Context): UserDB? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    UserDB::class.java, "user.db"
                ).build()
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}