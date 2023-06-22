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
import com.example.myapp.RecyclerView.Rcv_Sach.ThemSachActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ThemPhieuMuonActivity extends AppCompatActivity {

    Spinner nguoiMuon,tenSach;
    EditText ngayThue,ngayTra,giaThue;
    ImageView dateThue,dateTra;
    CardView btnThem,btnThoat;

    PhieuMuonDAO phieuMuonDAO;
    SachDAO sachDAO;
    ThanhVienDAO thanhVienDAO;

    ArrayList<String> mangSach = new ArrayList<>();
    ArrayList<String> mangIdSach = new ArrayList<>();
    ArrayList<String> mangThanhVien = new ArrayList<>();
    ArrayList<String> mangIdThanhVien = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_phieu_muon);

        nguoiMuon = findViewById(R.id.spinner_idTenNguoiPM);
        tenSach = findViewById(R.id.spinner_idTenSachPM);
        ngayThue = findViewById(R.id.pmNgayThue);
        ngayTra = findViewById(R.id.pmNgayTra);
        giaThue = findViewById(R.id.giaThue);
        dateThue = findViewById(R.id.date);
        dateTra = findViewById(R.id.datetra);

        sachDAO = new SachDAO(this);
        thanhVienDAO = new ThanhVienDAO(this);
        phieuMuonDAO = new PhieuMuonDAO(this);

        mangIdSach = sachDAO.getId();

        mangIdThanhVien = thanhVienDAO.getId();


        spSach();
        spThanhVien();

        btnThem = findViewById(R.id.themPm);
        btnThoat = findViewById(R.id.thoatPM);

        PhieuMuon pm = new PhieuMuon();

        dateThue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("zzzz", "onClick: vao ");
                final Calendar calendar = Calendar.getInstance();
                int nam = calendar.get(Calendar.YEAR);
                int thang = calendar.get(Calendar.MONTH);
                int ngay = calendar.get(Calendar.DATE);

                DatePickerDialog dialog =new DatePickerDialog(ThemPhieuMuonActivity.this, new DatePickerDialog.OnDateSetListener() {
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
        dateTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("zzzz", "onClick: vao ");
                final Calendar calendar = Calendar.getInstance();
                int nam = calendar.get(Calendar.YEAR);
                int thang = calendar.get(Calendar.MONTH);
                int ngay = calendar.get(Calendar.DATE);

                DatePickerDialog dialog =new DatePickerDialog(ThemPhieuMuonActivity.this, new DatePickerDialog.OnDateSetListener() {
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

        nguoiMuon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                pm.setMaTV(Integer.parseInt(mangIdThanhVien.get(i)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        tenSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                pm.setMaSach(Integer.parseInt(mangIdSach.get(i)));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });



        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (nguoiMuon.getSelectedItem() == null || tenSach.getSelectedItem() == null ||
                        ngayThue.getText().toString().isEmpty() || ngayTra.getText().toString().isEmpty() ||
                        giaThue.getText().toString().isEmpty()) {
                    Toast.makeText(ThemPhieuMuonActivity.this, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(ThemPhieuMuonActivity.this, "Ngày không hợp lệ. Vui lòng nhập lại theo định dạng dd/MM/yyyy", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    int soLuongSach = Integer.parseInt(giaThue.getText().toString());
                    if (soLuongSach <= 0) {
                        Toast.makeText(getApplicationContext(), "Gia sách phải là một số dương", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Gia sách phải là số", Toast.LENGTH_SHORT).show();
                    return;
                }

                pm.setGiaThue(giaThue.getText().toString());
                pm.setTrangThai(0);
                pm.setNgayTra(ngayTra.getText().toString());
                pm.setNgayThue(ngayThue.getText().toString());

                int kq = phieuMuonDAO.themPM(pm);

                if(kq==-1){
                    Toast.makeText(ThemPhieuMuonActivity.this,"Thêm Thất Bại",Toast.LENGTH_SHORT).show();
                }else{

                    Bundle bundle = new Bundle();
                    bundle.putInt("myInt", 2);
                    Intent i = new Intent(ThemPhieuMuonActivity.this, MainActivity.class);
                    i.putExtras(bundle);
                    startActivity(i);
                    finish();

                    Toast.makeText(ThemPhieuMuonActivity.this,"Thêm Thành Công",Toast.LENGTH_SHORT).show();
                }



            }
        });

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("myInt", 2);
                Intent i = new Intent(ThemPhieuMuonActivity.this, MainActivity.class);
                i.putExtras(bundle);
                startActivity(i);
                finish();
            }
        });
    }

    private void spThanhVien() {
        mangThanhVien = thanhVienDAO.getName();
        ArrayAdapter<String> dulieuTV = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,mangThanhVien);
        dulieuTV.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nguoiMuon.setAdapter(dulieuTV);
        Log.d("zzzzzzzz", "spThanhVien: " + mangThanhVien);
    }

    private void spSach() {
        mangSach = sachDAO.getName();
        ArrayAdapter<String> dulieuS = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,mangSach);
        dulieuS.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tenSach.setAdapter(dulieuS);
    }
}