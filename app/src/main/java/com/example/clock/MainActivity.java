package com.example.clock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    TextView time, date, zone;
    SimpleDateFormat df;
    Calendar c = Calendar.getInstance();
    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        date = findViewById(R.id.date);
        time = findViewById(R.id.clock);
        zone = findViewById(R.id.zone);

        webview = findViewById(R.id.webview);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl("https://www.timeanddate.com/on-this-day/");

        df = new SimpleDateFormat("zzzz");
        zone.setText(df.format(c.getTime()));

        df = new SimpleDateFormat("MMM d, YYYY G");


        date.setText(df.format(c.getTime()));

        df = new SimpleDateFormat("hh:mm:ss a");
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                c = Calendar.getInstance();
                String timeNow = df.format(c.getTime());
                time.setText(timeNow);
            }
        }, 0, 100);

    }

    public void switchto24(View view) {

        Button b = (Button) findViewById(R.id.switch_button);

        if (b.getText().equals(getString(R.string.switch12))) {

            df = new SimpleDateFormat("hh:mm:ss a");

            b.setText(getString(R.string.switch24));


        } else {
            df = new SimpleDateFormat("HH:mm:ss");

            b.setText(getString(R.string.switch12));
        }
    }


}
