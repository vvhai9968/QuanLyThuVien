package com.example.myapp.DTO;

public class ThanhVien {
    private int maTV;
    private String hoTen;
    private byte[] hinh;
    private String namSinh;

    public ThanhVien() {
    }

    public ThanhVien(int maTV, String hoTen, byte[] hinh, String namSinh) {
        this.maTV = maTV;
        this.hoTen = hoTen;
        this.hinh = hinh;
        this.namSinh = namSinh;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public byte[] getHinh() {
        return hinh;
    }

    public void setHinh(byte[] hinh) {
        this.hinh = hinh;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }
}
