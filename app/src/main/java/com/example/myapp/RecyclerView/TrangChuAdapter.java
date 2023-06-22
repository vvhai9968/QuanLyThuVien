package com.example.myapp.RecyclerView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.DTO.TKSMuonNhieu;
import com.example.myapp.Dao.SachDAO;
import com.example.myapp.R;

import java.util.ArrayList;

public class TrangChuAdapter extends RecyclerView.Adapter<TrangChuAdapter.viewHodler> {

    ArrayList<TKSMuonNhieu> thongKes;
    private Context context;

    SachDAO sachDAO;
    ArrayList<String> idSach = new ArrayList<>();
    ArrayList<String> tenSach = new ArrayList<>();

    public TrangChuAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<TKSMuonNhieu> mArr) {
        this.thongKes = mArr;
    }

    @NonNull
    @Override
    public viewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_top,parent,false);

        sachDAO = new SachDAO(context);
        return new TrangChuAdapter.viewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHodler holder, int position) {
        if(thongKes ==null){
            return;
        }
        TKSMuonNhieu thongKe = thongKes.get(position);

        idSach = sachDAO.getId();
        tenSach = sachDAO.getName();

        for(int i = 0 ; i<idSach.size();i++){
            if(String.valueOf(thongKe.getMaSach()).equals(idSach.get(i))){
                String loai = tenSach.get(i) ;
                holder.tv_Ten.setText(String.valueOf(loai));
                Log.d("zzzzz", "onBindViewHolder: "+loai);
            }
        }

        holder.tv_soLuong.setText(String.valueOf(thongKe.getSoLuong()));
    }

    @Override
    public int getItemCount() {
        if(thongKes !=null){
            return thongKes.size();
        }
        return 0;
    }

    public class viewHodler extends RecyclerView.ViewHolder{
        TextView tv_Ten,tv_soLuong;
        public viewHodler(@NonNull View itemView) {
            super(itemView);
            tv_Ten = itemView.findViewById(R.id.TenSach);
            tv_soLuong = itemView.findViewById(R.id.solan);
        }
    }
}
