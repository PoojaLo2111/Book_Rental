package com.example.bookrental;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabPageAdapter extends FragmentPagerAdapter {
    private Context context;
    public TabPageAdapter(Context context,@NonNull FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new ToRentFragment();
        }
        else {
            return new OnRentFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0){
            return "To Rent";
        }
        else {
            return "On Rent";
        }
    }
}
