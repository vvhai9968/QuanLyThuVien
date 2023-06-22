package com.example.myapp.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    static final String DB_NAME = "myapp.db";
    static final int DB_VERSION = 1;

    public DbHelper( Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tao bang tai khoan
        String crTK = "CREATE TABLE taiKhoan (" +
                "tenDN text  primary key," +
                "hoten text, " +
                "mk text )";
        db.execSQL(crTK);

        String crL = "CREATE TABLE LoaiS(" +
                "maLoai INTEGER PRIMARY key AUTOINCREMENT," +
                " tenLoai TEXT)";
        db.execSQL(crL);

        String crS ="create table Sach(" +
                "maSach integer primary key autoincrement," +
                "tenSach text ," +
                "nhaXuatBan text ," +
                "tacGia text ," +
                "soLuong integer ," +
                " hinh blod," +
                "maLoai integer ," +
                "foreign key(maLoai) references  LoaiSach(maLoai))";
        db.execSQL(crS);

        String crTV ="create table ThanhVien(" +
                "maTV integer primary key autoincrement," +
                "hoTen text ," +
                " hinh blod," +
                "namSinh text )";
        db.execSQL(crTV);

        String crPM ="create table PhieuMuon(" +
                "maPM integer primary key autoincrement," +
                "maTV integer ," +
                "maSach integer ," +
                "ngayThue text," +
                "ngayTra text," +
                "giaThue text, " +
                "trangThai integer," +
                "foreign key(maTV) references  ThanhVien(maTV)," +
                "foreign key(maSach) references  Sach(maSach))";
        db.execSQL(crPM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}