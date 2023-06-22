package com.example.myapp.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapp.DTO.TKSMuonNhieu;
import com.example.myapp.Dao.PhieuMuonDAO;
import com.example.myapp.Dao.TrangChuDAO;
import com.example.myapp.R;
import com.example.myapp.RecyclerView.TrangChuAdapter;

import java.util.ArrayList;

public class Fragment_trangChu extends Fragment {
    ArrayList<TKSMuonNhieu> mArr = new ArrayList<>();
    View view;
    TextView lanMuon,lanTra,tongTien;
    TrangChuDAO trangChuDAO;
    TrangChuAdapter trangChuAdapter;
    RecyclerView recyclerView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_trang_chu, container, false);
        lanMuon = view.findViewById(R.id.lanMuon);
        lanTra = view.findViewById(R.id.lanTra);
        tongTien = view.findViewById(R.id.tongTien);
        recyclerView = view.findViewById(R.id.sachMuonNhieu);
        trangChuDAO = new TrangChuDAO(getContext());
        trangChuAdapter = new TrangChuAdapter(getContext());

       lanMuon.setText(String.valueOf(trangChuDAO.checkLanMuon()));
       lanTra.setText(String.valueOf(trangChuDAO.checkLanTra()));
       tongTien.setText(String.valueOf(trangChuDAO.tongTien()));
        getData();
       return view;
    }

    private void getData() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        mArr = trangChuDAO.getTop();
        recyclerView.setLayoutManager(layoutManager);
        trangChuAdapter.setData(mArr);
        recyclerView.setAdapter(trangChuAdapter);
        trangChuAdapter.notifyDataSetChanged();
    }


}