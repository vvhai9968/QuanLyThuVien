package com.example.myapp.RecyclerView.Rcv_PhieuMuon.RecyclerView_ChuaTra;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.DTO.PhieuMuon;
import com.example.myapp.Dao.PhieuMuonDAO;
import com.example.myapp.Dao.SachDAO;
import com.example.myapp.Dao.ThanhVienDAO;
import com.example.myapp.R;
import com.example.myapp.RecyclerView.Rcv_PhieuMuon.ChiTietPhieuMuonActivity;
import com.example.myapp.RecyclerView.Rcv_Sach.RecyclerView_Sach.ChiTietActivity;

import java.util.ArrayList;

public class ChuaTra_Adapter extends RecyclerView.Adapter<ChuaTra_Adapter.viewHodler>  {

    ArrayList<PhieuMuon> mArr;
    private Context mContext;
    PhieuMuonDAO phieuMuonDAO;
    SachDAO sachDAO;
    ThanhVienDAO thanhVienDAO;

    ArrayList<String> mangIDThanhVien = new ArrayList<>();
    ArrayList<String> mangTenTV = new ArrayList<>();
    ArrayList<String> mangIDSach = new ArrayList<>();
    ArrayList<String> mangTenSach = new ArrayList<>();

    public ChuaTra_Adapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(ArrayList<PhieuMuon> mArr) {
        this.mArr = mArr;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public viewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_phieumuon,parent,false);

        phieuMuonDAO = new PhieuMuonDAO(mContext);
        sachDAO = new SachDAO(mContext);
        thanhVienDAO = new ThanhVienDAO(mContext);

        return new ChuaTra_Adapter.viewHodler(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull viewHodler holder, int position) {
        if(mArr== null){
            return;
        }

        PhieuMuon phieuMuon = mArr.get(position);

        mangIDThanhVien = thanhVienDAO.getId();
        mangTenTV = thanhVienDAO.getName();

        mangIDSach = sachDAO.getId();
        mangTenSach = sachDAO.getName();


        for (int i = 0; i<mangIDThanhVien.size();i++){
            if(String.valueOf(phieuMuon.getMaTV()).equals(mangIDThanhVien.get(i))){
                String tenTV = mangTenTV.get(i);
                holder.ten.setText(tenTV);
            }
        }

        for (int i = 0; i<mangIDSach.size();i++){
            if(String.valueOf(phieuMuon.getMaSach()).equals(mangIDSach.get(i))){
                String tenS = mangTenSach.get(i);
                holder.sach.setText(tenS);
            }
        }

        holder.ngaymuon.setText(phieuMuon.getNgayThue());
        holder.ngaytra.setText(phieuMuon.getNgayTra());

        holder.chiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, ChiTietPhieuMuonActivity.class);

                Bundle b = new Bundle();
                int id = mArr.get(position).getMaPM();
                b.putInt("idPM", id);
                i.putExtra("dataPM", b);
                mContext.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        if(mArr!=null){
            return mArr.size();
        }
        return 0;
    }

    public class viewHodler extends RecyclerView.ViewHolder{
        TextView ten,sach,ngaymuon,ngaytra;
        CardView chiTiet;

        public viewHodler(@NonNull View itemView) {
            super(itemView);

            ten = itemView.findViewById(R.id.tennguoimuon);
            sach = itemView.findViewById(R.id.sachMuon);
            ngaymuon = itemView.findViewById(R.id.ngayMuon);
            ngaytra = itemView.findViewById(R.id.ngayTra);
            chiTiet = itemView.findViewById(R.id.suaXoapm);
        }
    }
}
