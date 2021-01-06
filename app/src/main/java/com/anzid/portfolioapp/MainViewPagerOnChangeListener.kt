package com.anzid.portfolioapp

import androidx.viewpager2.widget.ViewPager2

class MainViewPagerOnChangeListener(private val onChange: (Int) -> Unit): ViewPager2.OnPageChangeCallback() {

    override fun onPageSelected(position: Int) {
        super.onPageSelected(position)
        onChange(position)
    }
}