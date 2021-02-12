package com.anzid.portfolioapp

import com.anzid.folio.storage.db.ResumeModel

interface ResumeRepository {

    fun saveResume(resumeModel: ResumeModel)
}