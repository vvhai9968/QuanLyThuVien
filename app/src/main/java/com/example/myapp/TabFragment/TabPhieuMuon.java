package com.example.myapp.TabFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapp.Fragment.Fragment_PM.ChuaTra_Fragment;
import com.example.myapp.Fragment.Fragment_PM.DaTra_Fragment;
import com.example.myapp.Fragment.Fragment_PM.Fragment_phieuMuon;

public class TabPhieuMuon extends FragmentStateAdapter {
    public TabPhieuMuon(@NonNull Fragment_phieuMuon fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new ChuaTra_Fragment();
            case 1:
                return new DaTra_Fragment();
            default:
                return new ChuaTra_Fragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
