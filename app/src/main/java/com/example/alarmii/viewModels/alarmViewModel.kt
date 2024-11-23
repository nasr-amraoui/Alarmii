package com.example.alarmii.viewModels

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.example.alarmii.broadcastReseiver.AlarmReceiver
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalTime
import java.util.Calendar

class AlarmViewModel() : ViewModel(){
    private val _alarms = MutableStateFlow<List<LocalTime>>(emptyList())
    val alarms = _alarms.asStateFlow()

    fun addAlarm(time: LocalTime, context: Context) {
        _alarms.value += time
        scheduleAlarm(time, context)
    }

    fun removeAlarm(time: LocalTime) {
        _alarms.value -= time
    }

    @SuppressLint("ScheduleExactAlarm")
    private fun scheduleAlarm(time: LocalTime, context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        // Create a Calendar instance for the alarm time
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, time.hour)
            set(Calendar.MINUTE, time.minute)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)

            // If the time is in the past for today, schedule it for the next day
            if (before(Calendar.getInstance())) {
                add(Calendar.DAY_OF_YEAR, 1)
            }
        }

        // Create a PendingIntent to trigger the AlarmReceiver
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra("ALARM_TIME", time.toString())
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            time.hashCode(), // Unique request code
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Schedule the alarm
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent
        )
    }
}