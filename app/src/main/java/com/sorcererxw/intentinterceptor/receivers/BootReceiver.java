package com.sorcererxw.intentinterceptor.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.sorcererxw.intentinterceptor.utils.DataUtil;

/**
 * @description:
 * @author: Sorcerer
 * @date: 2016/12/5
 */

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        DataUtil.createFile(context);
    }
}
