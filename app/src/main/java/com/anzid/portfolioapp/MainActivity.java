package com.anzid.portfolioapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.anzid.portfolioapp.cv.CVFragment;
import com.anzid.portfolioapp.home.HomeFragment;
import com.anzid.portfolioapp.portfolio.PortfolioFragment;
import com.anzid.portfolioapp.sidemenu.Callback;
import com.anzid.portfolioapp.sidemenu.DepthPageTransformer;
import com.anzid.portfolioapp.sidemenu.MenuAdapter;
import com.anzid.portfolioapp.sidemenu.MenuItem;
import com.anzid.portfolioapp.sidemenu.MenuUtil;
import com.anzid.portfolioapp.sidemenu.ScreenSlidePagerAdapter;
import com.anzid.portfolioapp.team.TeamFragment;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

import static androidx.viewpager2.widget.ViewPager2.SCROLL_STATE_DRAGGING;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements Callback {

    RecyclerView menuRv;
    ViewPager2 viewPager2;
    List<MenuItem> menuItems;
    MenuAdapter adapter;
    int selectedMenuPos = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        // setup side menu
        setupSideMenu();


        // set the default fragment when activity launch
//        setHomeFragment();



    }

    private void setupSideMenu() {

        menuRv = findViewById(R.id.rv_side_menu);
        viewPager2 = findViewById(R.id.view_pager);

        // get menu list item  will get data from a static data class

        menuItems = MenuUtil.getMenuList();
        adapter = new MenuAdapter(menuItems,this);
        menuRv.setLayoutManager(new LinearLayoutManager(this));
        menuRv.setAdapter(adapter);
        viewPager2.setAdapter(new ScreenSlidePagerAdapter(this));
        viewPager2.setPageTransformer(new DepthPageTransformer());
        viewPager2.setOffscreenPageLimit(5);
        viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                menuItems.get(selectedMenuPos).setSelected(false);
                menuItems.get(position).setSelected(true);
                selectedMenuPos = position ;
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager2.SCROLL_STATE_DRAGGING) {
                    // Prevent the ScrollView from intercepting this event now that the page is changing.
                    // When this drag ends, the ScrollView will start accepting touch events again.
//                    sv.requestDisallowInterceptTouchEvent(true);
                }
            }
        });
    }

    void setPortfoliofragment() {
        viewPager2.setCurrentItem(4);

//        getSupportFragmentManager().beginTransaction().replace(R.id.container,new PortfolioFragment()).commit();

    }

    void setTeamFragment () {
        viewPager2.setCurrentItem(3);

//        getSupportFragmentManager().beginTransaction().replace(R.id.container,new TeamFragment()).commit();
    }

    void setCVFragment(int i) {
        viewPager2.setCurrentItem(i);

//        getSupportFragmentManager().beginTransaction().replace(R.id.container,new CVFragment()).commit();
    }


    void setHomeFragment() {

        viewPager2.setCurrentItem(0);
//        getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment()).commit();

    }


    @Override
    public void onSideMenuItemClick(int i) {

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

        // hightligh the selected menu item

        menuItems.get(selectedMenuPos).setSelected(false);
        menuItems.get(i).setSelected(true);
        selectedMenuPos = i ;
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        viewPager2.unregisterOnPageChangeCallback();
    }
}
