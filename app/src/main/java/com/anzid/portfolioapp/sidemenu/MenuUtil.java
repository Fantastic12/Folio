package com.anzid.portfolioapp.sidemenu;

import com.anzid.portfolioapp.R;

import java.util.ArrayList;
import java.util.List;

public class MenuUtil {

    public static final int HOME_FRAGMENT_CODE = 0;
    public static final int EDUCATION_FRAGMENT_CODE = 1;
    public static final int WORK_EXP_FRAGMENT_CODE = 2;
    public static final int PORTFOLIO_FRAGMENT_CODE = 3;
    public static final int TEAM_FRAGMENT_CODE = 4;


    public static List<MenuItem> getMenuList() {

        List<MenuItem> list = new ArrayList<>();
        // first menu item is selected
        list.add(new MenuItem(R.drawable.ic_baseline_home_24, HOME_FRAGMENT_CODE, true));
        list.add(new MenuItem(R.drawable.ic_baseline_school_24, EDUCATION_FRAGMENT_CODE, false));
        list.add(new MenuItem(R.drawable.ic_work_black_24dp, WORK_EXP_FRAGMENT_CODE, false));
        list.add(new MenuItem(R.drawable.ic_baseline_group_24, TEAM_FRAGMENT_CODE, false));
        list.add(new MenuItem(R.drawable.ic_baseline_dashboard_24, PORTFOLIO_FRAGMENT_CODE, false));

        return list;
    }


}
