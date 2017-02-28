package com.example.administrator.ad20170227appandweb;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1002;
    private Button mButton;
    private WebView mWebView;
    private String phoneNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    @SuppressLint("JavascriptInterface")
    private void initViews() {
        mButton = (Button) findViewById(R.id.call_web);
        mWebView = (WebView) findViewById(R.id.myweb);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(MainActivity.this, "android");
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.loadUrl("file:///android_asset/web.html");
            }
        });
    }

    @JavascriptInterface
    public void callphone(String num) {
        Toast.makeText(MainActivity.this, "AAAAA", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+num));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CALL_PHONE},REQUEST_CODE);
        }
        startActivity(intent);
    }
   @JavascriptInterface
    public void sendMsg(String msg){
        Toast.makeText(MainActivity.this, "AAAAA", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Intent.ACTION_SENDTO,Uri.parse("smsto:123456789"));
       intent.putExtra("sms_body",msg);
        startActivity(intent);
    }

}
