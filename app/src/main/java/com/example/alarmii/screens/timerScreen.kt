package com.example.alarmii.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@SuppressLint("DefaultLocale")
@Composable
fun TimerScreen() {
    var totalTimeInSeconds by remember { mutableStateOf(60) }
    var remainingTime by remember { mutableStateOf(totalTimeInSeconds) }
    var isRunning by remember { mutableStateOf(false) }

    val minutes = remainingTime / 60
    val seconds = remainingTime % 60

    LaunchedEffect(isRunning) {
        while (isRunning && remainingTime > 0) {
            delay(1000L)
            remainingTime--
        }
        if (remainingTime == 0) {
            isRunning = false
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = String.format("%02d:%02d", minutes, seconds),
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = { isRunning = !isRunning },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = if (isRunning) "Pause" else "Start")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                isRunning = false
                remainingTime = totalTimeInSeconds
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Reset")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = {
                if (totalTimeInSeconds > 10 && !isRunning) {
                    totalTimeInSeconds -= 10
                    remainingTime = totalTimeInSeconds
                }
            }) {
                Text(text = "-10 sec")
            }
            Button(onClick = {
                if (!isRunning) totalTimeInSeconds += 10
                if (!isRunning) remainingTime = totalTimeInSeconds
            }) {
                Text(text = "+10 sec")
            }
        }
    }
}