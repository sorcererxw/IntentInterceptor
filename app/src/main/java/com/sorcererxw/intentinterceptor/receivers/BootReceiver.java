package com.sorcererxw.intentinterceptor.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.sorcererxw.intentinterceptor.services.XposedPartnerService;
import com.sorcererxw.intentinterceptor.utils.MyData;

/**
 * @description:
 * @author: Sorcerer
 * @date: 2016/12/5
 */

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        MyData.createFile(context);
        Intent xposedPartnerIntent = new Intent(context, XposedPartnerService.class);
        context.startService(xposedPartnerIntent);
    }
}