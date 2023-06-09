package com.nhom1_ptqlyc.quizzapp.objects;

import java.io.Serializable;
import java.util.ArrayList;

public class CauHoi implements Serializable {
    String noiDung;
    ArrayList<CauTraLoi> listCauTraLoi;

    public CauHoi() {
    }

    public CauHoi(String noiDung, ArrayList<CauTraLoi> listCauTraLoi) {
        this.noiDung = noiDung;
        this.listCauTraLoi = listCauTraLoi;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public ArrayList<CauTraLoi> getListCauTraLoi() {
        return listCauTraLoi;
    }

    public void setListCauTraLoi(ArrayList<CauTraLoi> listCauTraLoi) {
        this.listCauTraLoi = listCauTraLoi;
    }
}
