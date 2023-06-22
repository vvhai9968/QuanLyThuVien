package com.example.myapp.RecyclerView.Rcv_Sach.RecyclerView_Loai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.DTO.Loai;
import com.example.myapp.R;

import java.util.ArrayList;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.viewHodler>{
    ArrayList<Loai> mArr;
    private Context mContext;
    private onClickListenLoai mOnClickListenLoai;

    public LoaiSachAdapter(Context mContext, onClickListenLoai mOnClickListenLoai) {
        this.mContext = mContext;
        this.mOnClickListenLoai = mOnClickListenLoai;
    }



    public void setData(ArrayList<Loai> mArr){
        this.mArr = mArr;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public viewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_loai,parent,false);
        return new viewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHodler holder, int position) {
        if(mArr==null){
            return;
        }
        Loai loai = mArr.get(position);
        holder.tv_name.setText(loai.getTenLoai());


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnClickListenLoai.delete(loai.getIdLoai(), loai.getTenLoai());
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
        TextView tv_name ;
        ImageView  delete;
    public viewHodler(@NonNull View itemView) {
        super(itemView);
        tv_name = itemView.findViewById(R.id.item_row_ten);
        delete =  itemView.findViewById(R.id.item_rowDelete);
    }
}
}
