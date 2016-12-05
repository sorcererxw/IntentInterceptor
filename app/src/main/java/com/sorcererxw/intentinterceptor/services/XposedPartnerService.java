package com.sorcererxw.intentinterceptor.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * @description:
 * @author: Sorcerer
 * @date: 2016/12/5
 */

public class XposedPartnerService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("XposedPartnerService", "启动服务");
        getItself();
    }

    public Service getItself() {
        return this;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
