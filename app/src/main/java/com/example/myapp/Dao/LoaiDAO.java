package com.example.myapp.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapp.DTO.Loai;
import com.example.myapp.SQLite.DbHelper;

import java.util.ArrayList;

public class LoaiDAO {
    Context context;
    DbHelper mSqlite;
    SQLiteDatabase mSqLiteDatabase;

    public LoaiDAO(Context context) {
        this.context = context;
        mSqlite = new DbHelper(context);
        mSqLiteDatabase = mSqlite.getWritableDatabase();
    }

    public int themL(Loai s){
        ContentValues mValues = new ContentValues();
        mValues.put("tenLoai",s.getTenLoai());
        long kq = mSqLiteDatabase.insert("LoaiS",null,mValues);
        if(kq<0){
            return -1;
        }else{
            return 1;
        }
    }

    public ArrayList<Loai> getALL(){
        ArrayList<Loai> mArr = new ArrayList<>();
        Cursor c = mSqLiteDatabase.query("LoaiS",null,null,null,null,null,null);
        c.moveToFirst();
        while(c.isAfterLast()==false){
            Loai s = new Loai();
            s.setIdLoai(c.getInt(0));
            s.setTenLoai(c.getString(1));
            c.moveToNext();
            mArr.add(s);
        }
        c.close();
        return mArr;
    }

    public ArrayList<String> getId(){
        ArrayList<String> mArr = new ArrayList<>();
        Cursor c = mSqLiteDatabase.rawQuery("SELECT * FROM LoaiS",null);
        c.moveToFirst();
        while (c.isAfterLast()==false){

            Loai mLoaiChi = new Loai();
            mLoaiChi.setIdLoai(c.getInt(0));
            int ma = mLoaiChi.getIdLoai();
            mArr.add(String.valueOf(ma));
            c.moveToNext();
        }
        c.close();
        return mArr;
    }

    public ArrayList<String> getName(){
        ArrayList<String> Arr = new ArrayList<>();
        Cursor c = mSqLiteDatabase.rawQuery("SELECT * FROM LoaiS",null);
        c.moveToFirst();
        while(c.isAfterLast()==false){
            String x ="";
            Loai m = new Loai();
            x = c.getString(1);
            Arr.add(x);
            c.moveToNext();
        }
        return Arr;
    }

    public int xoaL(int id){
        int kq = mSqLiteDatabase.delete("LoaiS","maLoai=?",new String[]{String.valueOf(id)});
        if(kq<0){
            return -1;
        }else{
            return 1;
        }
    }

    public int update(Loai mlLoaiThu){
        ContentValues mValues = new ContentValues();
        mValues.put("tenLoai",mlLoaiThu.getTenLoai());
        long kq =  mSqLiteDatabase.update("LoaiS",mValues,"maLoai=?",new String[]{String.valueOf(mlLoaiThu.getIdLoai())});
        if(kq<0){
            return -1;
        }else{
            return 1;
        }
    }

}
