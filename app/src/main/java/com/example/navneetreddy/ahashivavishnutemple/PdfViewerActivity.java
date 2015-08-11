package com.example.navneetreddy.ahashivavishnutemple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class PdfViewerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);
//        com.joanzapata.pdfview.PDFView pdfView = (com.joanzapata.pdfview.PDFView)
//                findViewById(R.id.pdfview);
//
//        pdfView.fromAsset(Singleton.getEventToDisplay().getPdfLink())
//                .defaultPage(1)
//                .showMinimap(false)
//                .enableSwipe(true)
//                .onLoad(onLoadCompleteListener)
//                .onPageChange(onPageChangeListener)
//                .load();

        WebView webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.loadUrl(Singleton.getEventToDisplay().getPdfLink());
    }
}