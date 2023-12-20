package com.example.paranoid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;


public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("LowBatteryReceiver", "Received broadcast: " + intent.getAction());
        // Battery is low, show a toast
        Toast.makeText(context, "Battery is low!", Toast.LENGTH_LONG).show();
    }
}