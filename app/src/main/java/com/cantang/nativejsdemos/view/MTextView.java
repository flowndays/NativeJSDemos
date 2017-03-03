package com.cantang.nativejsdemos.view;

import android.support.annotation.NonNull;
import android.widget.TextView;

import com.cantang.nativejsdemos.adapter.JSTextView;
import com.cantang.nativejsdemos.adapter.JSWebViewProvider;

public class MTextView {

    private static final String SET_TEXT_SCRIPT_TEMPLATE = "%1$s.setTextJS('%2$s');";

    private JSTextView mTextView;
    private String jsName;

    public MTextView(@NonNull TextView textView, String jsName, JSWebViewProvider jsWebViewProvider) {
        mTextView = new JSTextView(textView, jsWebViewProvider);
        this.jsName = jsName;
        mTextView.inject(jsName);
    }

    public void setText(@NonNull String data) {
        mTextView.loadData(merge(data));
    }

    private String merge(String data) {
        return JSTemplate.of(String.format(SET_TEXT_SCRIPT_TEMPLATE, jsName, data));
    }

}
