package com.example.composegoogle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.composegoogle.ui.theme.ComposeGoogleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeGoogleTheme {
                RaceTrackerApp()
            }
        }
    }

}


