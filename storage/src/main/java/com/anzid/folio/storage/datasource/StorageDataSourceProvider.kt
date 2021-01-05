package com.anzid.folio.storage.datasource

import android.content.Context
import com.anzid.folio.storage.db.FolioDb
import com.anzid.folio.storage.db.ResumeMapper

object StorageDataSourceProvider {

    private lateinit var environmentDataSource: StorageDataSource

    fun provideEnvironmentDataSource(context: Context): StorageDataSource {
        if (::environmentDataSource.isInitialized.not())
            return StorageDataSourceImpl(
                    FolioDb.getDatabase(context), ResumeMapper()
            )
        return environmentDataSource
    }
}