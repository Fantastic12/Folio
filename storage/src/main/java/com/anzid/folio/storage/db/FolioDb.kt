package com.anzid.folio.storage.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.anzid.folio.storage.db.dao.ResumeDao
import com.anzid.folio.storage.db.entity.ResumeEntity

@Database(entities = [ResumeEntity::class],
        version = FolioDb.DB_VERSION,
        exportSchema = true)
internal abstract class FolioDb : RoomDatabase() {

    abstract fun resumeDao(): ResumeDao

    companion object {
        const val DB_VERSION = 1
        private const val DB_NAME = "folio_db"

        @Volatile
        private lateinit var INSTANCE: FolioDb

        @Synchronized
        fun getDatabase(context: Context): FolioDb {
            if (Companion::INSTANCE.isInitialized.not()) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, FolioDb::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .fallbackToDestructiveMigrationOnDowngrade()
                        .build()
            }

            return INSTANCE
        }
    }
}