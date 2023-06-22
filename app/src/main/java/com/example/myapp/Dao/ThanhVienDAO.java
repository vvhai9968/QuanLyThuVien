package com.example.myapp.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myapp.DTO.Sach;
import com.example.myapp.DTO.ThanhVien;
import com.example.myapp.SQLite.DbHelper;

import java.util.ArrayList;

public class ThanhVienDAO {
    Context context;
    DbHelper mSqlite;
    SQLiteDatabase mSqLiteDatabase;

    public ThanhVienDAO(Context context) {
        this.context = context;
        mSqlite = new DbHelper(context);
        mSqLiteDatabase = mSqlite.getWritableDatabase();
    }

    public int themTV(ThanhVien s){
        ContentValues mValues = new ContentValues();
        mValues.put("hoTen",s.getHoTen());
        mValues.put("hinh",s.getHinh());
        mValues.put("namSinh",s.getNamSinh());
        long kq = mSqLiteDatabase.insert("ThanhVien",null,mValues);
        if(kq<0){
            return -1;
        }else{
            return 1;
        }
    }

    public ArrayList<ThanhVien> getALL(){
        ArrayList<ThanhVien> mArr = new ArrayList<>();
        Cursor c = mSqLiteDatabase.query("ThanhVien",null,null,null,null,null,null);
        c.moveToFirst();
        while(c.isAfterLast()==false){
            ThanhVien s = new ThanhVien();
            s.setMaTV(c.getInt(0));
            s.setHoTen(c.getString(1));
            s.setHinh(c.getBlob(2));
            s.setNamSinh(c.getString(3));
            c.moveToNext();


            mArr.add(s);
        }
        c.close();
        return mArr;
    }

    public ArrayList<String> getId(){
        ArrayList<String> mArr = new ArrayList<>();
        Cursor c = mSqLiteDatabase.rawQuery("SELECT * FROM ThanhVien",null);
        c.moveToFirst();

        while (c.isAfterLast() == false){

            ThanhVien ls = new ThanhVien();
            ls.setMaTV(c.getInt(0));
            int ma = ls.getMaTV();
            mArr.add(String.valueOf(ma));
            c.moveToNext();
        }

        c.close();
        return mArr;
    }


    public ArrayList<String> getName(){
        ArrayList<String> Arr = new ArrayList<>();
        Cursor c = mSqLiteDatabase.rawQuery("SELECT * FROM ThanhVien",null);

        c.moveToFirst();
        while (c.isAfterLast()==false){
            String  x ="";
            x = c.getString(1);
            Arr.add(x);
            c.moveToNext();
            Log.d("zzzzzzzz", "getName: " +x);
        }
        return Arr;
    }



    public int delete(int id){
        long kq =  mSqLiteDatabase.delete("ThanhVien","maTV=?",new String[]{String.valueOf(id)});
        if(kq<0){
            return -1;
        }else{
            return 1;
        }
    }

    public int updatetv(ThanhVien s){

        ContentValues mValues = new ContentValues();
        mValues.put("hoTen",s.getHoTen());
        mValues.put("namSinh",s.getNamSinh());

        long kq =  mSqLiteDatabase.update("ThanhVien",mValues,"maTV=?",new String[]{String.valueOf(s.getMaTV())});
        if(kq<0){
            return -1;
        }else{
            return 1;
        }
    }

}
