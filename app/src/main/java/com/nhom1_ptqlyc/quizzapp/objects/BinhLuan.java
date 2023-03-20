package com.nhom1_ptqlyc.quizzapp.objects;

public class BinhLuan {
    String IDNguoiTao;
    String noiDung;
    boolean isShow;
    public BinhLuan() {
    }

    public BinhLuan(String IDNguoiTao, String noiDung, boolean isShow) {
        this.IDNguoiTao = IDNguoiTao;
        this.noiDung = noiDung;
        this.isShow = isShow;
    }

    public String getIDNguoiTao() {
        return IDNguoiTao;
    }

    public void setIDNguoiTao(String IDNguoiTao) {
        this.IDNguoiTao = IDNguoiTao;
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
