package com.example.myapp.Fragment.Fragment_SACH;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.DTO.Sach;
import com.example.myapp.Dao.SachDAO;
import com.example.myapp.R;
import com.example.myapp.RecyclerView.Rcv_Sach.RecyclerView_Sach.SachAdapter;
import com.example.myapp.RecyclerView.Rcv_Sach.ThemSachActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TatCa_Fragment extends Fragment  {
    View view;
    TextView soLuongS;
    FloatingActionButton mFloatingActionButton;
    RecyclerView mRecyclerView;
    SachAdapter sachAdapter;
    SachDAO sachDAO;

    ArrayList<Sach> mArr;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.tab_tatca,container,false);

        sachAdapter = new SachAdapter(getContext());
        sachDAO = new SachDAO(getContext());
        anhXa();
        getData();

        soLuongS.setText(String.valueOf(sachDAO.checkHang()));
        suKienBottomBtn();
        return view;
    }
    public void getData(){
        mArr = sachDAO.getALL();
        sachAdapter.setData(mArr);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(sachAdapter);
    }
    void anhXa(){
        mRecyclerView = view.findViewById(R.id.rcvtc);
        mFloatingActionButton = view.findViewById(R.id.fbtc);
        soLuongS = view.findViewById(R.id.soLuongSach);
    }

    void suKienBottomBtn(){
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ThemSachActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }
}
