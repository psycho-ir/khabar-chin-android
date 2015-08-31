package com.example.ssarabadani.khabarchin_prototype.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.ssarabadani.khabarchin_prototype.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WebDetailView extends Fragment {

    private WebView webView;

    public WebDetailView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_web_detail_view, container, false);

        webView = (WebView) view.findViewById(R.id.web_view);

        Bundle bundle = this.getArguments();
        String URL = bundle.getString("url");

        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setLoadWithOverviewMode(true);

        webView.invokeZoomPicker();
        webView.getSettings().getTextZoom();
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setUseWideViewPort(true);

        webView.loadUrl(URL);

        webView.setWebViewClient(new mBrowser());


        return view;
    }

    private class mBrowser extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            view.loadUrl(url);
            return true;
        }
    }


}
