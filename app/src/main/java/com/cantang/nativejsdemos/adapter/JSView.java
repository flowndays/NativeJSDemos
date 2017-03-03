package com.cantang.nativejsdemos.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.jakewharton.rxrelay2.PublishRelay;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by cantang on 2/24/17.
 */

public class JSView {
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

    public void loadUrl(@NonNull String url) {
        webView.loadUrl(url);
    }

    public void loadData(@NonNull String data) {
        webView.loadData(data, "text/html", "utf-8");
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
