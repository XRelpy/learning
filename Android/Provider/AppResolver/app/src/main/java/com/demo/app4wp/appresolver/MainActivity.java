package com.demo.app4wp.appresolver;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btn_insert;
    private ContentResolver contentResolver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_insert = (Button) findViewById(R.id.btn_insert);
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vbtn_insert();
            }
        });

        contentResolver = getContentResolver();
    }

    private void vbtn_insert() {
        ContentValues values = new ContentValues();
        values.put("xreply", 123);
        contentResolver.insert(DictUtils.DICT_CONTENT_NUM, values);
        values.clear();
        values.put("xreply", true);
        contentResolver.insert(DictUtils.DICT_CONTENT_PRIVATE, values);
    }
}
