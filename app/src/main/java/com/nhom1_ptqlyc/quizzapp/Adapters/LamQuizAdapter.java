package com.nhom1_ptqlyc.quizzapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.nhom1_ptqlyc.quizzapp.R;
import com.nhom1_ptqlyc.quizzapp.objects.CauHoi;
import com.nhom1_ptqlyc.quizzapp.objects.CauTraLoi;

import java.util.ArrayList;

public class LamQuizAdapter extends BaseAdapter {
    Context mContext;
    int row_layout;
    ArrayList<CauHoi> listCauHoi;
    ArrayList<Integer> listCauTraLoiCuaNgDung;

    public LamQuizAdapter(Context mContext, int row_layout, ArrayList<CauHoi> listCauHoi) {
        this.mContext = mContext;
        this.row_layout = row_layout;
        this.listCauHoi = listCauHoi;
    }

    public ArrayList<Integer> getListCauTraLoiCuaNgDung() {
        return listCauTraLoiCuaNgDung;
    }

    @Override
    public int getCount() {
        return listCauHoi.size();
    }

    @Override
    public Object getItem(int position) {
        return listCauHoi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.row_hien_thi_cau_hoi,null);

        TextView thuTuCauHoi = convertView.findViewById(R.id.textView_thuTuCauHoi);
        TextView ndCauHoi = convertView.findViewById(R.id.textView_ndCauHoi);
        RadioGroup radioGroup = convertView.findViewById(R.id.radioGroup_cauTraLoi);
        RadioButton radioButton1= convertView.findViewById(R.id.radioButton_dapAn1);
        RadioButton radioButton2= convertView.findViewById(R.id.radioButton_dapAn2);
        RadioButton radioButton3= convertView.findViewById(R.id.radioButton_dapAn3);

        thuTuCauHoi.setText("Câu "+ (position+1)+": ");
        ndCauHoi.setText(listCauHoi.get(position).getNoiDung());
        ArrayList<CauTraLoi> listCauTraLoi = new ArrayList<>();
        listCauTraLoi.clear();
        listCauTraLoi=listCauHoi.get(position).getListCauTraLoi();
        radioButton1.setText(listCauTraLoi.get(0).getNoiDung());
        radioButton2.setText(listCauTraLoi.get(1).getNoiDung());
        radioButton3.setText(listCauTraLoi.get(2).getNoiDung());

//        if (radioButton1.isChecked()){
//            listCauTraLoiCuaNgDung.add(0);
//        } else if (radioButton2.isChecked()) {
//            listCauTraLoiCuaNgDung.add(1);
//        } else if (radioButton3.isChecked()) {
//            listCauTraLoiCuaNgDung.add(2);
//        }
        listCauTraLoiCuaNgDung=new ArrayList<>();
        radioButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    listCauTraLoiCuaNgDung.add(0);
                }
            }
        });
        radioButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    listCauTraLoiCuaNgDung.add(1);
                }
            }
        });
        radioButton3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    listCauTraLoiCuaNgDung.add(2);
                }
            }
        });

        return convertView;
    }
}
