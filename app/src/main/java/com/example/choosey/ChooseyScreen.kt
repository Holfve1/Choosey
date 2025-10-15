package com.example.choosey

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
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
    var categoryName by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(categoryId) {
        categoryName = vm.getCategoryName(categoryId)
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF3A123E))
    ) {
        val screenWidth = maxWidth
        val screenHeight = maxHeight
        val isCompact = screenWidth < 360.dp

        // Responsive values
        val horizontalPadding = (screenWidth * 0.08f).coerceAtLeast(16.dp)
        val verticalPadding = (screenHeight * 0.05f).coerceAtLeast(16.dp)
        val verticalSpacing = (screenHeight * 0.02f).coerceAtLeast(12.dp)

        val titleFontSize = if (isCompact) 36 else 60
        val answerFontSize = if (isCompact) 32 else 50
        val buttonFontSize = if (isCompact) 24 else 30
        val buttonSize = if (screenHeight < 600.dp) 200.dp else 280.dp

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = horizontalPadding,
                    end = horizontalPadding,
                    top = verticalPadding,
                    bottom = verticalPadding
                ),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                SpringyBouncingLetters(
                    word = title,
                    fontSize = titleFontSize
                )
            }

            // Category picker
            SelectionButton(
                onClick = { navController.navigate("Selection") },
                categoryName = categoryName
            )

            Spacer(modifier = Modifier.height(verticalSpacing))

            // Answer / prompt
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(vertical = verticalSpacing),
                contentAlignment = Alignment.Center
            ) {
                DisplayAnswer(
                    text = answer,
                    fontSize = answerFontSize
                )
            }

            Spacer(modifier = Modifier.height(verticalSpacing))

            // Main action button
            MainButton(
                answer = answer,
                onClick = {
                    scope.launch {
                        val picked = vm.pickRandomLabel(categoryId)
                        answer = picked ?: "No choices selected"
                    }
                },
                modifier = Modifier.size(buttonSize),
                fontSize = buttonFontSize
            )

            Spacer(modifier = Modifier.height(verticalSpacing))
        }

        // Info button (bottom left)
        InfoButton(
            onClick = { navController.navigate("help") },
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = horizontalPadding, bottom = verticalPadding)
        )
    }
}

