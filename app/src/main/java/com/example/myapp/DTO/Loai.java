package com.example.myapp.DTO;

public class Loai {
    private int idLoai;
    private String tenLoai;

    public Loai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public Loai() {
    }

    public Loai(int idLoai, String tenLoai) {
        this.idLoai = idLoai;
        this.tenLoai = tenLoai;
    }

    public int getIdLoai() {
        return idLoai;
    }

    public void setIdLoai(int idLoai) {
        this.idLoai = idLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
}
