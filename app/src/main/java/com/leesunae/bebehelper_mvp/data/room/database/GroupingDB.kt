package com.leesunae.bebehelper_mvp.data.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.leesunae.bebehelper_mvp.data.room.dao.GroupingDao
import com.leesunae.bebehelper_mvp.data.room.entity.Grouping


@Database(entities = [Grouping::class], version = 1)
abstract class GroupingDB : RoomDatabase() {
    abstract fun groupingDao(): GroupingDao

    companion object {
        private var INSTANCE: GroupingDB? = null
        fun getInstance(context: Context): GroupingDB {
            return INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                GroupingDB::class.java, "grouping.db"
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