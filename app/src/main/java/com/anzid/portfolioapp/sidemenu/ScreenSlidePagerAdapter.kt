package com.anzid.portfolioapp.sidemenu

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.anzid.portfolioapp.cv.CVFragment
import com.anzid.portfolioapp.home.HomeFragment
import com.anzid.portfolioapp.portfolio.PortfolioFragment
import com.anzid.portfolioapp.team.TeamFragment

/**
 * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
 * sequence.
 */
class ScreenSlidePagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0-> HomeFragment()
            1-> CVFragment()
            2-> CVFragment()
            3-> TeamFragment()
            4-> PortfolioFragment()
            else -> HomeFragment()
        }
    }
}