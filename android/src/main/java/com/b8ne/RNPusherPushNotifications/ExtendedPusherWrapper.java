package com.b8ne.RNPusherPushNotifications;

import android.util.Log;

import android.app.Activity;

import java.util.Set;

import com.facebook.react.bridge.*;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.google.firebase.messaging.RemoteMessage;

import com.pusher.pushnotifications.PushNotifications;
import com.pusher.pushnotifications.SubscriptionsChangedListener;
import com.pusher.pushnotifications.PushNotificationReceivedListener;

public class ExtendedPusherWrapper extends PusherWrapper {
    public ExtendedPusherWrapper(String appKey, final ReactContext context) {
        super(appKey, context);
    }
    
    public void setUserId(final String userId, final ReadableMap tokenProvider, final Callback errorCallback,  final Callback successCallback) {
        Log.d("PUSHER_WRAPPER", "Setting userId to " +  userId);
        System.out.print("Setting userId to " +  userId);
        try {
            String token = "";
            if (tokenProvider.hasKey("token") && tokenProvider.getType("token") == ReadableType.String) {
              token = tokenProvider.getString("token");
            }
  
            LocalTokenProvider instance = new LocalTokenProvider(token);
  
            PushNotifications.setUserId(userId, instance);
            Log.d("PUSHER_WRAPPER", "Success! " + userId);
            System.out.print("Success! " + userId);
            successCallback.invoke();
        } catch (Exception ex) {
            Log.d("PUSHER_WRAPPER", "Exception in PusherWrapper " + ex.getMessage());
            System.out.print("Exception in PusherWrapper.setUserId " + ex.getMessage());
            errorCallback.invoke(0, ex.getMessage());
        }
    }
}
