package com.example.alarmii

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import java.time.LocalTime

@Composable
fun TimePickerDialog(
    onTimeSet: (LocalTime) -> Unit, // Removed @Composable annotation
    onDismiss: () -> Unit
) {
    val context = LocalContext.current

    DisposableEffect(Unit) {
        val timePicker = android.app.TimePickerDialog(
            context,
            { _, hour, minute ->
                onTimeSet(LocalTime.of(hour, minute))
            },
            LocalTime.now().hour,
            LocalTime.now().minute,
            false
        )

        timePicker.setOnCancelListener { onDismiss() }
        timePicker.show()

        onDispose { timePicker.dismiss() }
    }
}