package com.example.myapp.Fragment.Fragment_PM;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapp.DTO.PhieuMuon;
import com.example.myapp.Dao.PhieuMuonDAO;
import com.example.myapp.Dao.TrangChuDAO;
import com.example.myapp.R;
import com.example.myapp.RecyclerView.Rcv_PhieuMuon.RecyclerView_ChuaTra.ChuaTra_Adapter;

import java.util.ArrayList;


public class ChuaTra_Fragment extends Fragment {
      RecyclerView recyclerView;
      ChuaTra_Adapter chuaTra_adapter;
      PhieuMuonDAO phieuMuonDAO;
      ArrayList<PhieuMuon> mArr;
      View view;
       @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
           view = inflater.inflate(R.layout.tab_chuatra, container, false);
           chuaTra_adapter = new ChuaTra_Adapter(getContext());
        phieuMuonDAO = new PhieuMuonDAO(getContext());

           anhxa();
           getData();
        return view;
    }

    private void getData() {
           mArr = phieuMuonDAO.getALLChuaTra();
           chuaTra_adapter.setData(mArr);
           RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),1);
           recyclerView.setLayoutManager(layoutManager);
           recyclerView.setAdapter(chuaTra_adapter);
    }

    private void anhxa() {
           recyclerView = view.findViewById(R.id.rcvChuaTra);
    }
}