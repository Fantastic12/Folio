package com.anzid.portfolioapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.ImageView;

import com.anzid.portfolioapp.sidemenu.Callback;
import com.anzid.portfolioapp.sidemenu.DepthPageTransformer;
import com.anzid.portfolioapp.sidemenu.MenuAdapter;
import com.anzid.portfolioapp.sidemenu.MenuItem;
import com.anzid.portfolioapp.sidemenu.MenuUtil;
import com.anzid.portfolioapp.sidemenu.ScreenSlidePagerAdapter;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import kotlin.Unit;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements Callback {

    RecyclerView menuRv;
    ImageView nightMode;
    ViewPager2 viewPager2;
    List<MenuItem> menuItems;
    MenuAdapter adapter;
    MainViewPagerOnChangeListener listener;
    int selectedMenuPos = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        // setup side menu
        setupSideMenu();
        initNightMode();
    }

    private void initNightMode() {
        nightMode = findViewById(R.id.night_mode);

    }

    private void setupSideMenu() {
        menuRv = findViewById(R.id.rv_side_menu);

        // get menu list item  will get data from a static data class

        menuItems = MenuUtil.getMenuList();
        adapter = new MenuAdapter(menuItems,this);
        menuRv.setLayoutManager(new LinearLayoutManager(this));
        menuRv.setAdapter(adapter);

        initViewPager();
    }

    private void initViewPager() {
        viewPager2 = findViewById(R.id.view_pager);

        viewPager2.setAdapter(new ScreenSlidePagerAdapter(this));
        viewPager2.setPageTransformer(new DepthPageTransformer());
        viewPager2.setOffscreenPageLimit(5);
        viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        initMainViewPagerOnChangeListener();
    }

    private void initMainViewPagerOnChangeListener() {
        listener = new MainViewPagerOnChangeListener(position -> {
            updateMenu(position);
            return Unit.INSTANCE;
        });
        viewPager2.registerOnPageChangeCallback(listener);
    }

    void setPortfoliofragment() {
        viewPager2.setCurrentItem(4);
    }

    void setTeamFragment () {
        viewPager2.setCurrentItem(3);
    }

    void setCVFragment(int i) {
        viewPager2.setCurrentItem(i);
    }

    void setHomeFragment() {
        viewPager2.setCurrentItem(0);
    }

    @Override
    public void onSideMenuItemClick(int i) {
        if (selectedMenuPos == i) return;

        switch (menuItems.get(i).getCode()) {
            case MenuUtil.EDUCATION_FRAGMENT_CODE :
            case MenuUtil.WORK_EXP_FRAGMENT_CODE :
                setCVFragment(i);
            break;
            case MenuUtil.TEAM_FRAGMENT_CODE: setTeamFragment();
            break;
            case MenuUtil.PORTFOLIO_FRAGMENT_CODE:setPortfoliofragment();
            break;
            default: setHomeFragment();
        }
        updateMenu(i);
    }

    private void updateMenu(int newPosition) {
        menuItems.get(selectedMenuPos).setSelected(false);
        menuItems.get(newPosition).setSelected(true);
        selectedMenuPos = newPosition ;
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (viewPager2 != null) viewPager2.unregisterOnPageChangeCallback(listener);
    }
}