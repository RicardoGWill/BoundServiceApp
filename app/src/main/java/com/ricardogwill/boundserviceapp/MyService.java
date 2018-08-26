package com.ricardogwill.boundserviceapp;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

// This is how to create a bound services if the service is private to your app.
// This is known as "extending the 'Binder' class".
public class MyService extends Service {

    public MyService() {
    }

    // LocalBinder extends the Binder class.
    public class LocalBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }

    public final IBinder iBinder = new LocalBinder();
    // This creates a random-number-generator object.
    private final Random randomNumberGenerator = new Random();

    // This generates a random number from 0 to 99.
    public int getRandom() {
        return randomNumberGenerator.nextInt(100);
    }
    // This method outline is created automatically with a Service class.
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }
}
