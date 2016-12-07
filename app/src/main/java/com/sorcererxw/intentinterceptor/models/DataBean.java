package com.sorcererxw.intentinterceptor.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @description:
 * @author: Sorcerer
 * @date: 2016/12/5
 */

public class DataBean {

    /**
     * action : android.intent.action.MAIN
     * clipData : null
     * flags : 270532608
     * dataString : null
     * type : null
     * componentName : ComponentInfo{com.sorcererxw.intentinterceptor/com.sorcererxw.intentinterceptor.MainActivity}
     * scheme : null
     * package : null
     * categories : ["android.intent.category.LAUNCHER"]
     * intentExtras : [{"key":"profile","value":"0","class":"java.lang.Long"}]
     * bundle : [{"key":"android:activity.animHeight","value":"196","class":"java.lang.Integer"},{"key":"android:activity.animStartX","value":"180","class":"java.lang.Integer"},{"key":"android:activity.animStartY","value":"245","class":"java.lang.Integer"},{"key":"android:activity.animType","value":"11","class":"java.lang.Integer"},{"key":"android:activity.animWidth","value":"180","class":"java.lang.Integer"}]
     * requestCode : -1
     */

    @SerializedName("time")
    private String time;
    @SerializedName("from")
    private String from;
    @SerializedName("to")
    private String to;
    @SerializedName("action")
    private String action;
    @SerializedName("clipData")
    private String clipData;
    @SerializedName("flags")
    private int flags;
    @SerializedName("dataString")
    private String dataString;
    @SerializedName("type")
    private String type;
    @SerializedName("componentName")
    private String componentName;
    @SerializedName("scheme")
    private String scheme;
    @SerializedName("package")
    private String packageX;
    @SerializedName("requestCode")
    private int requestCode;
    @SerializedName("categories")
    private List<String> categories;
    @SerializedName("intentExtras")
    private List<IntentExtrasBean> intentExtras;
    @SerializedName("bundle")
    private List<BundleBean> bundle;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getClipData() {
        return clipData;
    }

    public void setClipData(String clipData) {
        this.clipData = clipData;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public String getDataString() {
        return dataString;
    }

    public void setDataString(String dataString) {
        this.dataString = dataString;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<IntentExtrasBean> getIntentExtras() {
        return intentExtras;
    }

    public void setIntentExtras(List<IntentExtrasBean> intentExtras) {
        this.intentExtras = intentExtras;
    }

    public List<BundleBean> getBundle() {
        return bundle;
    }

    public void setBundle(List<BundleBean> bundle) {
        this.bundle = bundle;
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "\naction='" + action + '\'' +
                "\n, clipData='" + clipData + '\'' +
                "\n, flags=" + flags +
                "\n, dataString='" + dataString + '\'' +
                "\n, type='" + type + '\'' +
                "\n, componentName='" + componentName + '\'' +
                "\n, scheme='" + scheme + '\'' +
                "\n, packageX='" + packageX + '\'' +
                "\n, requestCode=" + requestCode +
                "\n, categories=" + categories +
                "\n, intentExtras=" + intentExtras +
                "\n, bundle=" + bundle +
                "\n}";
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public static class IntentExtrasBean {
        /**
         * key : profile
         * value : 0
         * class : java.lang.Long
         */

        @SerializedName("key")
        private String key;
        @SerializedName("value")
        private String value;
        @SerializedName("class")
        private String classX;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getClassX() {
            return classX;
        }

        public void setClassX(String classX) {
            this.classX = classX;
        }

        @Override
        public String toString() {
            return "IntentExtrasBean{" +
                    "\nkey='" + key + '\'' +
                    "\n, value='" + value + '\'' +
                    "\n, classX='" + classX + '\'' +
                    "\n}";
        }
    }

    public static class BundleBean {
        /**
         * key : android:activity.animHeight
         * value : 196
         * class : java.lang.Integer
         */

        @SerializedName("key")
        private String key;
        @SerializedName("value")
        private String value;
        @SerializedName("class")
        private String classX;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getClassX() {
            return classX;
        }

        public void setClassX(String classX) {
            this.classX = classX;
        }

        @Override
        public String toString() {
            return "BundleBean{" +
                    "\nkey='" + key + '\'' +
                    "\n, value='" + value + '\'' +
                    "\n, classX='" + classX + '\'' +
                    "\n}";
        }
    }
}
