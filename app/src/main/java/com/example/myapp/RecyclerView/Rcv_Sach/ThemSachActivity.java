package com.example.myapp.RecyclerView.Rcv_Sach;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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

import com.example.myapp.DTO.Sach;
import com.example.myapp.Dao.LoaiDAO;
import com.example.myapp.Dao.SachDAO;
import com.example.myapp.MainActivity;
import com.example.myapp.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class ThemSachActivity extends AppCompatActivity {

    CardView thoat,them;
    EditText tenS,tacGia,nxb,soLuong;
    ImageView img,addimg;
    Spinner LSach;
    Bitmap bitmap1;
    int REQUEST_CODE_FOLDER = 456;
    LoaiDAO loaiDAO;
    SachDAO sachDAO;
    ArrayList<String> mangLoaiSach = new ArrayList<>();
    ArrayList<String> mangIdLoaiSach = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_sach);


        img =  findViewById(R.id.imgTS);
        addimg =  findViewById(R.id.addAnh);
        tenS =  findViewById(R.id.tenS);
        tacGia =  findViewById(R.id.tacgia);
        nxb = findViewById(R.id.nxb);
        soLuong = findViewById(R.id.soluong);
        LSach = findViewById(R.id.spinner_idLSach);

        loaiDAO = new LoaiDAO(this);
        sachDAO = new SachDAO(this);

        mangIdLoaiSach = loaiDAO.getId();
        mangLoaiSach = loaiDAO.getName();
        spLoaiSach();

        thoat =  findViewById(R.id.thoatts);
        them = findViewById(R.id.themS);

        Sach s = new Sach();

        LSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                s.setMaLoai(Integer.parseInt(mangIdLoaiSach.get(i)));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(tenS.getText().toString())||TextUtils.isEmpty(tacGia.getText().toString())||TextUtils.isEmpty(nxb.getText().toString())||TextUtils.isEmpty(soLuong.getText().toString())){
                    Toast.makeText(ThemSachActivity.this, "Vui lòng điền đủ thông tin ", Toast.LENGTH_SHORT).show();
                    return;
                }else  if (bitmap1 == null) {
                    Toast.makeText(ThemSachActivity.this, "Vui lòng chọn hình ảnh", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    int soLuongSach = Integer.parseInt(soLuong.getText().toString());
                    if (soLuongSach <= 0) {
                        Toast.makeText(ThemSachActivity.this, "Số lượng sách phải là một số dương", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(ThemSachActivity.this, "Số lượng sách phải là một số", Toast.LENGTH_SHORT).show();
                    return;
                }




                s.setTenSach(tenS.getText().toString());
                s.setSoLuong(Integer.parseInt(soLuong.getText().toString()));
                s.setTacGia(tacGia.getText().toString());
                s.setNhaXuatBan(nxb.getText().toString());

                if (bitmap1 != null){
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap1.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byte[] hinhAnh = byteArrayOutputStream.toByteArray();
                    s.setHinh(hinhAnh);
                    Log.d("zzz", "onClick: "+hinhAnh);
                }

                int kq = sachDAO.themS(s);
                if(kq==-1){
                       Toast.makeText(ThemSachActivity.this,"Thêm Thất Bại",Toast.LENGTH_SHORT).show();
                }else{

                    Bundle bundle = new Bundle();
                    bundle.putInt("myInt", 1);
                    Intent i = new Intent(ThemSachActivity.this, MainActivity.class);
                    i.putExtras(bundle);
                    startActivity(i);
                    finish();

                    Toast.makeText(ThemSachActivity.this,"Thêm Thành Công",Toast.LENGTH_SHORT).show();
                }

            }
        });


        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putInt("myInt", 1);
                Intent i = new Intent(ThemSachActivity.this, MainActivity.class);
                i.putExtras(bundle);
                startActivity(i);
                finish();


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

    public void spLoaiSach(){
        ArrayAdapter<String> dulieu =new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,mangLoaiSach);
        dulieu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        LSach.setAdapter(dulieu);

    }
}