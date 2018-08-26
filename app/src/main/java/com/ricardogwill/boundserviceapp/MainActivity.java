package com.ricardogwill.boundserviceapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    MyService myService;
    boolean isBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent serviceIntent = new Intent(this, MyService.class);
        startService(serviceIntent);
        bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    public void getRandomNumber(View view) {
        TextView resultTextView = findViewById(R.id.result_textView);
        resultTextView.setText(Integer.toString(myService.getRandom()));
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        // This method outline is automatically generated when a "new ServiceConnection()" is made.
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.LocalBinder binder = (MyService.LocalBinder) service;
            myService = binder.getService();
            isBound = true;
        }
        // This method outline is automatically generated when a "new ServiceConnection()" is made.
        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

}
