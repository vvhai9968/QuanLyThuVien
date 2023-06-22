package com.example.myapp.Fragment;

import static android.app.Activity.RESULT_OK;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapp.DTO.ThanhVien;
import com.example.myapp.Dao.ThanhVienDAO;
import com.example.myapp.R;
import com.example.myapp.RecyclerView.Rcv_ThanhVien.ThanhVienAdapter;
import com.example.myapp.RecyclerView.Rcv_ThanhVien.onClickListenThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class Fragment_theMuon extends Fragment implements onClickListenThanhVien {

    FloatingActionButton mFloatingActionButton;
    RecyclerView mRecyclerView;
    ArrayList<ThanhVien> thanhViens = new ArrayList<>();
    ThanhVienAdapter thanhVienAdapter;
    ThanhVienDAO thanhVienDAO;
    View view;
    Bitmap bitmap1;
    ImageView anh;
    int REQUEST_CODE_FOLDER = 456;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_the_muon, container, false);
        anhXa();
        thanhVienDAO = new ThanhVienDAO(getContext());
        thanhVienAdapter = new ThanhVienAdapter(getContext(),this);
        getData();
        dialogADD();

        return view;
    }
    void dialogADD() {
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diaLog();
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
                if (inputStream != null) {
                    bitmap1 = BitmapFactory.decodeStream(inputStream);

                    diaLog();


                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void diaLog() {
        Dialog mDialog = new Dialog(getContext());
        mDialog.setContentView(R.layout.dialog_them_thanhvien);

        ImageView   addanh,anh;
        EditText hoTen,namSinh;
        CardView them,thoat;


        addanh = mDialog.findViewById(R.id.addtvimg);
        hoTen = mDialog.findViewById(R.id.dilog_hoten);
        namSinh = mDialog.findViewById(R.id.dialog_NamSinh);
        them = mDialog.findViewById(R.id.themtv);
        thoat = mDialog.findViewById(R.id.thoattv);
        anh = mDialog.findViewById(R.id.tvimg);

        if (bitmap1 != null){
            anh.setImageBitmap(bitmap1);
        }

        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThanhVien tv= new ThanhVien();
                tv.setHoTen(hoTen.getText().toString());
                tv.setNamSinh(namSinh.getText().toString());

                String hoTenStr = hoTen.getText().toString();
                String namSinhStr = namSinh.getText().toString();

                if (hoTenStr.isEmpty() || namSinhStr.isEmpty() || bitmap1 == null) {
                    Toast.makeText(getContext(), "Vui lòng điền đủ thông tin và chọn ảnh", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Kiểm tra năm sinh có đúng định dạng 4 số không
                if (namSinhStr.length() != 4 || !namSinhStr.matches("\\d{4}")) {
                    Toast.makeText(getContext(), "Năm sinh phải là một chuỗi gồm 4 chữ số", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (bitmap1 != null){
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap1.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byte[] hinhAnh = byteArrayOutputStream.toByteArray();
                    tv.setHinh(hinhAnh);
                    Log.d("zzz", "onClick: "+hinhAnh);
                }
                int kq = thanhVienDAO.themTV(tv);
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

        addanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i, REQUEST_CODE_FOLDER );
                mDialog.dismiss();
            }
        });
        mDialog.show();
    }

    void anhXa(){
        mFloatingActionButton = view.findViewById(R.id.fbtv);
        mRecyclerView = view.findViewById(R.id.rcvtv);

    }
    void getData(){
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        mRecyclerView.setLayoutManager(layoutManager);
        thanhViens = thanhVienDAO.getALL();
        thanhVienAdapter.setData(thanhViens);
        mRecyclerView.setAdapter(thanhVienAdapter);
        thanhVienAdapter.notifyDataSetChanged();


    }
    @Override
    public void dialogSuaTv(int idTV, byte[] hinh, String tenTV, String namSinh) {
        Dialog mDialog = new Dialog(getContext());
        mDialog.setContentView(R.layout.dialog_sua_thanhvien);

        ImageView   addanh,anh;
        EditText hoTen,snamSinh;
        CardView sua,thoat;



        hoTen = mDialog.findViewById(R.id.sdilog_hoten);
        snamSinh = mDialog.findViewById(R.id.sdialog_NamSinh);
        sua = mDialog.findViewById(R.id.suaTV);
        thoat = mDialog.findViewById(R.id.thoattvs);
        anh = mDialog.findViewById(R.id.stvadd);

        hoTen.setText(tenTV);
        snamSinh.setText(namSinh);
        Bitmap bmp = BitmapFactory.decodeByteArray(hinh, 0,hinh.length);
        anh.setImageBitmap(bmp);

        sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThanhVien tv= new ThanhVien();
                tv.setMaTV(idTV);
                tv.setHoTen(hoTen.getText().toString());
                tv.setNamSinh(snamSinh.getText().toString());

                String hoTenStr = hoTen.getText().toString();
                String namSinhStr = snamSinh.getText().toString();

                if (hoTenStr.isEmpty() || namSinhStr.isEmpty()  ) {
                    Toast.makeText(getContext(), "Vui lòng điền đủ thông tin và chọn ảnh", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Kiểm tra năm sinh có đúng định dạng 4 số không
                if (namSinhStr.length() != 4 || !namSinhStr.matches("\\d{4}")) {
                    Toast.makeText(getContext(), "Năm sinh phải là một chuỗi gồm 4 chữ số", Toast.LENGTH_SHORT).show();
                    return;
                }

                int kq = thanhVienDAO.updatetv(tv);
                if(kq==-1){
                    Toast.makeText(getContext(),"Sua Thất Bại",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(),"Sua Thành Công",Toast.LENGTH_SHORT).show();
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
    public void dialogXoaTV(int idTV, String tenTV) {
        AlertDialog.Builder aBuilder = new AlertDialog.Builder(getContext());
        aBuilder.setMessage("Bạn Có Muốn Xóa "+tenTV+" Không ?");
        aBuilder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int kq = thanhVienDAO.delete(idTV);
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