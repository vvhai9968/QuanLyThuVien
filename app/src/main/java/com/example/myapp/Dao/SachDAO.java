package com.example.myapp.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myapp.DTO.Loai;
import com.example.myapp.DTO.Sach;
import com.example.myapp.SQLite.DbHelper;

import java.util.ArrayList;

public class SachDAO {
    Context context;
    DbHelper mSqlite;
    SQLiteDatabase mSqLiteDatabase;

    public SachDAO(Context context) {
        this.context = context;
        mSqlite = new DbHelper(context);
        mSqLiteDatabase = mSqlite.getWritableDatabase();
    }

    public int themS(Sach s){
        ContentValues mValues = new ContentValues();
        mValues.put("tenSach",s.getTenSach());
        mValues.put("nhaXuatBan",s.getNhaXuatBan());
        mValues.put("tacGia",s.getTacGia());
        mValues.put("soLuong",s.getSoLuong());
        mValues.put("hinh",s.getHinh());
        mValues.put("maLoai",s.getMaLoai());
        long kq = mSqLiteDatabase.insert("Sach",null,mValues);
        if(kq<0){
            return -1;
        }else{
            return 1;
        }
    }

    public ArrayList<Sach> getALL(){
        ArrayList<Sach> mArr = new ArrayList<>();
        Cursor c = mSqLiteDatabase.query("Sach",null,null,null,null,null,null);
        c.moveToFirst();
        while(c.isAfterLast()==false){
            Sach s = new Sach();
            s.setMaS(c.getInt(0));
            s.setTenSach(c.getString(1));
            s.setNhaXuatBan(c.getString(2));
            s.setTacGia(c.getString(3));
            s.setSoLuong(c.getInt(4));
            s.setHinh(c.getBlob(5));
            s.setMaLoai(c.getInt(6));

            c.moveToNext();
            mArr.add(s);
        }
        c.close();
        return mArr;
    }

    public ArrayList<String> getId(){
        ArrayList<String> mArr = new ArrayList<>();
        Cursor c = mSqLiteDatabase.rawQuery("SELECT * FROM Sach",null);
        c.moveToFirst();

        while (c.isAfterLast()==false){

            Sach ls = new Sach();
            ls.setMaS(c.getInt(0));
            int ma = ls.getMaS();

            mArr.add(String.valueOf(ma));

            c.moveToNext();
        }

        c.close();
        return mArr;
    }
    public int checkHang(){
        Cursor c = mSqLiteDatabase.rawQuery("SELECT * FROM Sach",null);
        Log.d("Count",c.getCount()+"");
        c.moveToFirst();
        while ( !c.isAfterLast()){
            int  a = c.getCount();
            c.moveToNext();
            c.close();
            return a;
        }

        return 0;
    }

    public ArrayList<String> getName(){
        ArrayList<String> Arr = new ArrayList<>();
        Cursor c = mSqLiteDatabase.rawQuery("SELECT * FROM Sach",null);

        c.moveToFirst();
        while (c.isAfterLast()==false){
            String  x ="";
            x = c.getString(1);
            Arr.add(x);
            c.moveToNext();
        }
        return Arr;
    }
    public Sach selectOne(int id){
        mSqLiteDatabase = mSqlite.getReadableDatabase();
        Sach s = new Sach();

        String sql = "SELECT * FROM Sach where maSach = "+id;

        Cursor c =  mSqLiteDatabase.rawQuery(sql, null);
        if(c.moveToFirst()){

            s.setMaS(c.getInt(0));
            s.setTenSach(c.getString(1));
            s.setNhaXuatBan(c.getString(2));
            s.setTacGia(c.getString(3));
            s.setSoLuong(c.getInt(4));
            s.setHinh(c.getBlob(5));
            s.setMaLoai(c.getInt(6));

        }

        return s;
    }


    public int delete(int id){
        long kq =  mSqLiteDatabase.delete("Sach","maSach=?",new String[]{String.valueOf(id)});
        if(kq<0){
            return -1;
        }else{
            return 1;
        }
    }

    public int updateSach(Sach s){

        ContentValues mValues = new ContentValues();
        mValues.put("tenSach",s.getTenSach());
        mValues.put("nhaXuatBan",s.getNhaXuatBan());
        mValues.put("tacGia",s.getTacGia());
        mValues.put("soLuong",s.getSoLuong());
        mValues.put("hinh",s.getHinh());
        mValues.put("maLoai",s.getMaLoai());

        long kq =  mSqLiteDatabase.update("Sach",mValues,"maSach=?",new String[]{String.valueOf(s.getMaS())});
        if(kq<0){
            return -1;
        }else{
            return 1;
        }
    }

}
