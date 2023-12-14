package com.example.ledat;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private Database dataBase = new Database(this);
    private ListView lsvSong;
    private ArrayList<Baihat> arrayListBaiHat;
    private BaiHatAdapter adapterBaihat;

    private Button btnAdd;
    private EditText edTimkiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Map();
        //dataBase.xoaToanBo();
        add(); // add dữ liệu vào sqlite
        arrayListBaiHat= (ArrayList<Baihat>) dataBase.getAll();
        Collections.sort(arrayListBaiHat);
        adapterBaihat = new BaiHatAdapter(MainActivity.this, R.layout.viewbaihat, arrayListBaiHat);
        lsvSong.setAdapter(adapterBaihat);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent(MainActivity.this, AddActivity.class);
                startActivity(it);
            }
        });
        lsvSong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it =new Intent(MainActivity.this, ThongTinActivity.class);

                String name = arrayListBaiHat.get(position).getName();
                int idsong = arrayListBaiHat.get(position).getID();
                String singer = arrayListBaiHat.get(position).getSinger();
                Float time = arrayListBaiHat.get(position).getTime();

                it.putExtra("idsong", idsong);
                it.putExtra("name", name);
                it.putExtra("singer", singer);
                it.putExtra("time", time);

                startActivity(it);
            }
        });
        registerForContextMenu(lsvSong);
    }
    private void add(){
//        dataBase.addSong(new Baihat("Phút cuối", "Bằng Kiều", (float) 6.27));
//        dataBase.addSong(new Baihat("Bà tôi", "Tùng Dương", (float) 4.23));
//        dataBase.addSong(new Baihat("Tình cha", "Ngọc Sơn", (float) 2.13));
//        dataBase.addSong(new Baihat("Tình mẹ", "Ngọc Sơn", (float) 5.73));
//        dataBase.addSong(new Baihat("Giả vờ yêu", "Ngô Kiến Huy", (float) 3.23));
    }

    private void Map() {
        edTimkiem = (EditText)findViewById(R.id.edTimKiem);
        arrayListBaiHat = new ArrayList<>();
        lsvSong = (ListView) findViewById(R.id.lsvBaiHat);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        //dataBase.xoaToanBo();
    }
}