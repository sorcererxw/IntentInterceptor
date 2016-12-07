package com.sorcererxw.intentinterceptor.utils;

import android.text.Html;
import android.text.Spanned;

/**
 * @description:
 * @author: Sorcerer
 * @date: 2016/12/6
 */

public class HtmlBuilder {
    private StringBuilder mStringBuilder;

    public HtmlBuilder() {
        mStringBuilder = new StringBuilder();
    }

    public HtmlBuilder appendBold(String bold) {
        mStringBuilder.append(String.format("<b>%s</b>", bold));
        return this;
    }

    public HtmlBuilder appendItalic(String italic) {
        mStringBuilder.append(String.format("<i>%s</i>", italic));
        return this;
    }

    public HtmlBuilder appendTab(int times) {
        for (int i = 0; i < times * 2; i++) {
            mStringBuilder.append("&nbsp;");
        }
        return this;
    }

    public HtmlBuilder appendText(String text) {
        mStringBuilder.append(text);
        return this;
    }

    public HtmlBuilder newLine() {
        return newLine(1);
    }

    public HtmlBuilder newLine(int lines) {
        for (int i = 0; i < lines; i++) {
            mStringBuilder.append("<br/>");
        }
        return this;
    }

    public HtmlBuilder blockquote(HtmlBuilder htmlBuilder) {
        mStringBuilder.append(String.format("<blockquote>%s</blockquote>", htmlBuilder.getRaw()));
        return this;
    }

    public Spanned build() {
        return Html.fromHtml(mStringBuilder.toString());
    }

    public String getRaw() {
        return mStringBuilder.toString();
    }
}
