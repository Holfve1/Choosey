package com.example.choosey

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HelpScreen (navController: NavController){
    Column(){
        Box(){
            Button(onClick = { navController.popBackStack() }) {
                Text("Next", fontSize = 18.sp)
            }
        }
        Box(){
            Text(text = "Choosy Instructions")
        }
        Box(){
            Text(text = "1. Choose a category")
        }
    }
}