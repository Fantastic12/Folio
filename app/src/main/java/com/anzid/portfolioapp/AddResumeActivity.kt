package com.anzid.portfolioapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anzid.portfolioapp.themes.getSelectedTheme
import kotlinx.android.synthetic.main.activity_add_resume.*

class AddResumeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_resume)

        supportActionBar?.hide()

        window.statusBarColor = getSelectedTheme().colorPrimary
        container.setBackgroundColor(getSelectedTheme().colorPrimary)

    }
}