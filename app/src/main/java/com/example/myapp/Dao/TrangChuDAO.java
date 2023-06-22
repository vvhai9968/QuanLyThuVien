package com.example.myapp.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myapp.DTO.TKSMuonNhieu;
import com.example.myapp.SQLite.DbHelper;

import java.util.ArrayList;

public class TrangChuDAO {
    Context context;
    DbHelper mSqlite;
    SQLiteDatabase mSqLiteDatabase;

    public TrangChuDAO(Context context) {
        this.context = context;
        mSqlite = new DbHelper(context);
        mSqLiteDatabase = mSqlite.getWritableDatabase();
    }

    public int checkLanTra(){
        Cursor c = mSqLiteDatabase.rawQuery("SELECT * FROM PhieuMuon where TrangThai='1' ",null);
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
    public int checkLanMuon(){
        Cursor c = mSqLiteDatabase.rawQuery("SELECT * FROM PhieuMuon ",null);
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
    //Thong ke top 10
    public ArrayList<TKSMuonNhieu> getTop(){
        ArrayList<TKSMuonNhieu> mArr = new ArrayList<>();
        Cursor c = mSqLiteDatabase.rawQuery("SELECT MaSach, count(MaSach) as soLuong FROM PhieuMuon GROUP BY MaSach ORDER BY soLuong DESC LIMIT 10",null);
        c.moveToFirst();
        while (c.isAfterLast()==false){

            TKSMuonNhieu s = new TKSMuonNhieu();
            s.setMaSach(c.getInt(0));
            s.setSoLuong(c.getInt(1));

            mArr.add(s);
            c.moveToNext();
            Log.d("zzzzzzzz", "getTop: " +s.getMaSach());
        }
        c.close();
        return mArr;
    }

    public double tongTien(){
        Cursor c = mSqLiteDatabase.rawQuery("SELECT SUM(giaThue) FROM PhieuMuon ",null);
        double tt = 0;
        c.moveToFirst();
        tt = c.getDouble(0);
        c.close();
        return tt;
    }
}
