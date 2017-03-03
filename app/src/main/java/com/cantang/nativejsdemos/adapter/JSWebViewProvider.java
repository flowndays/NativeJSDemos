package com.cantang.nativejsdemos.adapter;

import android.content.Context;
import android.webkit.WebView;

/**
 * Created by cantang on 3/3/17.
 */

public interface JSWebViewProvider {

    WebView createWebView(Context context);
}
