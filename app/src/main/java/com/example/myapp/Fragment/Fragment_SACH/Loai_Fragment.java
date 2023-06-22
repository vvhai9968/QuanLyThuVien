package com.example.myapp.Fragment.Fragment_SACH;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.DTO.Loai;
import com.example.myapp.Dao.LoaiDAO;
import com.example.myapp.R;
import com.example.myapp.RecyclerView.Rcv_Sach.RecyclerView_Loai.LoaiSachAdapter;
import com.example.myapp.RecyclerView.Rcv_Sach.RecyclerView_Loai.onClickListenLoai;
import com.example.myapp.RecyclerView.Rcv_Sach.RecyclerView_Sach.ChiTietActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Loai_Fragment extends Fragment implements onClickListenLoai {
    View view;
    FloatingActionButton mFloatingActionButton;
    RecyclerView mRecyclerView;
    LoaiSachAdapter loaiSachAdapter;
    LoaiDAO loaiDAO;
    ArrayList<Loai> mArr = new ArrayList<>();
    Dialog mDialog;
    EditText tenL;
    CardView them,thoat;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.tab_loai,container,false);

        loaiSachAdapter = new LoaiSachAdapter(getContext(),this);
        loaiDAO = new LoaiDAO(getContext());
        anhXa();
        getData();
        dialogADD();
        return view;
    }

    void anhXa(){
        mRecyclerView = view.findViewById(R.id.rcvl);
        mFloatingActionButton = view.findViewById(R.id.fbl);

    }
    void getData(){
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        mRecyclerView.setLayoutManager(layoutManager);
        mArr = loaiDAO.getALL();
        loaiSachAdapter.setData(mArr);
        mRecyclerView.setAdapter(loaiSachAdapter);
        loaiSachAdapter.notifyDataSetChanged();
    }
    void dialogADD() {
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diaLog();
            }
        });
    }
    void diaLog(){

        mDialog = new Dialog(getContext());
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.dialog_theloai);


        tenL = mDialog.findViewById(R.id.dialog_tenloai);

        thoat = mDialog.findViewById(R.id.thoatL);
        them = mDialog.findViewById(R.id.themL);

        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(tenL.getText().toString())){
                    Toast.makeText( getContext(), "Vui lòng điền đủ thông tin ", Toast.LENGTH_SHORT).show();
                    return;
                }
              Loai l = new Loai();
              l.setTenLoai(tenL.getText().toString());

              int  kq = loaiDAO.themL(l);
                if(kq==-1){
                    Toast.makeText(getContext(),"Thêm Thất Bại",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(),"Thêm Thành Công",Toast.LENGTH_SHORT).show();
                }
                getData();
                mDialog.dismiss();
            }
        });


        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });

        mDialog.show();
    }

    @Override
    public void delete(int id, String ten_loaiS) {
        AlertDialog.Builder aBuilder = new AlertDialog.Builder(getContext());
        aBuilder.setMessage("Bạn Có Muốn Xóa "+ten_loaiS+" Không ?");
        aBuilder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int kq = loaiDAO.xoaL(id);
                if (kq == -1) {
                    Toast.makeText(getContext(), "Xóa Thất Bại", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                }
                getData();
            }
        });

        aBuilder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        aBuilder.show();
    }
}
