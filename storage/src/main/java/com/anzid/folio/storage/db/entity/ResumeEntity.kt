package com.anzid.folio.storage.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.anzid.folio.storage.db.entity.ResumeEntity.Companion.TABLE_NAME
import com.anzid.utils.utils.AppState

@Entity(tableName = TABLE_NAME)
data class ResumeEntity(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = ID)
        val id: Long,
        val name: String,
        val iconLink: String,
        val jobPosition: String,
        val resumeDescription: String,
        val currentOrLastJobPosition: String,
        val dataModel: String) {

    companion object {
        const val TABLE_NAME = "resume_table"
        const val ID = "id"
    }
}