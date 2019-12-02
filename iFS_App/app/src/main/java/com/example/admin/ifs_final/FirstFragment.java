package com.example.admin.ifs_final;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

public class FirstFragment extends Fragment {
    private WebView mWebView;
    private WebSettings mWebSettings;
    private TextView tv_outPut;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_activity_content, null, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.navIcon);
        final NavigationActivity navigationActivity = (NavigationActivity) getActivity();

        //웹뷰
        mWebView = (WebView)view.findViewById(R.id.webview_login);
        mWebView.setWebViewClient(new WebViewClient());
        mWebSettings = mWebView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);

        mWebView.loadUrl("http://192.168.27.214:8080/ifs/");

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationActivity.drawerLayout.openDrawer(navigationActivity.navView, true);
            }
        });
        return view;


    }
}

