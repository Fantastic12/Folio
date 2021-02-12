package com.anzid.folio.storage.db.dao

import androidx.room.*
import com.anzid.folio.storage.db.entity.ResumeEntity
import com.anzid.utils.room.BaseDao

@Dao
internal interface ResumeDao : BaseDao<ResumeEntity>