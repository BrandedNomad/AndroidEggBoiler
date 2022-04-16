package com.example.android.eggtimernotifications

import android.app.NotificationManager
import android.content.ContentValues.TAG
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.android.eggtimernotifications.util.sendNotification
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService: FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)
        Log.e("onReceived", "from: ${remoteMessage?.from}")

        remoteMessage?.data?.let {
            Log.e(TAG, "Message data payload: " + remoteMessage.data)
        }

        remoteMessage?.notification?.let{
            val notificationManager = ContextCompat.getSystemService(applicationContext,
                NotificationManager::class.java) as NotificationManager
            notificationManager.sendNotification(it.body!!,applicationContext)
        }
    }

    override fun onNewToken(token: String?){
        Log.e("NewToken","Refreshed token: $token")
        //sendRegistrationToServer(token)
    }


}