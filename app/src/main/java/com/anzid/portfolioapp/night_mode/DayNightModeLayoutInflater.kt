package com.anzid.portfolioapp.night_mode

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatDelegate

class DayNightModeLayoutInflater(
        private val delegate: AppCompatDelegate
) : LayoutInflater.Factory2 {

    override fun onCreateView(
            parent: View?,
            name: String,
            context: Context,
            attrs: AttributeSet
    ): View? {
        return when (name) {
            "TextView" -> DayNightModeTextView(context, attrs)
            else -> delegate.createView(parent, name, context, attrs)
        }
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return onCreateView(null, name, context, attrs)
    }
}