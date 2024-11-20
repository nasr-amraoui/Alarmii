package com.example.alarmii.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.alarmii.screens.AlarmScreen
import com.example.alarmii.screens.SettingsScreen
import com.example.alarmii.screens.StopwatchScreen
import com.example.alarmii.screens.TimerScreen

@Composable
fun AppNavHost(
    navHostController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination,
        builder = {
            composable(NavCons.ALARM) {
                AlarmScreen()
            }
            composable(NavCons.STOPWATCH) {
                StopwatchScreen()
            }
            composable(NavCons.TIMER) {
                TimerScreen()
            }
            composable(NavCons.SETTINGS) {
                SettingsScreen()
            }
        }
    )
}