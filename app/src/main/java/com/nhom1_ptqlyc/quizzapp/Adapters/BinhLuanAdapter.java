package com.nhom1_ptqlyc.quizzapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhom1_ptqlyc.quizzapp.R;
import com.nhom1_ptqlyc.quizzapp.objects.BinhLuan;
import com.nhom1_ptqlyc.quizzapp.objects.ListBinhLuan;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class BinhLuanAdapter extends BaseAdapter {
    Context mContext;
    ListBinhLuan listBinhLuan;

    public BinhLuanAdapter(Context mContext, ListBinhLuan listBinhLuan) {
        this.mContext = mContext;
        this.listBinhLuan = listBinhLuan;
    }

    @Override
    public int getCount() {
        return listBinhLuan.getListBinhLuan().size();
    }

    @Override
    public Object getItem(int position) {
        return listBinhLuan.getListBinhLuan().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.row_binhluan,null);

        TextView textView_username= convertView.findViewById(R.id.textView_username);
        TextView textView_ndungBinhLuan= convertView.findViewById(R.id.textView_ndungBinhLuan);
        ImageView imageView_avatar= convertView.findViewById(R.id.imageView_avatar);

        textView_username.setText(listBinhLuan.getListBinhLuan().get(position).getIDNguoiTao());
        textView_ndungBinhLuan.setText(listBinhLuan.getListBinhLuan().get(position).getNoiDung());

        if (!listBinhLuan.getListBinhLuan().get(position).getHinhAnhNguoiTao().isEmpty()){
            Picasso.get().load(listBinhLuan.getListBinhLuan().get(position).getHinhAnhNguoiTao()).into(imageView_avatar);
        }

        return convertView;
    }
}
