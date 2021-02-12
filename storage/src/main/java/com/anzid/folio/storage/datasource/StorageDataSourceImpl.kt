package com.anzid.folio.storage.datasource

import com.anzid.folio.storage.db.FolioDb
import com.anzid.folio.storage.db.ResumeMapper
import com.anzid.folio.storage.db.ResumeModel

internal class StorageDataSourceImpl(db: FolioDb,
                                     private val resumeMapper: ResumeMapper
) : StorageDataSource {

    private val resumeDao = db.resumeDao()

    override fun addResume(resumeModel: ResumeModel) {
        resumeDao.insert(resumeMapper.mapToEntity(resumeModel))
    }

}