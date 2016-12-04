package com.sorcererxw.intentinterceptor;

import android.content.Intent;
import android.os.Bundle;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

/**
 * @description:
 * @author: Sorcerer
 * @date: 2016/12/4
 */

public class Xposed implements IXposedHookLoadPackage {
    private XC_MethodHook mIntentHook = new XC_MethodHook() {
        @Override
        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
            super.beforeHookedMethod(param);
            XposedBridge.log("开始劫持了~");
            Intent intent = (Intent) param.args[0];
            Bundle intentBundle = intent.getExtras();
            log("intent action: " + intent.getAction());
            log("intent component: " + intent.getComponent());
            log("intent package: " + intent.getPackage());
            log("intent scheme: " + intent.getScheme());
            log("intent type: " + intent.getType());
            log("intent dataString: " + intent.getDataString());
            log("intent clipData: " + intent.getClipData());
            log("intent flag: " + intent.getFlags());
            if (intentBundle != null) {
                for (String key : intentBundle.keySet()) {
                    Object value = intentBundle.get(key);
                    log("intent bundle: "
                            + String.format("{key: %s,\nvalue: %s,\nclass: (%s)}",
                            key, value.toString(), value.getClass().getName()));
                }
            }
            Bundle bundle = (Bundle) param.args[2];
            if (bundle != null) {
                for (String key : bundle.keySet()) {
                    Object value = bundle.get(key);
                    log("bundle: "
                            + String.format("{key: %s,\nvalue: %s,\nclass: (%s)}",
                            key, value.toString(), value.getClass().getName()));
                }
            }
        }

        @Override
        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
            super.afterHookedMethod(param);
        }
    };

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        findAndHookMethod("android.app.Activity", lpparam.classLoader,
                "startActivityForResult", Intent.class, int.class, Bundle.class,
                mIntentHook);
    }

    private void log(String s) {
        XposedBridge.log(s);
        File file = new File("/data/data/com.sorcererxw.intentinterceptor/files/test");
        try {
            FileUtils.write(file, s, (String) null, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
