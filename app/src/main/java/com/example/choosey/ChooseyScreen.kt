package com.example.choosey

import androidx.compose.foundation.background
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch


@Composable
fun ChooseyScreen(
    navController: NavController,
    vm: ChooseyViewModel,
    title: String = "CHOOSEY"
) {
    var answer by rememberSaveable { mutableStateOf("Help Me Choosey") }
    val scope = rememberCoroutineScope()
    val categoryId by vm.currentCategoryId

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF3A123E))
            .padding(20.dp)
            .padding(bottom = 40.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            SpringyBouncingLetters(word = title)
        }

        Box(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            DisplayAnswer(text = answer)
        }

        Box(
            modifier = Modifier
                .weight(2f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            MainButton(
                answer = answer,
                onClick = {
                    scope.launch {
                        val picked = vm.pickRandomLabel(categoryId)
                        answer = picked?.let { "Choosey chose $it" } ?: "No choices selected"
                    }
                }
            )
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            SelectionButton(
                onClick = {
                    navController.navigate("Selection")
                }
            )
        }

        // Optional: show current category
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Current category: ${if (categoryId == 1L) "Takeaway" else "Movie Genre"}",
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}
