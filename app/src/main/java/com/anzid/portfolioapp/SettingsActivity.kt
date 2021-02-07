package com.anzid.portfolioapp

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.anzid.day_night_mode.DayNightMode
import com.anzid.day_night_mode.DayNightModeInitializer
import com.anzid.day_night_mode.views.base.BaseDynamicThemeActivity
import com.anzid.day_night_mode.views.base.BaseDynamicThemePreferenceFragment
import com.anzid.portfolioapp.themes.*
import kotlinx.android.synthetic.main.settings_activity.*

private const val TITLE_TAG = "settingsActivityTitle"

class SettingsActivity : BaseDynamicThemeActivity(),
        PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        DayNightModeInitializer.getDynamicThemeManager().initModeInflater(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.settings, HeaderFragment())
                    .commit()
        } else {
            title = savedInstanceState.getCharSequence(TITLE_TAG)
        }

        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount == 0) {
                title_settings.text = getString(R.string.title_activity_settings)
            }
        }
        supportActionBar?.hide()
        back_button.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Save current activity title so we can set it again after a configuration change
        outState.putCharSequence(TITLE_TAG, title)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onPreferenceStartFragment(
            caller: PreferenceFragmentCompat,
            pref: Preference
    ): Boolean {
        // Instantiate the new Fragment
        val args = pref.extras
        val fragment = supportFragmentManager.fragmentFactory.instantiate(
                classLoader,
                pref.fragment
        ).apply {
            arguments = args
            setTargetFragment(caller, 0)
        }
        // Replace the existing Fragment with the new Fragment
        supportFragmentManager.beginTransaction()
                .replace(R.id.settings, fragment)
                .addToBackStack(null)
                .commit()
        title_settings.text = pref.title
        return true
    }

    override fun onThemeChanged(mode: DayNightMode) {
        container.setBackgroundColor(mode.theme.colorPrimary)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(mode.theme.colorPrimary))
        window.statusBarColor = mode.theme.colorPrimary
        back_button.setColorFilter(mode.theme.primaryTextColor)
    }

    class HeaderFragment : BaseDynamicThemePreferenceFragment() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.header_preferences, rootKey)
        }

        override fun onThemeChanged(mode: DayNightMode) {
            findPreference<Preference>("theme_header")?.icon?.setTint(mode.theme.iconTint)
            findPreference<Preference>("sync_header")?.icon?.setTint(mode.theme.iconTint)
        }
    }

    class AppearanceFragment : BaseDynamicThemePreferenceFragment() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.appearance_preferences, rootKey)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            findPreference<ListPreference>("theme")?.setOnPreferenceChangeListener { preference, newValue ->
                val newTheme = when (newValue) {
                    "white" -> WhiteTheme
                    "black" -> BlackTheme
                    "dark" -> DarkTheme
                    "light" -> LightTheme
                    else -> throw AssertionError()
                }
                DayNightModeInitializer.getDynamicThemeManager().updateSelectedThemeAndModeIfNeeded(newTheme)
                true
            }
        }

        override fun onThemeChanged(mode: DayNightMode) {
            findPreference<ListPreference>("theme")?.icon?.setTint(mode.theme.iconTint)
        }
    }

    class SyncFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.sync_preferences, rootKey)
        }
    }
}