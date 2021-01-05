package com.anzid.folio.storage.datasource

import com.anzid.folio.storage.db.FolioDb
import com.anzid.folio.storage.db.ResumeMapper

internal class StorageDataSourceImpl(db: FolioDb,
                                     private val resumeMapper: ResumeMapper
) : StorageDataSource {

    private val resumeDao = db.resumeDao()


}