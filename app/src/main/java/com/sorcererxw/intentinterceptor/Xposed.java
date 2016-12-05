package com.sorcererxw.intentinterceptor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import com.sorcererxw.intentinterceptor.utils.MyData;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
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
            Intent intent = (Intent) param.args[0];
            int requestCode = (int) param.args[1];
            Bundle bundle = (Bundle) param.args[2];
            log(MyData.parser(intent, requestCode, bundle));
        }

        @Override
        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
            super.afterHookedMethod(param);
        }
    };

    private Context mContext;

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
//        findAndHookMethod("com.sorcererxw.intentinterceptor.services.XposedPartnerService",
//                lpparam.classLoader,
//                "getItself", new XC_MethodHook() {
//                    @Override
//                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                        super.afterHookedMethod(param);
//                        Object o = param.getResult();
//                        if (o != null) {
//                            mContext = (Context) o;
//                            XposedBridge.log("拿到Context啦");
//                        }
//                    }
//                });

        findAndHookMethod("android.app.Activity", lpparam.classLoader,
                "startActivityForResult", Intent.class, int.class, Bundle.class,
                mIntentHook);
    }

    private void log(String s) throws IOException {
        MyData.write(s);
    }
}
