package com.example.choosey

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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
    var answer by rememberSaveable { mutableStateOf("") }
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

        // Optional: show current category
        Column(
            modifier = Modifier
                .background(
                    color = Color(0xFF00AF6D),
                    shape = RoundedCornerShape(12.dp)
                )
                .border(1.dp, Color.Gray, shape = RoundedCornerShape(12.dp))
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Current category:",
                fontSize = 18.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${if (categoryId == 1L) "Takeaway" else "Movie Genre"}",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            val fallback = answer.isBlank()

            DisplayAnswer(
                text = if (fallback) "What will Choosey chose for you?" else answer,
                isFallback = fallback
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
                        answer = picked ?: "No choices selected"
                    }
                }
            )
        }



        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(

                text = "Current category: ${if (categoryId == 1L) "YES / NO" 
                else if (categoryId == 2L) "Movie Genre" 
                else if (categoryId == 3L) "Date Night"
                else "Takeaway"}",    // this is for adding a category name (not scalable - ask coach)
                fontSize = 20.sp,

                color = Color.White
            )
        }
    }
}
