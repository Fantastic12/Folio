package com.anzid.portfolioapp.add_resume

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.anzid.portfolioapp.R
import com.anzid.portfolioapp.themes.getSelectedTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_add_resume.*

@AndroidEntryPoint
class AddResumeActivity : AppCompatActivity() {

    private val viewModel by viewModels<AddResumeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_resume)

        supportActionBar?.hide()

        window.statusBarColor = getSelectedTheme().colorPrimary
        container.setBackgroundColor(getSelectedTheme().colorPrimary)
        initListeners()
    }

    private fun initListeners() {
        button.setOnClickListener {
            handleSaveClick()
        }
    }

    private fun handleSaveClick() {
        viewModel.handleSaveResume(
                name.text.toString(),
                position.text.toString(),
                description.text.toString()
        )
    }

}