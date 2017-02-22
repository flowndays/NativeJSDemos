package com.cantang.nativejsdemos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.EditText;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;

public class WebViewActivity extends AppCompatActivity {

    private EditText editText;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_solution);

        editText = (EditText) findViewById(R.id.card_number);

        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);

        initJSClient((ViewGroup) toolBar.getParent());
    }

    private void initJSClient(ViewGroup parent) {
        WebView webView = new WebView(this);
        webView.setVisibility(View.GONE);

        webView.getSettings().setJavaScriptEnabled(true);

        WebViewJavaScriptInterface jsInterface = new WebViewJavaScriptInterface();
        webView.addJavascriptInterface(jsInterface, "app");
        disposable = jsInterface.editTextFlowable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    editText.setText(s);
                });
        parent.addView(webView);

        webView.loadUrl("file:///android_asset/web.html");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }

    public class WebViewJavaScriptInterface {

        private Flowable<String> editTextFlowable;

        private BehaviorSubject<String> behaviorSubject = BehaviorSubject.create();

        public WebViewJavaScriptInterface() {

            editTextFlowable = behaviorSubject.toFlowable(BackpressureStrategy.DROP);
        }

        @JavascriptInterface
        public void setEditText(String text) {
            behaviorSubject.onNext(text);
        }
    }
}
