package com.cantang.nativejsdemos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.squareup.duktape.Duktape;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.test_webview_solution).setOnClickListener(
                view -> startActivity(new Intent(MainActivity.this, WebViewActivity.class)));
        findViewById(R.id.test_duktape).setOnClickListener(view -> testDuktape());
        findViewById(R.id.test_v8).setOnClickListener(view -> testV8());
        findViewById(R.id.test_rhino).setOnClickListener(view -> testRhino());
    }

    private void testRhino() {

    }

    private void testV8() {

    }

    private CountryPicker countryPicker = countryISO ->
            Toast.makeText(getApplicationContext(), "selected country:" + countryISO, Toast.LENGTH_LONG).show();

    private void testDuktape() {
        Duktape duktape = Duktape.create();
        duktape.set("CountryPicker", CountryPicker.class, countryPicker);

        try {
            duktape.evaluate("CountryPicker.setCountryISO('US');");
        } finally {
            duktape.close();
        }
    }

}
