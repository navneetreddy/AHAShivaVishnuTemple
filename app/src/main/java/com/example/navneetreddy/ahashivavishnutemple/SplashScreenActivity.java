package com.example.navneetreddy.ahashivavishnutemple;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public class SplashScreenActivity extends Activity implements Runnable {

        Thread mThread;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash_screen);

            OutlineTextView title = (OutlineTextView) findViewById(R.id.splashTitle);
            title.setText("AHA\nShiva Vishnu Temple");

            mThread = new Thread(this);
            mThread.start();
        }

        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                finish();
            }
        }
}