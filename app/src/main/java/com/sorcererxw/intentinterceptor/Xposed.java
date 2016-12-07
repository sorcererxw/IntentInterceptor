package com.sorcererxw.intentinterceptor;

import android.content.Intent;
import android.os.Bundle;

import com.sorcererxw.intentinterceptor.utils.DataUtil;

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
            DataUtil.write(DataUtil.parser(intent, requestCode, bundle,
                    param.thisObject.getClass().getName()));
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
}
