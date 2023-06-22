package com.example.myapp.TabFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapp.Fragment.Fragment_SACH.Fragment_Sach;
import com.example.myapp.Fragment.Fragment_SACH.Loai_Fragment;
import com.example.myapp.Fragment.Fragment_SACH.TatCa_Fragment;

public class TabSach extends FragmentStateAdapter {

    public TabSach(@NonNull Fragment_Sach fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new TatCa_Fragment();
            case 1:
                return new Loai_Fragment();
            default:
                return new TatCa_Fragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
