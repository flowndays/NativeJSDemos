package com.cantang.nativejsdemos.adapter;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.jakewharton.rxrelay2.PublishRelay;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by cantang on 2/24/17.
 */

public class JSView {
    private static final String TAG = "JSView";
    private View view;
    private final PublishRelay<Boolean> setEnableRelay = PublishRelay.create();
    private final PublishRelay<Integer> setVisibilityRelay = PublishRelay.create();
    protected WebView webView;

    protected JSView(@NonNull View view, @NonNull JSWebViewProvider jsWebViewProvider) {
        this.view = view;
        setEnableRelay
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view::setEnabled);
        setVisibilityRelay
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view::setVisibility);
        webView = jsWebViewProvider.createWebView(view.getContext());
    }

    public void loadData(@NonNull String data) {
        Log.d(TAG, "loading:\n" + data);
        webView.loadData(data, "text/html", "utf-8");
    }

    public void inject(String jsName) {
        webView.addJavascriptInterface(this, jsName);
    }

    @JavascriptInterface
    public boolean isEnabled() {
        return view.isEnabled();
    }

    @JavascriptInterface
    public void setEnabled(boolean enabled) {
        setEnableRelay.accept(enabled);
    }

    @JavascriptInterface
    public int getVisibility() {
        return view.getVisibility();
    }

    @JavascriptInterface
    public void setVisibility(int visibility) {
        setVisibilityRelay.accept(visibility);
    }

}
