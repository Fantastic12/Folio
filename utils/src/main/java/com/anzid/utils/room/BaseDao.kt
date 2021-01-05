package com.anzid.utils.room

import androidx.room.*

interface BaseDao<ENTITY> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: ENTITY): Long

    @Insert
    fun insert(vararg obj: ENTITY)

    @Update
    fun update(entity: ENTITY)

    @Delete
    fun delete(entity: ENTITY)

}