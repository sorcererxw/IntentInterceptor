package com.sorcererxw.intentinterceptor;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.sorcererxw.intentinterceptor.models.DataBean;
import com.sorcererxw.intentinterceptor.ui.adapters.DataAdapter;
import com.sorcererxw.intentinterceptor.utils.DataUtil;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.textView_hint)
    TextView mHintTextView;

    private DataAdapter mDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        DataUtil.createFile(this);
        mDataAdapter = new DataAdapter(this);
        mRecyclerView.setAdapter(mDataAdapter);
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= 24) {
            mHintTextView.setText(getString(R.string.hint_not_support_nougat));
        } else {
            try {
                updateList();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateList() throws IOException {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    subscriber.onNext(DataUtil.read());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).map(new Func1<String, List<DataBean>>() {
            @Override
            public List<DataBean> call(String s) {
                List<DataBean> list = new ArrayList<>();
                Gson gson = new Gson();
                JsonParser parser = new JsonParser();
                JsonElement el = parser.parse(s);
                JsonArray jsonArray = el.getAsJsonArray();
                for (JsonElement je : jsonArray) {
                    DataBean dataBean = gson.fromJson(je, DataBean.class);
                    if (dataBean != null) {
                        list.add(dataBean);
                    }
                }
                Collections.reverse(list);
                return list;
            }
        })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<DataBean>>() {
                    @Override
                    public void call(List<DataBean> dataBeen) {
                        if (dataBeen.size() == 0) {
                            mHintTextView.setText(getString(R.string.hint_not_data).replace("|","\n"));
                        } else {
                            mHintTextView.setText("");
                        }
                        mDataAdapter.setData(dataBeen);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_clear:
                try {
                    DataUtil.clear();
                    mDataAdapter.clearData();
                    mHintTextView.setText(getString(R.string.hint_not_data).replace("|","\n"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
