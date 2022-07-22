package com.leesunae.bebehelper_mvp.data.room.dao

import androidx.room.*
import com.leesunae.bebehelper_mvp.data.room.entity.Group


@Dao
interface GroupDao {

    @Insert
    fun insertAll(vararg group: Group?)

    /** 그룹 추가 */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(group: Group): Long

    /** 그룹 정보 업데이트 */
    @Update
    fun updateGroup(group: Group)

    /** 그룹 삭제 */
    @Query("DELETE FROM `Group` WHERE id = :id")
    fun deleteGroup(id: Int)

    /** 그룹 조회 */
    @Query("SELECT * FROM `Group` WHERE id = :id")
    fun getUser(id: Int): Group

    /** 그룹 목록 조회 */
    @Query("SELECT * FROM `Group`")
    fun getAll(): List<Group>
}