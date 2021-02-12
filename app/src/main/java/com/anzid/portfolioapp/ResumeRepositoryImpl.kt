package com.anzid.portfolioapp

import com.anzid.folio.storage.datasource.StorageDataSource
import com.anzid.folio.storage.db.ResumeModel
import javax.inject.Inject

class ResumeRepositoryImpl @Inject constructor(
        private val storage: StorageDataSource
) : ResumeRepository {

    override fun saveResume(resumeModel: ResumeModel) {
        storage.addResume(resumeModel)
    }
}