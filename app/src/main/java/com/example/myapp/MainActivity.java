package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.myapp.Fragment.Fragment_SACH.Fragment_Sach;
import com.example.myapp.Fragment.Fragment_caiDat;
import com.example.myapp.Fragment.Fragment_PM.Fragment_phieuMuon;
import com.example.myapp.Fragment.Fragment_theMuon;
import com.example.myapp.Fragment.Fragment_trangChu;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private long backpressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_nav_home);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            int myInt = bundle.getInt("myInt", 0);

            if (myInt == 1) {
                Log.d("zzzz", "Sach " +myInt);
                bottomNavigationView.getMenu().findItem(R.id.nav_book).setChecked(true);
                replaceFragment(new Fragment_Sach());
            }
            else if(myInt == 2){
                bottomNavigationView.getMenu().findItem(R.id.nav_pm).setChecked(true);
                replaceFragment(new Fragment_phieuMuon());
            }
            else {
                Log.d("zzzz", "Trang Chu " +myInt);
                replaceFragment(new Fragment_trangChu());
            }

        } else {
            // Không có Bundle được truyền từ Intent
            replaceFragment(new Fragment_trangChu());
        }

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        replaceFragment(new Fragment_trangChu());
                        break;
                    case R.id.nav_book:
                        replaceFragment(new Fragment_Sach());
                        break;
                    case R.id.nav_pm:
                        replaceFragment(new Fragment_phieuMuon());
                        break;
                    case R.id.nav_add:
                        replaceFragment(new Fragment_theMuon());
                        break;
                    case R.id.nav_account:
                        showAccountDialog(); // Gọi hàm hiển thị dialog
                        break;
                }
                return true;
            }
        });
    }
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.Frame_layout, fragment);
        transaction.commit();
    }

    public void onBackPressed() {
        if (backpressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
        }
        Toast.makeText(this, "Bấm 2 lần để thoát", Toast.LENGTH_SHORT).show();
        backpressedTime = System.currentTimeMillis();
    }
    private boolean isAccountDialogShowing = false;
    private void showAccountDialog() {
        if (isAccountDialogShowing) {
            return; // Nếu dialog đã được hiển thị thì không làm gì cả
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Xác nhận")
                .setMessage("Bạn có muốn đăng xuất?")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(getApplicationContext(),ManHinhDangNhap.class);
                        startActivity(i);
                        finish();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                isAccountDialogShowing = false; // Đánh dấu dialog đã đóng
            }
        });

        dialog.show();
        isAccountDialogShowing = true; // Đánh dấu dialog đang được hiển thị
    }

}