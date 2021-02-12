package com.anzid.portfolioapp.add_resume

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.anzid.folio.storage.db.ResumeModel
import com.anzid.folio.storage.db.models.ResumeDataModel
import com.anzid.portfolioapp.ResumeRepository

class AddResumeViewModel @ViewModelInject constructor(
        private val resumeRepository: ResumeRepository
) : ViewModel() {


    fun handleSaveResume(
            name: String,
            position: String,
            description: String
    ) {
        if (name.isEmpty() || position.isEmpty() || description.isEmpty()) {
            return
        }

        val resumeDataModel = ResumeDataModel(
                position, position, position, position, listOf(), position, listOf()
        )
        val resume = ResumeModel(
                0, name, "",
                position, description,
                resumeDataModel
        )
        resumeRepository.saveResume(resume)
    }

}