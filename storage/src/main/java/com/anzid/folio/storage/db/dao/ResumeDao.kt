package com.anzid.folio.storage.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.anzid.folio.storage.db.entity.ResumeEntity
import com.anzid.utils.room.BaseDao

@Dao
internal interface ResumeDao : BaseDao<ResumeEntity> {

    @Query(value = "SELECT * FROM ${ResumeEntity.TABLE_NAME}")
    fun getAllResumes(): LiveData<List<ResumeEntity>>

    @Query(value = "SELECT * FROM ${ResumeEntity.TABLE_NAME}")
    fun getListResumes(): List<ResumeEntity>

    @Query(value = "DELETE FROM ${ResumeEntity.TABLE_NAME}")
    fun cleanTable()
}