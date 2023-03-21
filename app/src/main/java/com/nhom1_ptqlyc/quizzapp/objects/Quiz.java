package com.nhom1_ptqlyc.quizzapp.objects;

import java.io.Serializable;
import java.util.ArrayList;

public class Quiz implements Serializable {
    String ten;
    String nguoiTao;
    String hinhAnhURL;
    String chuDe;
    String ngayTao;
    long luotLam;
    float rating;
    int gioiHanThoiGian;
    int soLuongCauHoi;

    ArrayList<CauHoi> listCauHoi;

    public Quiz() {
    }

    public Quiz(String ten, String nguoiTao, String hinhAnhURL, String chuDe, String ngayTao, long luotLam, float rating, int gioiHanThoiGian, int soLuongCauHoi, ArrayList<CauHoi> listCauHoi) {
        this.ten = ten;
        this.nguoiTao = nguoiTao;
        this.hinhAnhURL = hinhAnhURL;
        this.chuDe = chuDe;
        this.ngayTao = ngayTao;
        this.luotLam = luotLam;
        this.rating = rating;
        this.gioiHanThoiGian = gioiHanThoiGian;
        this.soLuongCauHoi = soLuongCauHoi;
        this.listCauHoi = listCauHoi;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public String getHinhAnhURL() {
        return hinhAnhURL;
    }

    public void setHinhAnhURL(String hinhAnhURL) {
        this.hinhAnhURL = hinhAnhURL;
    }

    public String getChuDe() {
        return chuDe;
    }

    public void setChuDe(String chuDe) {
        this.chuDe = chuDe;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public long getLuotLam() {
        return luotLam;
    }

    public void setLuotLam(long luotLam) {
        this.luotLam = luotLam;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getGioiHanThoiGian() {
        return gioiHanThoiGian;
    }

    public void setGioiHanThoiGian(int gioiHanThoiGian) {
        this.gioiHanThoiGian = gioiHanThoiGian;
    }

    public int getSoLuongCauHoi() {
        return soLuongCauHoi;
    }

    public void setSoLuongCauHoi(int soLuongCauHoi) {
        this.soLuongCauHoi = soLuongCauHoi;
    }

    public ArrayList<CauHoi> getListCauHoi() {
        return listCauHoi;
    }

    public void setListCauHoi(ArrayList<CauHoi> listCauHoi) {
        this.listCauHoi = listCauHoi;
    }
}
