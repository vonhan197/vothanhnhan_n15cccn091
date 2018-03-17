package com.example.hoaiduc.thi;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnFocusChangeListener,View.OnClickListener {
    EditText ma,ten,tuoi;
    EditText suama,suaten,suatuoi;

    Button btnthem,btnhuy,xong;
    List<Sinhvien> sinhvienList;
    AlertDialog.Builder mBuilder;
    View mView;
    AlertDialog dialog;
    ListView listView;
    AdapterSinhVien adapterSinhVien;
    boolean kiemtra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        kiemtra=false;
        listView=(ListView) findViewById(R.id.listview);
        sinhvienList=new ArrayList<Sinhvien>();
        registerForContextMenu(listView);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.them:
                hienthiDialog();

                ;break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        int id=view.getId();
        switch (id)
        {
            case R.id.extma:
                String masv=null;
                if(!b)
                {
                    masv=((EditText)view).getText().toString().trim();
                    if(masv.trim().equals(null)||masv.trim().matches(""))
                    {
                        kiemtra=false;
                    }
                    else
                    {
                        kiemtra=true;
                    }
                }


                ;break;
            case R.id.extten:
                 String tensv=null;
                if(!b)
                {
                    tensv=((EditText)view).getText().toString().trim();
                    if(tensv.equals(null)||tensv.trim().matches(""))
                    {
                        kiemtra=false;
                    }
                    else
                    {
                        kiemtra=true;
                    }
                }

                ;break;
            case R.id.tuoi:
                String tuoisv=null;
                if(!b)
                {
                    tuoisv=((EditText)view).getText().toString().trim();
                    if(tuoisv.equals(null)||tuoisv.matches(""))
                    {

                        kiemtra=false;
                    }
                    else
                    {
                        Toast.makeText(this, ""+tuoisv, Toast.LENGTH_SHORT).show();
                        kiemtra=true;
                    }
                }
                else
                {
                    tuoisv=((EditText)view).getText().toString().trim();
                    if(tuoisv.equals(null)||tuoisv.matches(""))
                    {

                        kiemtra=false;
                    }
                    else
                    {
                        kiemtra=true;
                    }
                }

                ;break;

        }
    }
    private void layDulieu()
    {
            if(kiemtrathem())
            {
                String a=tuoi.getText().toString().trim();
                Log.d("aa",a);
                int tuoisv=Integer.parseInt(tuoi.getText().toString().trim());
                String tensv=ten.getText().toString().trim();
                String masv=ma.getText().toString().trim();
                Sinhvien sv=new Sinhvien();
                sv.setMasv(masv);
                sv.setTen(tensv);
                sv.setTuoi(tuoisv);
                sinhvienList.add(sv);
                adapterSinhVien=new AdapterSinhVien(this,sinhvienList,R.layout.dong_sinhvien);
                listView.setAdapter(adapterSinhVien);
                adapterSinhVien.notifyDataSetChanged();
                Toast.makeText(this, "thêm thành công", Toast.LENGTH_SHORT).show();
            }

        else
        {
            Toast.makeText(this, "vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }
    }
    private void hienthiDialog()
    {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.activity_them, null);
        ma=(EditText)    mView.findViewById(R.id.extma);
        ten=(EditText)   mView.findViewById(R.id.extten);
        tuoi=(EditText)  mView.findViewById(R.id.tuoi);
        btnhuy=(Button)  mView.findViewById(R.id.btnhuy);
        btnthem=(Button) mView.findViewById(R.id.btnthem);
        mBuilder.setView(mView);
        btnthem.setOnClickListener(this);
       /* ma.setOnFocusChangeListener(this);
        ten.setOnFocusChangeListener(this);
        tuoi.setOnFocusChangeListener(this);*/
        dialog = mBuilder.create();
        dialog.show();
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
    @Override
    public void onClick(View view)
    {
        int id = view.getId();
        switch (id) {
            case R.id.btnthem:
                layDulieu();
                break;
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId())
        {
            case R.id.xoa:

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                mBuilder.setMessage("bạn có muốn xóa")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                sinhvienList.remove(info.position);
                                adapterSinhVien.notifyDataSetChanged();
                            }
                        })
                 .setNegativeButton("hủy",null);
                dialog = mBuilder.create();
                dialog.show();
                break;
            case R.id.sua:

                hienThiHopThoai(sinhvienList.get(info.position),info.position);
                break;
        }
        return super.onContextItemSelected(item);
    }
    private void hienThiHopThoai(Sinhvien oldItem, final int index)
    {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.update, null);
         suama=(EditText)    mView.findViewById(R.id.extsuama);
        suaten=(EditText)   mView.findViewById(R.id.extsuaten);
        suatuoi=(EditText)  mView.findViewById(R.id.extsuatuoi);
        xong=(Button)        mView.findViewById(R.id.btnxong);
        xong.setOnClickListener(this);
        mBuilder.setView(mView);
        suama.setText(oldItem.getMasv());
        suaten.setText(oldItem.getTen());
        suatuoi.setText(String.valueOf(oldItem.getTuoi()));




            xong.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(kiemTraSua())
                    {


                        String ten = suaten.getText().toString();
                        String ma = suama.getText().toString();
                        int tuoi = Integer.parseInt(suatuoi.getText().toString());
                        final Sinhvien sv = new Sinhvien();
                        sv.setTen(ten);
                        sv.setTuoi(tuoi);
                        sv.setMasv(ma);
                        sinhvienList.set(index, sv);
                        adapterSinhVien.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "vui lòng không để trống thông tin", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        dialog = mBuilder.create();
            dialog.show();

    }
    private  boolean kiemtrathem()
    {
        String masv=ma.getText().toString().trim();
        String tensv=ten.getText().toString().trim();
        String tuoisv=tuoi.getText().toString().trim();
        if(masv.trim().equals(null)||masv.trim().matches("")
           ||tensv.trim().equals(null)||tensv.trim().matches("")
           || tuoisv.trim().equals(null)||tuoisv.trim().matches(""))
        {
            kiemtra=false;
        }
        else
        {
            kiemtra=true;
        }

        return kiemtra;
    }
    private boolean kiemTraSua()
    {
        boolean check=false;
        String suamasv=suama.getText().toString().trim();
        String suatensv=suaten.getText().toString().trim();
        String suatuoisv=suatuoi.getText().toString().trim();
        if(suamasv.trim().equals(null)||
           suamasv.trim().equals("")||
           suatensv.trim().equals(null)||suatensv.trim().equals("")
           ||suatuoisv.trim().equals(null)||suatuoisv.trim().equals(""))
        {
            check=false;
        }
        else
        {
            check=true;
        }
        return check;
    }
}
