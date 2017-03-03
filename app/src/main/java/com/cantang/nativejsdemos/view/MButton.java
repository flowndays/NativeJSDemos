package com.cantang.nativejsdemos.view;

import android.support.annotation.NonNull;
import android.widget.Button;

import com.cantang.nativejsdemos.adapter.JSButton;
import com.cantang.nativejsdemos.adapter.JSWebViewProvider;

public class MButton {

    private static final String SET_TEXT_SCRIPT_TEMPLATE = "%1$s.setButtonTextJS('%2$s');";

    private JSButton mButton;
    private String jsName;

    public MButton(@NonNull Button button, String jsName, JSWebViewProvider jsWebViewProvider) {
        mButton = new JSButton(button, jsWebViewProvider);
        this.jsName = jsName;
        mButton.inject(jsName);
    }

    public void setText(@NonNull String data) {
        mButton.loadData(merge(data));
    }

    private String merge(String data) {
        return JSTemplate.of(String.format(SET_TEXT_SCRIPT_TEMPLATE, jsName, data));
    }

}
