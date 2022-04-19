package com.example.freefacebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowInsets;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    HashMap<Integer, String> navigationUrls;

    BottomNavigationView navigationView;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = this.findViewById(R.id.navBar);
        webView = findViewById(R.id.webView);

        navigationUrls = new HashMap<>();

        navigationUrls.put(R.id.home,"https://free.facebook.com/home.php");
        navigationUrls.put(R.id.profile,"https://free.facebook.com/profile");
        navigationUrls.put(R.id.messages,"https://free.facebook.com/messages");
        navigationUrls.put(R.id.notifications,"https://free.facebook.com/notifications.php");
        navigationUrls.put(R.id.friends,"https://free.facebook.com/friends");
////        navigationUrls.put(R.id.groups,"https://free.facebook.com/groups");

        loadPage(R.id.home);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                return loadPage(id);
            }
        });

    }
    private Boolean loadPage(int id) {
        String url = navigationUrls.get(id);
        try {
            WebViewClient webViewClient = new WebViewClient();
            webView.setWebViewClient(webViewClient); // to prevent opening the page out of the webview
            webView.loadUrl(url);
            webView.getSettings().setSupportZoom(true);
            webView.getSettings().setDisplayZoomControls(true);
            return true;
        } catch (Exception e) {
            String msg = getResources().getString(R.string.errorMessage);
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            return  false;
        }
    }
}