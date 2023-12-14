package com.example.ledat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ThongTinActivity extends AppCompatActivity {

    EditText edName, edSinger, edTime;

    Button btnUpdate, btnBack, btnDelete;
    TextView edid;
    private Database dataBase = new Database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin);

        edName = findViewById(R.id.edName);
        edSinger = findViewById(R.id.edSinger);
        edTime = findViewById(R.id.edTime);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnBack = findViewById((R.id.btnBack));
        edid = findViewById(R.id.edid);
        btnDelete = findViewById(R.id.btnDelete);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        int id = intent.getIntExtra("idsong", 0);
        String singer = intent.getStringExtra("singer");
        float time = intent.getFloatExtra("time", 0);

        edName.setText(name);
        edSinger.setText(singer);
        edTime.setText(Float.toString(time));
        edid.setText(String.valueOf(id));
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBase.updateSong(id, String.valueOf(edName.getText()), String.valueOf(edSinger.getText()),Float.valueOf(String.valueOf(edTime.getText())));
                Intent it =new Intent(ThongTinActivity.this, MainActivity.class);
                startActivity(it);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent(ThongTinActivity.this, MainActivity.class);
                startActivity(it);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBase.deleteSong(id);
                Intent it =new Intent(ThongTinActivity.this, MainActivity.class);
                startActivity(it);
            }
        });

    }
}