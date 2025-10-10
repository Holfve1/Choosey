package com.example.choosey

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun ChooseyScreen(
    navController: NavController,
    vm: ChooseyViewModel,
    title: String = "CHOOSEY"
) {
    var answer by rememberSaveable { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val categoryId by vm.currentCategoryId

    var categoryName by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(categoryId) {
        categoryName = vm.getCategoryName(categoryId)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF3A123E))
    ) {
        // Main content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 35.dp, end = 35.dp, top = 20.dp, bottom = 80.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                SpringyBouncingLetters(word = title)
            }

            // Category picker
            SelectionButton(
                onClick = { navController.navigate("Selection") },
                categoryName = categoryName
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Answer / prompt
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 16.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                DisplayAnswer(
                    text = answer,
                )
            }

            // Spacer to push button lower nicely
            Spacer(modifier = Modifier.height(20.dp))

            // Main action

            MainButton(
                answer = answer,
                onClick = {
                    scope.launch {
                        val picked = vm.pickRandomLabel(categoryId)
                        answer = picked ?: "No choices selected"
                    }
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
        }

        InfoButton(
            onClick = { navController.navigate("help") },
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 16.dp, bottom = 50.dp) // spacing from screen edges
        )
    }
}