package com.anzid.dynamic_theme.views.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.preference.PreferenceFragmentCompat
import com.anzid.dynamic_theme.addThemeListener
import com.anzid.dynamic_theme.dayNightMode
import com.anzid.dynamic_theme.removeThemeListener
import com.anzid.dynamic_theme.theme.ThemeChangedListener

abstract class BaseDynamicThemePreferenceFragment : PreferenceFragmentCompat(), ThemeChangedListener {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onThemeChanged(dayNightMode)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        addThemeListener(this)
    }

    override fun onDetach() {
        super.onDetach()
        removeThemeListener(this)
    }
}