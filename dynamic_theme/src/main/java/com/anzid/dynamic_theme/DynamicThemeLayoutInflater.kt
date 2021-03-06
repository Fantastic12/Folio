package com.anzid.dynamic_theme

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import com.anzid.dynamic_theme.views.DynamicThemeCardView
import com.anzid.dynamic_theme.views.DynamicThemeTextView

internal class DynamicThemeLayoutInflater(
        private val delegate: AppCompatDelegate
) : LayoutInflater.Factory2 {

    override fun onCreateView(
            parent: View?,
            name: String,
            context: Context,
            attrs: AttributeSet
    ): View? {
        return when (name) {
            "TextView" -> DynamicThemeTextView(context, attrs)
            "com.google.android.material.card.MaterialCardView" -> DynamicThemeCardView(context, attrs)
            else -> delegate.createView(parent, name, context, attrs)
        }
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return onCreateView(null, name, context, attrs)
    }
}