package com.example.alarmii.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Alarm
import androidx.compose.material.icons.rounded.HourglassTop
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.Timer
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(val route: String, val imageVector: ImageVector, val label: String) {
    data object Alarm : Screens(
        route = NavCons.ALARM,
        label = "Alarm",
        imageVector = Icons.Rounded.Alarm
    )

    data object Stopwatch : Screens(
        route = NavCons.STOPWATCH,
        label = "Stopwatch",
        imageVector = Icons.Rounded.HourglassTop
    )

    data object Timer : Screens(
        route = NavCons.TIMER,
        label = "Timer",
        imageVector = Icons.Rounded.Timer
    )

    data object Settings : Screens(
        route = NavCons.SETTINGS,
        label = "Settings",
        imageVector = Icons.Rounded.Settings
    )
}