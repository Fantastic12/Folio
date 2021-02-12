package com.anzid.folio.storage.datasource

import com.anzid.folio.storage.db.ResumeModel

interface StorageDataSource {

    fun addResume(resumeModel: ResumeModel)
}