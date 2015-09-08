package com.navneetreddy.ahashivavishnutemple;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


/**
 * Splash screen shown when the app is launched.
 *
 * @author Navneet Reddy
 */
public class SplashScreenActivity extends Activity implements Runnable {

    Thread mThread;

    /**
     * Creates a new Splash Screen Activity.
     *
     * @param savedInstanceState Any saved data from the activity if the activity is re-initializing.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        OutlineTextView title = (OutlineTextView) findViewById(R.id.splashTitle);
        title.setText("AHA\nShiva Vishnu Temple");

        mThread = new Thread(this);
        mThread.start();
    }

    /**
     * Starts the Main Activity after 3000 milliseconds.
     */
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