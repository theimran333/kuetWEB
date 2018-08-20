package me.theimran333.app.kuetweb;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;


public class MainActivity extends AppCompatActivity {



    private WebView webView;
    private AlertDialog.Builder alertDialog;
    FloatingActionMenu floatingActionMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionMenu = (FloatingActionMenu) findViewById(R.id.menuBAR);


        webView = (WebView) findViewById(R.id.webViewId);

        webView.getSettings().setJavaScriptEnabled(true);

        final Activity activity = this;
        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {

                activity.setProgress(progress * 1000);
            }
        });

        webView.setWebViewClient(new WebViewClient() {

            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {

                Toast.makeText(activity, "Oh no! " + description,
                        Toast.LENGTH_SHORT).show();
            }
        });
        webView.loadUrl("http://kuet.ac.bd/");


    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }
        else
        {
            alertDialog = new AlertDialog.Builder(MainActivity.this);
            alertDialog.setTitle(R.string.app_name);
            alertDialog.setMessage(R.string.alertexit);
            alertDialog.setIcon(R.drawable.ic_warning_black_24dp);
            alertDialog.setCancelable(false);
            alertDialog.setPositiveButton(R.string.alertnepositive, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();

                }
            });
            alertDialog.setNegativeButton(R.string.alertnegative, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog1 = alertDialog.create();
            alertDialog1.show();


        }
    }

    public void setAbout(View view) {
        Intent i = new Intent(MainActivity.this,AboutActivity.class);
        startActivity(i);
        floatingActionMenu.close(true);

    }

    public void setUp(View view) {

        webView.pageUp(true);
        floatingActionMenu.close(true);
    }
}


