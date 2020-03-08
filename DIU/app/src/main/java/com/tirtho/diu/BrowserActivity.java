package com.tirtho.diu;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tirtho.diu.com.tirtho.others.MyWebViewClient;

import java.security.PrivateKey;

public class BrowserActivity extends AppCompatActivity {

    private EditText searchBar;
    private Button btnSearch;
    ProgressBar progressBar;
    WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        btnSearch = ((Button) findViewById(R.id.btnSearch));
        searchBar = (EditText) findViewById(R.id.etAddress);
        webView = (WebView) findViewById(R.id.web);
        progressBar= (ProgressBar) findViewById(R.id.progress);
        webView.setWebChromeClient(new MyWebVierClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setBuiltInZoomControls(true);

        webView.setWebViewClient(new MyWebViewClient());

        String search = "https://diu.ac";

        if (isOnline()) {
            try {
                webView.loadUrl(search);
                BrowserActivity.this.progressBar.setProgress(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getApplicationContext(), "You are offline.\nCheck Internet connection", Toast.LENGTH_SHORT).show();
        }
        searchBar.setText(search);
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    btnSearch.setVisibility(View.INVISIBLE);
                } else {
                    btnSearch.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



//

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri="https://"+searchBar.getText().toString();
                InputMethodManager inm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inm.hideSoftInputFromWindow(searchBar.getWindowToken(), 0);

                if (isOnline()) {
                    try {
                        webView.loadUrl(uri);
                        BrowserActivity.this.progressBar.setProgress(0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "    You are offline.\nCheck Internet connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public boolean validateUrl(String url){
        return true;
    }
    private class MyWebVierClient extends WebChromeClient{
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            BrowserActivity.this.progressBar.setProgress(newProgress);
            super.onProgressChanged(view,newProgress);
        }
    }
    public void setValue(int progress){
        this.progressBar.setProgress(progress);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_browser, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public boolean isOnline() {
        ConnectivityManager cm =(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
            BrowserActivity.this.progressBar.setProgress(0);
        } else {
            super.onBackPressed();
        }
    }
}
