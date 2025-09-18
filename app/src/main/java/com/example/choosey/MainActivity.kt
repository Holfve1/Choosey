package com.example.choosey

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.choosey.ui.theme.ChooseyTheme

class MainActivity : ComponentActivity() {

    private val vm: ChooseyViewModel by viewModels() // shared ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // your existing function

        setContent {
            ChooseyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNav(vm)
                }
            }
        }
    }
}

@Composable
fun AppNav(vm: ChooseyViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "choosey"
    ) {
        // --- Choosey Screen ---
        composable("choosey") {
            ChooseyScreen(
                navController = navController,
                vm = vm,
                title = "CHOOSEY" // keep fixed title
            )
        }

        // --- Selection Screen ---
        composable("Selection") {
            SelectionScreen(
                navController = navController,
                vm = vm
            )
        }
    }
}

