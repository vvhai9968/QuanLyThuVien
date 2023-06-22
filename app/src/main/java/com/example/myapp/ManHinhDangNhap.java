package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ManHinhDangNhap extends AppCompatActivity {
    TextInputEditText user,pass;
    TextInputLayout checkuser,checkpass;
    CardView btnDN;
    String tk,mk;
    TextView thongBao ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_dang_nhap);

        user = findViewById(R.id.user);
        pass = findViewById(R.id.pass);
        checkuser = findViewById(R.id.checkuser);
        checkpass = findViewById(R.id.checkpass);

        btnDN = findViewById(R.id.dangnhap);
        thongBao = findViewById(R.id.tbdn);



        btnDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin();
            }
        });


    }
    private void checkLogin(){
        tk = user.getText().toString();
        mk = pass.getText().toString();

        if (!checkUser() | !checkPass()){
            return ;
        }
        else if(tk.equals("admin")&&mk.equals("admin")){
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
            finish();
        }else {
            Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
        }
    }
    private boolean checkUser() {
        String user = checkuser.getEditText().getText().toString().trim();
        if (user.isEmpty()) {
            checkuser.setError(" ");
            return false;
        } else {
            checkuser.setError(null);
            return true;
        }
    }
    private boolean checkPass() {
        String pass = checkpass.getEditText().getText().toString().trim();
        if (pass.isEmpty() ) {
            checkpass.setError(" ");
            return false;
        } else {
            checkpass.setError(null);
            return true;
        }
    }

}