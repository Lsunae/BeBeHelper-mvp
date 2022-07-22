package com.leesunae.bebehelper_mvp.data.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.leesunae.bebehelper_mvp.data.room.dao.GroupDao
import com.leesunae.bebehelper_mvp.data.room.entity.Group


@Database(entities = [Group::class], version = 1)
abstract class GroupDB : RoomDatabase() {
    abstract fun groupDao(): GroupDao

    companion object {
        private var INSTANCE: GroupDB? = null
        fun getInstance(context: Context): GroupDB {
            return INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                GroupDB::class.java, "user.db"
            )
                .fallbackToDestructiveMigration()
                .build()
                .also {
                    INSTANCE = it
                }
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}