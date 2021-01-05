package com.anzid.folio.storage.db.dao

import androidx.room.*
import com.anzid.folio.storage.db.entity.ResumeEntity
import com.dstarlab.dstarannotation.RoomDaoGeneration

@RoomDaoGeneration(ResumeEntity.TABLE_NAME)
@Dao
interface ResumeDao : AnzidResumeDao<ResumeEntity>