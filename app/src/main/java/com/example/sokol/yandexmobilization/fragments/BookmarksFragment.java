package com.example.sokol.yandexmobilization.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sokol.yandexmobilization.R;

/**
 * Created by sokol on 18.03.2017.
 */

public class BookmarksFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_bookmarks, container, false);

        FragmentPagerAdapter pagerAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            public final CharSequence FAVOURITES_TITLE = "Favourites";
            public final CharSequence HISTORY_TITLE = "History";

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position){
                    case 0:
                        return HISTORY_TITLE;
                    default:
                        return FAVOURITES_TITLE;
                }
            }

            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0:
                        return new HistoryFragment();
                    default:
                        return new FavouritesFragment();
                }
            }
            @Override
            public int getCount() {
                return 2;
            }
        };

        Toolbar toolbar = (Toolbar) container.findViewById(R.id.toolbar_bookmarks);
        AppCompatActivity parent = (AppCompatActivity)getActivity();
        parent.setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.viewpager_bookmarks);
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.tab_layout_bookmarks);
        tabLayout.setupWithViewPager(viewPager);

        return rootView;
    }
}
