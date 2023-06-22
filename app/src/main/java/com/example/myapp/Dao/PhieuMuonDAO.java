package com.example.myapp.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myapp.DTO.PhieuMuon;
import com.example.myapp.DTO.Sach;
import com.example.myapp.DTO.TKSMuonNhieu;
import com.example.myapp.SQLite.DbHelper;

import java.util.ArrayList;

public class PhieuMuonDAO {
    Context context;
    DbHelper mSqlite;
    SQLiteDatabase mSqLiteDatabase;

    public PhieuMuonDAO(Context context) {
        this.context = context;
        mSqlite = new DbHelper(context);
        mSqLiteDatabase = mSqlite.getWritableDatabase();
    }

    public int themPM(PhieuMuon s){

        ContentValues mValues = new ContentValues();
        mValues.put("maTV",s.getMaTV());
        mValues.put("maSach",s.getMaSach());
        mValues.put("ngayThue",s.getNgayThue());
        mValues.put("ngayTra",s.getNgayTra());
        mValues.put("giaThue",s.getGiaThue());
        mValues.put("trangThai",s.getTrangThai());
        long kq = mSqLiteDatabase.insert("PhieuMuon",null,mValues);
        if(kq<0){
            return -1;
        }else{
            return 1;
        }
    }

    public ArrayList<PhieuMuon> getALLDaTra() {
        ArrayList<PhieuMuon> mArr = new ArrayList<>();
        String[] columns = {"MaPM", "MaTV", "MaSach", "NgayThue", "NgayTra", "GiaThue", "TrangThai"};
        String selection = "TrangThai = ?";
        String[] selectionArgs = {"1"};
        Cursor c = mSqLiteDatabase.query("PhieuMuon", columns, selection, selectionArgs, null, null, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            PhieuMuon s = new PhieuMuon();
            s.setMaPM(c.getInt(0));
            s.setMaTV(c.getInt(1));
            s.setMaSach(c.getInt(2));
            s.setNgayThue(c.getString(3));
            s.setNgayTra(c.getString(4));
            s.setGiaThue(c.getString(5));
            s.setTrangThai(c.getInt(6));
            c.moveToNext();
            mArr.add(s);
        }
        c.close();
        return mArr;
    }


    public ArrayList<PhieuMuon> getALLChuaTra() {
        ArrayList<PhieuMuon> mArr = new ArrayList<>();
        String[] columns = {"MaPM", "MaTV", "MaSach", "NgayThue", "NgayTra", "GiaThue", "TrangThai"};
        String selection = "TrangThai = ?";
        String[] selectionArgs = {"0"};
        Cursor c = mSqLiteDatabase.query("PhieuMuon", columns, selection, selectionArgs, null, null, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            PhieuMuon s = new PhieuMuon();
            s.setMaPM(c.getInt(0));
            s.setMaTV(c.getInt(1));
            s.setMaSach(c.getInt(2));
            s.setNgayThue(c.getString(3));
            s.setNgayTra(c.getString(4));
            s.setGiaThue(c.getString(5));
            s.setTrangThai(c.getInt(6));
            c.moveToNext();
            mArr.add(s);
        }
        c.close();
        return mArr;
    }

    public ArrayList<String> getId(){
        ArrayList<String> mArr = new ArrayList<>();
        Cursor c = mSqLiteDatabase.rawQuery("SELECT * FROM PhieuMuon",null);
        c.moveToFirst();

        while (c.isAfterLast()==false){

            PhieuMuon ls = new PhieuMuon();
            ls.setMaPM(c.getInt(0));
            int ma = ls.getMaPM();

            mArr.add(String.valueOf(ma));

            c.moveToNext();
        }

        c.close();
        return mArr;
    }


    public PhieuMuon selectOne(int id){
        mSqLiteDatabase = mSqlite.getReadableDatabase();
        PhieuMuon s = new PhieuMuon();

        String sql = "SELECT * FROM PhieuMuon where maPM = "+id;

        Cursor c =  mSqLiteDatabase.rawQuery(sql, null);
        if(c.moveToFirst()){

            s.setMaPM(c.getInt(0));
            s.setMaTV(c.getInt(1));
            s.setMaSach(c.getInt(2));
            s.setNgayThue(c.getString(3));
            s.setNgayTra(c.getString(4));
            s.setGiaThue(c.getString(5));
            s.setTrangThai(c.getInt(6));

        }

        return s;
    }


    public int delete(int id){
        long kq =  mSqLiteDatabase.delete("PhieuMuon","maPM=?",new String[]{String.valueOf(id)});
        if(kq<0){
            return -1;
        }else{
            return 1;
        }
    }



    public int updatePhieuMuon(PhieuMuon s){

        ContentValues mValues = new ContentValues();
        mValues.put("maTV",s.getMaTV());
        mValues.put("maSach",s.getMaSach());
        mValues.put("ngayThue",s.getNgayThue());
        mValues.put("ngayTra",s.getNgayTra());
        mValues.put("giaThue",s.getNgayThue());
        mValues.put("trangThai",s.getTrangThai());

        long kq =  mSqLiteDatabase.update("PhieuMuon",mValues,"maPM=?",new String[]{String.valueOf(s.getMaPM())});
        if(kq<0){
            return -1;
        }else{
            return 1;
        }
    }

}

