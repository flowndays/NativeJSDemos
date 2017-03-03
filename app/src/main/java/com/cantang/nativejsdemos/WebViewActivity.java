package com.cantang.nativejsdemos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.cantang.nativejsdemos.adapter.JSWebViewProvider;
import com.cantang.nativejsdemos.view.MButton;
import com.cantang.nativejsdemos.view.MTextView;

public class WebViewActivity extends AppCompatActivity {

    private MTextView cardNumber;
    private MButton button;
    private JSWebViewProvider jsWebViewProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_solution);

        jsWebViewProvider = context -> {
            WebView webView = new WebView(context);
            webView.setVisibility(View.GONE);

            webView.getSettings().setJavaScriptEnabled(true);
            return webView;
        };

        cardNumber = new MTextView((TextView) findViewById(R.id.card_number), "cardNumber", jsWebViewProvider);
        button = new MButton((Button) findViewById(R.id.button), "button", jsWebViewProvider);

        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        cardNumber.setText("Hello world from java through js!");
        button.setText("Button Text from JS");
    }

}
