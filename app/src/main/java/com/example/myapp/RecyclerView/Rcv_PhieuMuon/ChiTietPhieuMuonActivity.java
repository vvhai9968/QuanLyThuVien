package com.example.myapp.RecyclerView.Rcv_PhieuMuon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapp.DTO.PhieuMuon;
import com.example.myapp.Dao.PhieuMuonDAO;
import com.example.myapp.Dao.SachDAO;
import com.example.myapp.Dao.ThanhVienDAO;
import com.example.myapp.MainActivity;
import com.example.myapp.R;
import com.example.myapp.RecyclerView.Rcv_Sach.RecyclerView_Sach.ChiTietActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ChiTietPhieuMuonActivity extends AppCompatActivity {

    Spinner tenTv,tenSach;
    EditText ngayThue,ngayTra;
    ImageView imgThue,imgTra;
    CheckBox chk;
    CardView sua,xoa,thoat;
    PhieuMuonDAO phieuMuonDAO;
    PhieuMuon pm = new PhieuMuon();
    SachDAO sachDAO;
    ThanhVienDAO thanhVienDAO;
    ArrayList<String> mangSach = new ArrayList<>();
    ArrayList<String> mangIdSach = new ArrayList<>();
    ArrayList<String> mangThanhVien = new ArrayList<>();
    ArrayList<String> mangIdThanhVien = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_phieu_muon);

        tenSach = findViewById(R.id.cttenSach);
        tenTv= findViewById(R.id.cttenTV);
        ngayThue = findViewById(R.id.ctpmNgayThue);
        ngayTra = findViewById(R.id.ctpmNgayTra);
        imgThue = findViewById(R.id.ctdate);
        imgTra = findViewById(R.id.ctdatetra);
        chk = findViewById(R.id.chkPM);
        sua = findViewById(R.id.suaPM);
        xoa = findViewById(R.id.xoaPM);
        thoat = findViewById(R.id.thoatctPM);

        sachDAO = new SachDAO(this);
        thanhVienDAO = new ThanhVienDAO(this);
        phieuMuonDAO = new PhieuMuonDAO(this);

        mangIdSach = sachDAO.getId();
        mangSach = sachDAO.getName();

        mangIdThanhVien = thanhVienDAO.getId();
        mangThanhVien = thanhVienDAO.getName();

        //lay id san pham
        Intent intent = getIntent();
        Bundle b = intent.getBundleExtra("dataPM");
        int id = b.getInt("idPM");

        //lay data san pham
        pm = phieuMuonDAO.selectOne(id);

        Log.d("idzzzz", "onCreate: "+id);

        ngayTra.setText(pm.getNgayTra());
        ngayThue.setText(pm.getNgayThue());
        spTenTV();
        spTenSach();

        if(pm.getTrangThai()==1){
            chk.setChecked(true);
        }
        else {
            chk.setChecked(false);
        }

        Log.d("mangsachtv", "onCreate: "+pm.getMaSach()+pm.getMaTV());
        for(int i=0;i<mangIdSach.size();i++){
            if(String.valueOf(pm.getMaSach()).equals(mangIdSach.get(i))){
                hienThi(tenSach,mangSach.get(i));
                break;
            }
        }

        for(int i=0;i<mangIdThanhVien.size();i++){
            if(String.valueOf(pm.getMaTV()).equals(mangIdThanhVien.get(i))){
                hienThi(tenTv,mangThanhVien.get(i));
                break;
            }
        }


        tenSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                pm.setMaSach(Integer.parseInt(mangIdSach.get(i)));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        tenTv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                pm.setMaTV(Integer.parseInt(mangIdThanhVien.get(i)));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        imgThue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("zzzz", "onClick: vao ");
                final Calendar calendar = Calendar.getInstance();
                int nam = calendar.get(Calendar.YEAR);
                int thang = calendar.get(Calendar.MONTH);
                int ngay = calendar.get(Calendar.DATE);

                DatePickerDialog dialog =new DatePickerDialog(ChiTietPhieuMuonActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        calendar.set(i,i1,i2);
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        ngayThue.setText(format.format(calendar.getTime()));
                    }
                },ngay,thang,nam);
                dialog.getDatePicker().updateDate(nam, thang, ngay);
                dialog.show();
            }
        });
        imgTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("zzzz", "onClick: vao ");
                final Calendar calendar = Calendar.getInstance();
                int nam = calendar.get(Calendar.YEAR);
                int thang = calendar.get(Calendar.MONTH);
                int ngay = calendar.get(Calendar.DATE);

                DatePickerDialog dialog =new DatePickerDialog(ChiTietPhieuMuonActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        calendar.set(i,i1,i2);
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        ngayTra.setText(format.format(calendar.getTime()));
                    }
                },ngay,thang,nam);
                dialog.getDatePicker().updateDate(nam, thang, ngay);
                dialog.show();
            }
        });


        sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tenSach.getSelectedItem() == null || ngayThue.getText().toString().isEmpty() || ngayTra.getText().toString().isEmpty()) {
                    Toast.makeText(ChiTietPhieuMuonActivity.this, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                String ngayThueStr = ngayThue.getText().toString();
                String ngayTraStr = ngayTra.getText().toString();

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                dateFormat.setLenient(false);

                try {
                    dateFormat.parse(ngayThueStr);
                    dateFormat.parse(ngayTraStr);
                } catch (ParseException e) {
                    Toast.makeText(ChiTietPhieuMuonActivity.this, "Ngày không hợp lệ. Vui lòng nhập lại theo định dạng dd/MM/yyyy", Toast.LENGTH_SHORT).show();
                    return;
                }
                pm.setNgayTra(ngayTra.getText().toString());
                pm.setNgayThue(ngayThue.getText().toString());

                if(chk.isChecked()){
                    pm.setTrangThai(Integer.parseInt(String.valueOf(1)));
                }else{
                    pm.setTrangThai(Integer.parseInt(String.valueOf(0)));
                }

                int kq = phieuMuonDAO.updatePhieuMuon(pm);
                if(kq==-1){
                    Toast.makeText(ChiTietPhieuMuonActivity.this,"Sua Thất Bại",Toast.LENGTH_SHORT).show();
                }else{

                    Bundle bundle = new Bundle();
                    bundle.putInt("myInt", 2);
                    Intent i = new Intent(ChiTietPhieuMuonActivity.this, MainActivity.class);
                    i.putExtras(bundle);
                    startActivity(i);
                    finish();

                    Toast.makeText(ChiTietPhieuMuonActivity.this,"Sua Thành Công",Toast.LENGTH_SHORT).show();
                }


            }
        });

        xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int kq = phieuMuonDAO.delete(pm.getMaPM());
                if(kq==-1){
                    Toast.makeText(ChiTietPhieuMuonActivity.this,"Xoa Thất Bại",Toast.LENGTH_SHORT).show();
                }else{

                    Bundle bundle = new Bundle();
                    bundle.putInt("myInt", 2);
                    Intent i = new Intent(ChiTietPhieuMuonActivity.this, MainActivity.class);
                    i.putExtras(bundle);
                    startActivity(i);
                    finish();

                    Toast.makeText(ChiTietPhieuMuonActivity.this,"Xoa Thành Công",Toast.LENGTH_SHORT).show();
                }
            }
        });

        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("myInt", 2);
                Intent i = new Intent(ChiTietPhieuMuonActivity.this, MainActivity.class);
                i.putExtras(bundle);
                startActivity(i);
                finish();
            }
        });
    }

    private void hienThi(Spinner spinner,String value) {
        for(int i =0;i<spinner.getCount();i++){
            if(spinner.getItemAtPosition(i).equals(value)){
                spinner.setSelection(i);
                break;
            }
        }
    }

    private void spTenSach() {
        ArrayAdapter<String> dulieuS =new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,mangSach);
        dulieuS.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tenSach.setAdapter(dulieuS);
    }

    private void spTenTV() {
        ArrayAdapter<String> dulieuTV = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,mangThanhVien);
        dulieuTV.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tenTv.setAdapter(dulieuTV);
    }


}