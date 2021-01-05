package com.anzid.folio.storage.db.models

import androidx.annotation.Keep
import com.google.gson.GsonBuilder

@Keep
data class ResumeDataModel(val currentOrLastJobPosition: String,
                           val currentOrLastJobPositionDescription: String,
                           val mainDiploma: String,
                           val portfolioDescription: String,
                           val portfolioProjects: List<PortfolioProject>,
                           val ourTeamDescription: String,
                           val currentOrLastTeammates: List<Teammate>,
) {

    fun toJson(): String = GsonBuilder().create().toJson(this)

}