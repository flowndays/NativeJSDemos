package com.cantang.nativejsdemos.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.webkit.JavascriptInterface;

/**
 * Created by cantang on 2/24/17.
 */

public class JSButton extends JSView {
    public JSButton(@NonNull View view,
                       @NonNull String jsName,
                       @NonNull JSWebViewProvider jsWebViewProvider) {
        super(view, jsWebViewProvider);
    }

    @JavascriptInterface
    void setText(String text) {

    }
}
