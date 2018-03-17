package com.example.hoaiduc.thi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hoaiduc on 3/14/2018.
 */

public class AdapterSinhVien extends BaseAdapter {
    private Context context;
    private List<Sinhvien> sinhvienList;
    private int layout;

    public AdapterSinhVien(Context context, List<Sinhvien> sinhvienList, int layout) {
        this.context = context;
        this.sinhvienList = sinhvienList;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return sinhvienList.size();
    }

    @Override
    public Object getItem(int i) {
        return sinhvienList.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.dong_sinhvien,null);
        view=inflater.inflate(layout,null);
        TextView ten,ma,tuoi;
        ten=(TextView)view.findViewById(R.id.txtten);
        ma=(TextView)view.findViewById(R.id.txtmasv);
        tuoi=(TextView)view.findViewById(R.id.txttuoi);
        Sinhvien sv=(Sinhvien) getItem(i);
        ten.setText(sv.getTen());
        ma.setText(sv.getMasv());
        tuoi.setText(String.valueOf(sv.getTuoi()));
        return view;
    }
}
