package com.example.myapp.Fragment.Fragment_SACH;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapp.R;
import com.example.myapp.TabFragment.TabSach;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class Fragment_Sach extends Fragment {
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sach, container, false);
        anhxa();

        TabSach tabSach =new TabSach( Fragment_Sach.this);
        viewPager2.setAdapter(tabSach);

        new TabLayoutMediator(tabLayout,viewPager2,(tab, position) -> {
            switch (position){
                case 0:
                    tab.setText("Tất Cả");
                    break;
                case 1:
                    tab.setText("Thể Loại");
                    break;
            }
        }).attach();
        return view;
    }

    private void anhxa(){
        tabLayout = view.findViewById(R.id.Tab_Layout);
        viewPager2 = view.findViewById(R.id.view_Pager2);
    }
}