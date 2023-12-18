package com.emerald.eyecare.service

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.util.Log
import com.emerald.eyecare.utils.Constant
import com.emerald.eyecare.utils.Constant.TAG

object AlarmService {
    @SuppressLint("ScheduleExactAlarm")
    fun setRepeatingAlarm(context: Context) {
        Log.i(TAG, "setRepeatingAlarm: ")
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)

        val interval = 1 * 60 * 1000L // 20 minutes

        val triggerAtMillis = System.currentTimeMillis() + interval

        // For API 19 and above, use setExact
        alarmManager.setExact(
            AlarmManager.RTC_WAKEUP,
            triggerAtMillis,
            pendingIntent
        )
    }

    fun checkPermission(context: Context) {
        Log.i(TAG, "checkPermission: ")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            if (!alarmManager.canScheduleExactAlarms()) {
                Log.i(TAG, "checkPermission: startActivity")
                val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
                context.startActivity(intent)
            } else {
                Log.i(TAG, "checkPermission: cannot")
                // Schedule the alarm as you have permission
                setRepeatingAlarm(context)
            }
        } else {
            Log.i(TAG, "checkPermission: old version")
            // For older versions, just schedule the alarm
            setRepeatingAlarm(context)
        }

    }
}