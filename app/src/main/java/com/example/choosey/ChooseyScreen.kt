package com.example.choosey

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlin.random.Random



@Composable
fun ChooseyScreen(navController: NavController, vm: ChooseyViewModel, title: String = "Choosey") {
    var answer by rememberSaveable { mutableStateOf("Help Me Choosey") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Box(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 68.sp,
                lineHeight = 116.sp,
                textAlign = TextAlign.Center
            )
        }

        Box(
            modifier = Modifier
                .weight(2f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            // remove the stray "vm" and actually update the state on click
            MainButton(
                answer = answer,
                onClick = {
                    answer = vm.pickRandom()?.let {"Choosey chose $it"} ?:"No choices selected"
                }
            )
        }

        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
                SelectionButton(onClick = { navController.navigate("Selection") })
            }
            Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
                SelectionButton(onClick = { navController.navigate("Selection") })
            }
        }
    }
}