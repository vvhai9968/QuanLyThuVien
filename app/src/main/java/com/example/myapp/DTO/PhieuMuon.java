package com.example.myapp.DTO;

public class PhieuMuon {
    private int maPM;
    private int maTV;
    private int maSach;
    private String ngayThue;
    private String ngayTra;
    private  String giaThue;
    private int trangThai;

    public PhieuMuon(int maPM, int maTV, int maSach, String ngayThue, String ngayTra, String giaThue, int trangThai) {
        this.maPM = maPM;
        this.maTV = maTV;
        this.maSach = maSach;
        this.ngayThue = ngayThue;
        this.ngayTra = ngayTra;
        this.giaThue = giaThue;
        this.trangThai = trangThai;
    }

    public PhieuMuon() {
    }

    public int getMaPM() {
        return maPM;
    }

    public void setMaPM(int maPM) {
        this.maPM = maPM;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getNgayThue() {
        return ngayThue;
    }

    public void setNgayThue(String ngayThue) {
        this.ngayThue = ngayThue;
    }

    public String getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(String ngayTra) {
        this.ngayTra = ngayTra;
    }

    public String getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(String giaThue) {
        this.giaThue = giaThue;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}
