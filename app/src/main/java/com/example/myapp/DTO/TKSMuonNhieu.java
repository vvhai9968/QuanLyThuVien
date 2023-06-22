package com.example.myapp.DTO;

public class TKSMuonNhieu {
    private int maSach;
    private int soLuong;

    public TKSMuonNhieu() {
    }

    public TKSMuonNhieu(int maSach, int soLuong) {
        this.maSach = maSach;
        this.soLuong = soLuong;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
