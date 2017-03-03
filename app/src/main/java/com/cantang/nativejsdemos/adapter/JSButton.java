package com.cantang.nativejsdemos.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.Button;

import com.jakewharton.rxrelay2.PublishRelay;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by cantang on 2/24/17.
 */

public class JSButton extends JSView {
    private final PublishRelay<String> setTextRelay = PublishRelay.create();

    public JSButton(@NonNull Button button,
                    @NonNull JSWebViewProvider jsWebViewProvider) {
        super(button, jsWebViewProvider);
        setTextRelay
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(button::setText);
    }

    @JavascriptInterface
    void setButtonTextJS(String text) {
        setTextRelay.accept(text);
    }
}
