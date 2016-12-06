package com.sorcererxw.intentinterceptor.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;

/**
 * @description:
 * @author: Sorcerer
 * @date: 2016/12/5
 */

public class MyData {

    private static final String DATA_PATH =
            "/data/data/com.sorcererxw.intentinterceptor/files/intent_data";

    public static void createFile(Context context) {
        File file = new File(DATA_PATH);
        if (!file.exists()) {
            try {
                FileOutputStream stream =
                        context.openFileOutput("intent_data", Context.MODE_WORLD_WRITEABLE);
                stream.write(" ".getBytes());
                stream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void clear() throws IOException {
        FileUtils.write(new File(DATA_PATH), "", "GBK", false);
    }

    public static void write(String s) throws IOException {
        FileUtils.write(new File(DATA_PATH), s + "\n", "GBK", true);
    }

    public static String parser(Intent intent, int requestCode, Bundle bundle) {
        StringBuilder builder = new StringBuilder();

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        String dateStrTmp = dateFormat.format(date);

        builder.append("{")
                .append(String.format("\"time\": \"%s\",", dateStrTmp))
                .append(String.format("\"action\": \"%s\",", intent.getAction()))
                .append(String.format("\"clipData\": \"%s\",", intent.getClipData()))
                .append(String.format((Locale) null, "\"flags\": %d,", intent.getFlags()))
                .append(String.format("\"dataString\": \"%s\",", intent.getDataString()))
                .append(String.format("\"type\": \"%s\",", intent.getType()))
                .append(String.format("\"componentName\": \"%s\",", intent.getComponent()))
                .append(String.format("\"scheme\": \"%s\",", intent.getScheme()))
                .append(String.format("\"package\": \"%s\",", intent.getPackage()));

        Set<String> categories = intent.getCategories();
        if (categories != null) {
            builder.append("\"categories\" :[");
            boolean added = false;
            for (String s : categories) {
                if (added) {
                    builder.append(",");
                }
                builder.append(String.format("\"%s\"", s));
                added = true;
            }
            builder.append("],");
        }

        Bundle intentExtras = intent.getExtras();
        if (intentExtras != null) {
            builder.append("\"intentExtras\": [");
            boolean added = false;
            for (String key : intentExtras.keySet()) {
                Object value = intentExtras.get(key);
                if (added) {
                    builder.append(",");
                }
                builder.append(String.format("{\"key\": \"%s\",", key))
                        .append(String.format("\"value\": \"%s\",", value))
                        .append(String.format("\"class\": \"%s\"}", value.getClass().getName()));
                added = true;
            }
            builder.append("],");
        }
        if (bundle != null) {
            builder.append("\"bundle\": [");
            boolean added = false;
            for (String key : bundle.keySet()) {
                Object value = bundle.get(key);
                if (added) {
                    builder.append(",");
                }
                builder.append(String.format("{\"key\": \"%s\",", key))
                        .append(String.format("\"value\": \"%s\",", value))
                        .append(String
                                .format("\"class\": \"%s\"}", value.getClass().getName()));
                added = true;
            }
            builder.append("],");
        }
        builder.append(String.format((Locale) null, "\"requestCode\": %d", requestCode));
        builder.append("},");
        return builder.toString();
    }
}
