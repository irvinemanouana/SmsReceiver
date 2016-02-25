package com.dev.christopher.tpsecumobile;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by Christopher on 25/02/2016.
 */
public class ServiceSms extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
