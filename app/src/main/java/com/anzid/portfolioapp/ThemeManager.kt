package com.anzid.portfolioapp

object ThemeManager {

    private val listeners = mutableSetOf<ThemeChangedListener>()
    var theme = getTheme(FolioApp.FOLIO_APP)
        set(value) {
            field = value
            listeners.forEach { listener -> listener.onThemeChanged(value) }
        }
        
    interface ThemeChangedListener {
        fun onThemeChanged(theme: DayNightMode)
    }
    
    fun addListener(listener: ThemeChangedListener) {
        listeners.add(listener)
    }

    fun removeListener(listener: ThemeChangedListener) {
        listeners.remove(listener)
    }
}