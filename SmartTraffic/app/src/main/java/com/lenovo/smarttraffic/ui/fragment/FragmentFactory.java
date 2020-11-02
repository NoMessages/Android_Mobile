package com.lenovo.smarttraffic.ui.fragment;

import android.support.v4.app.Fragment;
import android.util.SparseArray;

/**
 * Fragment工厂类
 */
public class FragmentFactory {
    /**
     * 根据position生产不同的fragment
     * @param position
     * @return
     */
    private static FragmentFactory mFactory = null;
    public static FragmentFactory getInstance() {
        if (mFactory == null) {
            mFactory = new FragmentFactory();
        }
        return mFactory;
    }
    public Fragment getFragment(int position) {
        SparseArray<Fragment> map = new SparseArray<>();
        Fragment fragment = null;
        if (map.get(position) != null) {
            return map.get(position);
        }
        switch (position) {
            case 0:
                fragment = new FirstFragment();
                break;
            case 1:
                fragment = new SecondFragment();
                break;
            case 2:
                fragment = new ThirdFragment();
                break;
            case 3:
                fragment = new FourFragment();
                break;
            case 4:
                fragment = new FiveFragment();
                break;
            case 5:
                fragment = new SixFragment();
                break;
            case 6:
                fragment = new SevenFragment();
                break;
            case 7:
                fragment = new EightFragment();
                break;
        }
        map.put(position, fragment);
        return fragment;
    }
}