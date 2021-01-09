package com.anzid.portfolioapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FolioApp : Application() {

    companion object {
        lateinit var FOLIO_APP: FolioApp
    }

    override fun onCreate() {
        FOLIO_APP = this
        super.onCreate()
    }
}