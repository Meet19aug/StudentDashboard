package com.example.studentdashboard;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyFragmentAdapter extends FragmentStateAdapter {
    public MyFragmentAdapter(@NonNull androidx.fragment.app.FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){

            case 1:
                return new GeneralNoticeFragment();
            case 2:
                return new AddNoticeFragment();
            case 3:
                return new MapFragment();
            default:
                return new ProfileFragment();

        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
