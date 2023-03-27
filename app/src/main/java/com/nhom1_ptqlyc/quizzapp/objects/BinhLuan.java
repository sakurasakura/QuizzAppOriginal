package com.nhom1_ptqlyc.quizzapp.objects;

public class BinhLuan {
    String IDNguoiTao;
    String hinhAnhNguoiTao;
    String noiDung;
    boolean isShow;

    public BinhLuan() {
    }

    public BinhLuan(String IDNguoiTao, String hinhAnhNguoiTao, String noiDung, boolean isShow) {
        this.IDNguoiTao = IDNguoiTao;
        this.hinhAnhNguoiTao = hinhAnhNguoiTao;
        this.noiDung = noiDung;
        this.isShow = isShow;
    }

    public String getIDNguoiTao() {
        return IDNguoiTao;
    }

    public void setIDNguoiTao(String IDNguoiTao) {
        this.IDNguoiTao = IDNguoiTao;
    }

    public String getHinhAnhNguoiTao() {
        return hinhAnhNguoiTao;
    }

    public void setHinhAnhNguoiTao(String hinhAnhNguoiTao) {
        this.hinhAnhNguoiTao = hinhAnhNguoiTao;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }
}
