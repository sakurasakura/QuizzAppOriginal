package com.nhom1_ptqlyc.quizzapp.objects;

public class Quiz {
    String ten;
    String nguoiTao;
    String hinhAnh;
    String chuDe;
    String ngayTao;
    long luotLam;
    float rating;
    int gioiHanThoiGian;
    int soLuongCauHoi;
    int soLuongCauTraLoi;

    public Quiz(String ten, String nguoiTao, String hinhAnh, String chuDe, String ngayTao, long luotLam, float rating, int gioiHanThoiGian, int soLuongCauHoi, int soLuongCauTraLoi) {
        this.ten = ten;
        this.nguoiTao = nguoiTao;
        this.hinhAnh = hinhAnh;
        this.chuDe = chuDe;
        this.ngayTao = ngayTao;
        this.luotLam = luotLam;
        this.rating = rating;
        this.gioiHanThoiGian = gioiHanThoiGian;
        this.soLuongCauHoi = soLuongCauHoi;
        this.soLuongCauTraLoi = soLuongCauTraLoi;
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

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
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

    public int getSoLuongCauTraLoi() {
        return soLuongCauTraLoi;
    }

    public void setSoLuongCauTraLoi(int soLuongCauTraLoi) {
        this.soLuongCauTraLoi = soLuongCauTraLoi;
    }
}
