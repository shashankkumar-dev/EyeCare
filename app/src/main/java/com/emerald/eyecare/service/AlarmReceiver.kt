package com.emerald.eyecare.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import com.emerald.eyecare.R
import com.emerald.eyecare.utils.Constant.TAG

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // Trigger the notification here
        Log.i(TAG, "onReceive: ")
        showNotification(context)
    }

    private fun showNotification(context: Context) {
        Log.i(TAG, "showNotification: ")
        val notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        // Create a notification channel for API 26+
        val channel = NotificationChannel("channel_id", "Channel Name", NotificationManager.IMPORTANCE_DEFAULT)
        notificationManager.createNotificationChannel(channel)

        // Build the notification
        val notification = NotificationCompat.Builder(context, "channel_id")
            .setContentTitle("Alarm")
            .setContentText("This is an alarm notification.")
            .setSmallIcon(R.drawable.ic_notifications_black_24dp) // Set a small icon
            .build()

        // Notify
        notificationManager.notify(1, notification)
    }
}
