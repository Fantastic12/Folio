package com.anzid.day_night_mode.views.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceFragmentCompat
import com.anzid.day_night_mode.addThemeListener
import com.anzid.day_night_mode.dayNightMode
import com.anzid.day_night_mode.removeThemeListener
import com.anzid.day_night_mode.theme.ThemeChangedListener

abstract class BaseDayNightModePreferenceFragment : PreferenceFragmentCompat(), ThemeChangedListener {

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