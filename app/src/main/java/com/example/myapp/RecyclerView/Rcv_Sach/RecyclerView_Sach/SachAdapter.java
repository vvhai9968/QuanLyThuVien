package com.example.myapp.RecyclerView.Rcv_Sach.RecyclerView_Sach;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.DTO.Sach;
import com.example.myapp.Dao.LoaiDAO;
import com.example.myapp.R;

import java.util.ArrayList;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.viewHodler>{
    ArrayList<Sach> mArr;
    private Context mContext;
    ArrayList<String> mangIDLoaiSach = new ArrayList<>();
    ArrayList<String> mangTenSach = new ArrayList<>();
    LoaiDAO loaiDAO;

    public SachAdapter(Context mContext ) {
        this.mContext = mContext;
    }
    public void setData(ArrayList<Sach> mArr){
        this.mArr = mArr;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public viewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_sach,parent,false);

        loaiDAO =new LoaiDAO(mContext);
        return new SachAdapter.viewHodler(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull viewHodler holder, int position) {
        if(mArr==null){
            return;
        }
        Sach sach = mArr.get(position);
        mangIDLoaiSach = loaiDAO.getId();
        mangTenSach = loaiDAO.getName();

        for (int i =0; i<mangIDLoaiSach.size();i++){
         if (String.valueOf(sach.getMaLoai()).equals(mangIDLoaiSach.get(i))){
                String loai =mangTenSach.get(i);
                holder.tv_loaiSach.setText(loai);
         }
        }

        holder.tv_tenSach.setText(sach.getTenSach());

        byte[] hinh = sach.getHinh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinh, 0, hinh.length);
        holder.anhSach.setImageBitmap(bitmap);

        holder.tv_soLuong.setText(String.valueOf(sach.getSoLuong()));
        holder.suaXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.suaXoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(mContext, ChiTietActivity.class);
                        Bundle b = new Bundle();
                        int id = mArr.get(position).getMaS();
                        b.putInt("id", id);
                        i.putExtra("data", b);
                        mContext.startActivity(i);
                    }
                });

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

    public  class viewHodler extends RecyclerView.ViewHolder{
        TextView tv_tenSach,tv_soLuong,tv_loaiSach ;
        ImageView anhSach;
        CardView suaXoa;
        public viewHodler(@NonNull View itemView) {
            super(itemView);
            tv_tenSach = itemView.findViewById(R.id.tenSach);
            tv_soLuong = itemView.findViewById(R.id.soLuong);
            anhSach =  itemView.findViewById(R.id.anhSach);
            tv_loaiSach = itemView.findViewById(R.id.loaiSach);
            suaXoa = itemView.findViewById(R.id.suaXoa);

        }
    }
}