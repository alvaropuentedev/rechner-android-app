package dev.alvaropuente.rechnerandroidapp

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import dev.alvaropuente.rechnerandroidapp.ui.screen.CalculatorScreen
import dev.alvaropuente.rechnerandroidapp.ui.theme.RechnerAndroidAppTheme
import dev.alvaropuente.rechnerandroidapp.viewmodel.CalculatorViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the orientation to portrait
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        val calculatorViewModel = ViewModelProvider(this).get(CalculatorViewModel::class.java)
        enableEdgeToEdge()
        setContent {
            RechnerAndroidAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalculatorScreen(modifier = Modifier.padding(innerPadding), calculatorViewModel)
                }
            }
        }
    }
}