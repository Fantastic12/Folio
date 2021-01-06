package com.anzid.folio.storage.db.dao

import androidx.room.*
import com.anzid.folio.storage.db.entity.ResumeEntity
import com.anzid.utils.room.BaseDao
import com.dstarlab.dstarannotation.RoomDaoGeneration

//@RoomDaoGeneration(ResumeEntity.TABLE_NAME)
@Dao
interface ResumeDao : BaseDao<ResumeEntity>