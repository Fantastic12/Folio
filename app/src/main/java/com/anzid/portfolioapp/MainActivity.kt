package com.anzid.portfolioapp

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.LayoutInflaterCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.anzid.portfolioapp.sidemenu.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.hypot

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Callback {
    
    private var menuItems: List<MenuItem> = emptyList()
    lateinit var adapter: MenuAdapter
    lateinit var listener: MainViewPagerOnChangeListener
    
    var selectedMenuPos = 0
    
    override fun onCreate(savedInstanceState: Bundle?) {
        LayoutInflaterCompat.setFactory2(
                LayoutInflater.from(this),
                MyLayoutInflater(delegate)
        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        // setup side menu
        setupSideMenu()
        initNightMode()
    }

    private fun initNightMode() {
        night_mode.setOnClickListener {
            val newTheme = when (ThemeManager.theme) {
                DayNightMode.NIGHT -> DayNightMode.DAY
                DayNightMode.DAY -> DayNightMode.NIGHT
            }
            updateDayNightMode(newTheme)
//            FOLIO_APP.updateThemeMode()
        }
    }

    private fun setupSideMenu() {

        // get menu list item  will get data from a static data class
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


    private fun updateDayNightMode(dayNightMode: DayNightMode) {
        if (screen.visibility == View.VISIBLE) {
            return
        }
        val w: Int = main_container.measuredWidth
        val h: Int = main_container.measuredHeight
        val bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)

        val canvas = Canvas(bitmap)
        main_container.draw(canvas)
        screen.setImageBitmap(bitmap)
        screen.visibility = View.VISIBLE

        val finalRadius = hypot(w.toFloat(), h.toFloat())
        val x: Int = night_mode.left
        val y: Int = night_mode.top

        ThemeManager.theme = dayNightMode
        updateCalor()

        val anim: Animator = ViewAnimationUtils.createCircularReveal(screen, x, y, finalRadius, 0f)
        anim.duration = 1000
        anim.interpolator = Easings.easeInOutQuad
        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                screen.setImageDrawable(null)
                screen.visibility = View.GONE
                night_mode.setImageRes(ThemeManager.theme)
            }
        })
        anim.start()


    }

    private var isNight = false

    fun updateCalor() {
        if(isNight) {
            window.statusBarColor = ContextCompat.getColor(this,android.R.color.white)
            isNight = false
        } else {
            window.statusBarColor = ContextCompat.getColor(this,android.R.color.black)
            isNight = true
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        if (view_pager != null) view_pager.unregisterOnPageChangeCallback(listener)
    }
}