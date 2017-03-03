package com.cantang.nativejsdemos.adapter;

import android.support.annotation.NonNull;
import android.webkit.JavascriptInterface;
import android.widget.TextView;

import com.jakewharton.rxrelay2.PublishRelay;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class JSTextView extends JSView {
    private final PublishRelay<String> setTextRelay = PublishRelay.create();
    private final PublishRelay<String> setHintRelay = PublishRelay.create();

    public JSTextView(@NonNull TextView textView, @NonNull JSWebViewProvider jsWebViewProvider) {
        super(textView, jsWebViewProvider);
        setTextRelay
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(textView::setText);
        setHintRelay
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(textView::setHint);
    }

    @JavascriptInterface
    public void setTextJS(String text) {
        setTextRelay.accept(text);
    }

    @JavascriptInterface
    public void setHintJS(String hint) {
        setHintRelay.accept(hint);
    }

    public void inject(String jsName) {
        webView.addJavascriptInterface(this, jsName);
    }
}
