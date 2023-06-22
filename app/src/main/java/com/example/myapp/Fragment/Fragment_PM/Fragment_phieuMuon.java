package com.example.myapp.Fragment.Fragment_PM;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.myapp.R;
import com.example.myapp.RecyclerView.Rcv_PhieuMuon.ThemPhieuMuonActivity;
import com.example.myapp.TabFragment.TabPhieuMuon;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class Fragment_phieuMuon extends Fragment {
    TabLayout mTabLayout;
    ViewPager2 mViewPager2;
    View view;
    Dialog dialog;
    Spinner tenNguoiMuon,tenSach;
    EditText ngayThue, gia;
    CheckBox check;
    CardView them,thoat;


    FloatingActionButton mFloatingActionButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_phieu_muon, container, false);
        anhXa();
        TabPhieuMuon tabPhieuMuon = new TabPhieuMuon(this);
        mViewPager2.setAdapter(tabPhieuMuon);

        new TabLayoutMediator(mTabLayout,mViewPager2,(tab, position) -> {
            switch (position){
                case 0:
                    tab.setText("Chưa Trả");
                    break;
                case 1:
                    tab.setText("Đã Trả");
                    break;
            }
        }).attach();


        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ThemPhieuMuonActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return view;
    }


    private void anhXa() {
        mTabLayout = view.findViewById(R.id.tabpm);
        mViewPager2 = view.findViewById(R.id.vpm);
        mFloatingActionButton = view.findViewById(R.id.log_pm);
    }
}