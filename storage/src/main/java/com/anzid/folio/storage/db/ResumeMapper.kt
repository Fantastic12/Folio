package com.anzid.folio.storage.db

import com.anzid.folio.storage.db.entity.ResumeEntity
import com.anzid.folio.storage.db.models.ResumeDataModel
import com.google.gson.GsonBuilder

internal class ResumeMapper : Mapper<ResumeEntity, ResumeModel> {

    override fun mapToEntity(model: ResumeModel): ResumeEntity = with(model) {
        ResumeEntity(
                id, name, iconLink, jobPosition, resumeDescription, currentOrLastJobPosition, dataModel.toJson()
        )
    }

    override fun mapToModel(entity: ResumeEntity): ResumeModel = with(entity) {
        ResumeModel(id, name, iconLink, jobPosition, resumeDescription, currentOrLastJobPosition,
                GsonBuilder().create().fromJson(dataModel, ResumeDataModel::class.java)
        )
    }
}