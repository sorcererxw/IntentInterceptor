package com.sorcererxw.intentinterceptor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        (findViewById(R.id.button_test)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                intent.putExtra("test arg1", "lalalala");
                intent.putExtra("test arg2", "lalsalsdasdasdasdas");
                startActivity(intent);
            }
        });
        File file = new File(getFilesDir(), "test");
        while (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        file.setWritable(true);
        file.setReadable(true);
        try {
            Log.d("test", FileUtils.readFileToString(file, (String) null));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
