package com.nhom1_ptqlyc.quizzapp.objects;

import java.util.ArrayList;

public class ListBinhLuan {
    ArrayList<BinhLuan> listBinhLuan;

    public ListBinhLuan() {
    }

    public ListBinhLuan(ArrayList<BinhLuan> listBinhLuan) {
        this.listBinhLuan = listBinhLuan;
    }

    public ArrayList<BinhLuan> getListBinhLuan() {
        return listBinhLuan;
    }

    public void setListBinhLuan(ArrayList<BinhLuan> listBinhLuan) {
        this.listBinhLuan = listBinhLuan;
    }
}
