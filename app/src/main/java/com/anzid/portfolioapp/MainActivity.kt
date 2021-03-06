package com.anzid.portfolioapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.anzid.dynamic_theme.DynamicThemeInitializer
import com.anzid.dynamic_theme.day_night_mode.DayNightModeConfiguration
import com.anzid.portfolioapp.sidemenu.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Callback {

    private var menuItems: List<MenuItem> = emptyList()
    lateinit var adapter: MenuAdapter
    lateinit var listener: MainViewPagerOnChangeListener

    var selectedMenuPos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        DynamicThemeInitializer.getDynamicThemeManager().initModeInflater(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        initConfigDynamicMode()
        setupSideMenu()
        initListeners()
    }

    override fun onResume() {
        super.onResume()
        DynamicThemeInitializer.getDynamicThemeManager().updateStatusBar(this)
    }

    private fun initListeners() {
        settings.setOnClickListener {
            Intent(this, SettingsActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    private fun initConfigDynamicMode() {
        DayNightModeConfiguration.Builder(this)
                .setAnimationDuration(500)
                .setMainContainer(main_container)
                .setScreenPlaceholder(screen)
                .setSunnyOrMoonImageView(night_mode)
                .configure()
    }

    private fun setupSideMenu() {
        menuItems = MenuUtil.getMenuList()
        adapter = MenuAdapter(menuItems, this)
        rv_side_menu.layoutManager = LinearLayoutManager(this)
        rv_side_menu.adapter = adapter
        initViewPager()
    }

    private fun initViewPager() {
        view_pager.adapter = ScreenSlidePagerAdapter(this)
        view_pager.setPageTransformer(DepthPageTransformer())
        view_pager.offscreenPageLimit = 5
        view_pager.orientation = ViewPager2.ORIENTATION_VERTICAL
        initMainViewPagerOnChangeListener()
    }

    private fun initMainViewPagerOnChangeListener() {
        listener = MainViewPagerOnChangeListener { position: Int ->
            updateMenu(position)
        }
        view_pager.registerOnPageChangeCallback(listener)
    }

    fun setPortfoliofragment() {
        view_pager.currentItem = 4
    }

    private fun setTeamFragment() {
        view_pager.currentItem = 3
    }

    private fun setCVFragment(i: Int) {
        view_pager.currentItem = i
    }

    private fun setHomeFragment() {
        view_pager.currentItem = 0
    }

    override fun onSideMenuItemClick(i: Int) {
        if (selectedMenuPos == i) return
        when (menuItems[i].code) {
            MenuUtil.EDUCATION_FRAGMENT_CODE, MenuUtil.WORK_EXP_FRAGMENT_CODE -> setCVFragment(i)
            MenuUtil.TEAM_FRAGMENT_CODE -> setTeamFragment()
            MenuUtil.PORTFOLIO_FRAGMENT_CODE -> setPortfoliofragment()
            else -> setHomeFragment()
        }
        updateMenu(i)
    }

    private fun updateMenu(newPosition: Int) {
        menuItems[selectedMenuPos].isSelected = false
        menuItems[newPosition].isSelected = true
        selectedMenuPos = newPosition
        adapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (view_pager != null) view_pager.unregisterOnPageChangeCallback(listener)
    }
}