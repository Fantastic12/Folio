package com.anzid.folio.storage.db

import androidx.annotation.Keep
import com.anzid.folio.storage.db.models.ResumeDataModel

@Keep
data class ResumeModel(val id: Long,
                       val name: String,
                       val iconLink: String,
                       val jobPosition: String,
                       val resumeDescription: String,
                       val dataModel: ResumeDataModel)