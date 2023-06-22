package com.example.myapp.RecyclerView.Rcv_ThanhVien;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.DTO.Sach;
import com.example.myapp.DTO.ThanhVien;
import com.example.myapp.R;
import com.example.myapp.RecyclerView.Rcv_Sach.RecyclerView_Loai.LoaiSachAdapter;
import com.example.myapp.RecyclerView.Rcv_Sach.RecyclerView_Loai.onClickListenLoai;

import java.util.ArrayList;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.viewHodler> {
    ArrayList<ThanhVien> mArr;
    private Context mContext;
    private onClickListenThanhVien onClickListenThanhVien;

    public ThanhVienAdapter(Context mContext, onClickListenThanhVien onClickListenThanhVien) {
        this.mContext = mContext;
        this.onClickListenThanhVien = onClickListenThanhVien;
    }

    @NonNull
    @Override
    public viewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_thanhvien,parent,false);
        return new viewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHodler holder, int position) {
        if(mArr==null){
            return;
        }

        ThanhVien tv = mArr.get(position);
        holder.tv_name.setText(tv.getHoTen());

        byte[] hinh = tv.getHinh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinh, 0, hinh.length);
        holder.anhDaiDien.setImageBitmap(bitmap);
        holder.tv_sn.setText(tv.getNamSinh());

        holder.Sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref2 = mContext.getSharedPreferences("CHECK",MODE_PRIVATE);
                SharedPreferences.Editor edit2 = pref2.edit();
                edit2.putString("check","2");
                edit2.commit();
                onClickListenThanhVien.dialogSuaTv(tv.getMaTV(),tv.getHinh(),tv.getHoTen(),tv.getNamSinh());
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              onClickListenThanhVien.dialogXoaTV(tv.getMaTV(),tv.getHoTen());
            }
        });
    }
    public void setData(ArrayList<ThanhVien> mArr){
        this.mArr = mArr;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        if(mArr!=null){
            return mArr.size();
        }
        return 0;
    }

    public class viewHodler extends RecyclerView.ViewHolder {
        TextView tv_name,tv_sn;
        ImageView delete,anhDaiDien,Sua;

        public viewHodler(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.item_hoTen);
            anhDaiDien = itemView.findViewById(R.id.anhDaiDien);
            tv_sn = itemView.findViewById(R.id.item_namSinh);
            Sua = itemView.findViewById(R.id.item_Sua);
            delete = itemView.findViewById(R.id.item_Xoa);
        }
    }
}
