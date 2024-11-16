package com.deepdark.lab5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.deepdark.lab5.components.TopAppBar
import com.deepdark.lab5.pages.InterruptionLossCalculatorPage
import com.deepdark.lab5.pages.ReliabilityComparisonPage
import com.deepdark.lab5.ui.theme.Lab5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab5Theme {
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { TopAppBar(navController) }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "ReliabilityComparison",
                        Modifier.padding(innerPadding)
                    ) {
                        composable("ReliabilityComparison") { ReliabilityComparisonPage() }
                        composable("InterruptionLossCalculator") { InterruptionLossCalculatorPage() }
                    }
                }
            }
        }
    }
}
