package com.example.alarmii

import android.annotation.SuppressLint
import android.view.animation.Animation
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animate
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.alarmii.navigation.Screens

@SuppressLint("UnrememberedAnimatable")
@Composable
fun BottomNavBar(
    navController: NavHostController,
) {
    var selectedIndex by remember {
        mutableIntStateOf(0)
    }
    val list = listOf(
        Screens.Alarm,
        Screens.Stopwatch,
        Screens.Timer,
        Screens.Settings,
    )
    NavigationBar() {
        list.forEachIndexed { index, screens ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = {
                    navController.navigate(screens.route)
                    selectedIndex = index
                },
                icon = {
                    Icon(imageVector = screens.imageVector, contentDescription = null)
                },
                label = {
                    Text(text = screens.label)
                },
                alwaysShowLabel = false
            )
        }
    }
}