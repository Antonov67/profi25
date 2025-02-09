package com.example.profi25.Presentation;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class OnBoardingAdapter extends FragmentStateAdapter {
    public OnBoardingAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return (PageFragment.newInstance(position));
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
