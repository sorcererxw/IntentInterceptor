package com.sorcererxw.intentinterceptor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.sorcererxw.intentinterceptor.models.DataBean;
import com.sorcererxw.intentinterceptor.ui.adapters.DataAdapter;
import com.sorcererxw.intentinterceptor.utils.MyData;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private DataAdapter mDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        MyData.createFile(this);
        mDataAdapter = new DataAdapter(this);
        mRecyclerView.setAdapter(mDataAdapter);
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
    }

    @Override
    protected void onResume() {
        super.onResume();
        StringBuilder builder = new StringBuilder();
        try {
            FileInputStream fis = MainActivity.this.openFileInput("intent_data");
            int ch;
            while ((ch = fis.read()) != -1) {
                builder.append((char) ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str = "[" + builder.toString() + "]";
        List<DataBean> list = new ArrayList<>();
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonElement el = parser.parse(str);
        JsonArray jsonArray = el.getAsJsonArray();
        for (JsonElement je : jsonArray) {
            DataBean dataBean = gson.fromJson(je, DataBean.class);
            if (dataBean != null) {
                list.add(dataBean);
            }
        }
        mDataAdapter.setData(list);
    }
}
