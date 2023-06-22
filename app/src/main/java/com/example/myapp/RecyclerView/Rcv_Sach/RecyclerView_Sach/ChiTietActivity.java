package com.example.myapp.RecyclerView.Rcv_Sach.RecyclerView_Sach;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.myapp.DTO.Sach;
import com.example.myapp.Dao.LoaiDAO;
import com.example.myapp.Dao.SachDAO;
import com.example.myapp.MainActivity;
import com.example.myapp.R;
import com.example.myapp.RecyclerView.Rcv_Sach.ThemSachActivity;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class ChiTietActivity extends AppCompatActivity   {
    CardView thoat,sua,xoa;
    EditText tenS,tacGiaS,nxb,soLuongS;
    ImageView img,addimg;
    Spinner LSach;
    Bitmap bitmap1;
    int REQUEST_CODE_FOLDER = 456;
    LoaiDAO loaiDAO;
    SachDAO sachDAO;
    ArrayList<String> mangLoaiSach = new ArrayList<>();
    ArrayList<String> mangIdLoaiSach = new ArrayList<>();

    Sach s = new Sach();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet);

        img =  findViewById(R.id.ctaddAnh);
        addimg =  findViewById(R.id.ctimg);
        tenS =  findViewById(R.id.cttenS);
        tacGiaS =  findViewById(R.id.cttacgia);
        nxb = findViewById(R.id.ctnxb);
        soLuongS = findViewById(R.id.ctsoluong);
        LSach = findViewById(R.id.ctspinner_idLSach);

        loaiDAO = new LoaiDAO(this);
        sachDAO = new SachDAO(this);

        mangIdLoaiSach = loaiDAO.getId();
        mangLoaiSach = loaiDAO.getName();

        thoat =  findViewById(R.id.thoatct);
        sua = findViewById(R.id.suaS);
        xoa = findViewById(R.id.xoaS);
        spLoaiSach();

        //Lay id sp
        Intent intent = getIntent();
        Bundle b = intent.getBundleExtra("data");
        int id = b.getInt("id");

        //lay data 1 sp
        sachDAO = new SachDAO(this);
        s = sachDAO.selectOne(id);

        tenS.setText(s.getTenSach());
        tacGiaS.setText(s.getTacGia());
        nxb.setText(s.getNhaXuatBan());
        soLuongS.setText(String.valueOf(s.getSoLuong()));
        Bitmap bmp = BitmapFactory.decodeByteArray(s.getHinh(), 0, s.getHinh().length);
        img.setImageBitmap(bmp);

        for(int i = 0 ; i<mangIdLoaiSach.size();i++){
            if(String.valueOf(s.getMaLoai()).equals(mangIdLoaiSach.get(i))){
                selectValue(LSach,mangLoaiSach.get(i));
            }
        }

        LSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                s.setMaLoai(Integer.parseInt(mangIdLoaiSach.get(i)));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        addimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i, REQUEST_CODE_FOLDER );
            }
        });


        sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(tenS.getText().toString())||TextUtils.isEmpty(tacGiaS.getText().toString())||TextUtils.isEmpty(nxb.getText().toString())||TextUtils.isEmpty(soLuongS.getText().toString())){
                    Toast.makeText(ChiTietActivity.this, "Vui lòng điền đủ thông tin ", Toast.LENGTH_SHORT).show();
                    return;
                }else  if (bitmap1 == null) {
                    Toast.makeText(ChiTietActivity.this, "Vui lòng chọn hình ảnh", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    int soLuongSach = Integer.parseInt(soLuongS.getText().toString());
                    if (soLuongSach <= 0) {
                        Toast.makeText(ChiTietActivity.this, "Số lượng sách phải là một số dương", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(ChiTietActivity.this, "Số lượng sách phải là một số", Toast.LENGTH_SHORT).show();
                    return;
                }

                s.setTenSach(tenS.getText().toString());
                s.setSoLuong(Integer.parseInt(soLuongS.getText().toString()));
                s.setTacGia(tacGiaS.getText().toString());
                s.setNhaXuatBan(nxb.getText().toString());

                if (bitmap1 != null){
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap1.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byte[] hinhAnh = byteArrayOutputStream.toByteArray();
                    s.setHinh(hinhAnh);
                    Log.d("zzz", "onClick: "+hinhAnh);
                }

                int kq = sachDAO.updateSach(s);
                if(kq==-1){
                    Toast.makeText(ChiTietActivity.this,"Sua Thất Bại",Toast.LENGTH_SHORT).show();
                }else{

                    Bundle bundle = new Bundle();
                    bundle.putInt("myInt", 1);
                    Intent i = new Intent(ChiTietActivity.this, MainActivity.class);
                    i.putExtras(bundle);
                    startActivity(i);
                    finish();

                    Toast.makeText(ChiTietActivity.this,"Sua Thành Công",Toast.LENGTH_SHORT).show();
                }
            }
        });

        xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int kq = sachDAO.delete(s.getMaS());
                if(kq==-1){
                    Toast.makeText(ChiTietActivity.this,"Xoa Thất Bại",Toast.LENGTH_SHORT).show();
                }else{

                    Bundle bundle = new Bundle();
                    bundle.putInt("myInt", 1);
                    Intent i = new Intent(ChiTietActivity.this, MainActivity.class);
                    i.putExtras(bundle);
                    startActivity(i);
                    finish();

                    Toast.makeText(ChiTietActivity.this,"Xoa Thành Công",Toast.LENGTH_SHORT).show();
                }
            }
        });

        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("myInt", 1);
                Intent i = new Intent(ChiTietActivity.this, MainActivity.class);
                i.putExtras(bundle);
                startActivity(i);
                finish();
            }
        });

    }
    public void spLoaiSach(){
        ArrayAdapter<String> dulieu =new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,mangLoaiSach);
        dulieu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        LSach.setAdapter(dulieu);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = this.getContentResolver().openInputStream(uri);
                if (inputStream != null) {
                    bitmap1 = BitmapFactory.decodeStream(inputStream);
                    if (img != null) {
                        img.setImageBitmap(bitmap1);
                    }

                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    private void selectValue(Spinner spinner, String value) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).equals(value)) {
                spinner.setSelection(i);
                break;
            }
        }
    }
}