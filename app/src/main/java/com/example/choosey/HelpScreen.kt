package com.example.choosey

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HelpScreen(
    navController: NavController,
    title: String = "CHOOSEY"
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF3A123E))
            .padding(start = 35.dp, end = 35.dp, top = 40.dp, bottom = 40.dp)
    ) {
        // Title
        Box(
            modifier = Modifier.weight(1f).fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            SpringyBouncingLetters(word = title)
        }

        // Back button (top-right within its row)
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.TopEnd
        ) {
            Button(onClick = { navController.popBackStack() }) {
                Text("Back", fontSize = 18.sp)
            }
        }

        // Scrollable help content
        LazyColumn(
            modifier = Modifier
                .weight(6f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header
            item {
                Text(
                    text = "How To Use Choosey",
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Intro
            item {
                Text(
                    text = "Welcome to Choosey – your fun little helper for when you just can’t decide! \n 🎲✨",
                    fontSize = 15.sp,
                    lineHeight = 18.sp,
                    color = Color.White
                )
            }
            item {
                Text(
                    text = "This guide will walk you through how to use the app.",
                    fontSize = 15.sp,
                    lineHeight = 18.sp,
                    color = Color.White
                )
            }

            // Home Screen
            item {
                Text(
                    text = "🏠 Home Screen (Choosey Screen)\n\nThe big round red button is the heart of the app.",
                    fontSize = 15.sp,
                    color = Color.White
                )
            }
            item {
                Image(
                    painter = painterResource(R.drawable.choosey_button),
                    contentDescription = "Help me Choosey button"
                )
            }

            item {
                Text(
                    text = "Tap “Help Me Choosey” → Choosey will randomly pick from your selected options (by default either no options or YES / NO will be selected).",
                    fontSize = 15.sp,
                    color = Color.White
                )
            }

            // Categories
            item {
                Text(
                    text = "📂 Choosing Categories\n\nTap the “Choose Category” button at the top of the Home Screen to pick which category Choosey should decide on.",
                    fontSize = 15.sp,
                    color = Color.White
                )
            }
            item {
                Image(
                    painter = painterResource(R.drawable.category_button),
                    contentDescription = "Category button"
                )
            }
            item {
                Image(
                    painter = painterResource(R.drawable.category_select),
                    contentDescription = "Category dropdown options"
                )
            }

            // Add option
            item {
                Text(
                    text = "➕ Adding Your Own Options\n\nOn the Category Selection screen, below the dropdown, tap “Add Option” to add your own custom choice.",
                    fontSize = 15.sp,
                    color = Color.White
                )
            }
            item {
                Image(
                    painter = painterResource(R.drawable.add_option_button),
                    contentDescription = "Add option button"
                )
            }
            item {
                Image(
                    painter = painterResource(R.drawable.add_option_popup),
                    contentDescription = "Add option popup"
                )
            }

            // Selecting items
            item {
                Text(
                    text = "✅ Selecting Items\n\nYou’ll see a list of options for the chosen category. Tap an option to select it. Selected items change colour and appear in the summary.",
                    fontSize = 15.sp,
                    color = Color.White
                )
            }
            item {
                Image(
                    painter = painterResource(R.drawable.options_list),
                    contentDescription = "Options list with selections"
                )
            }

            // Display chosen
            item {
                Text(
                    text = "Once you’ve chosen, you’ll see what Choosey picked:",
                    fontSize = 15.sp,
                    color = Color.White
                )
            }
            item {
                Image(
                    painter = painterResource(R.drawable.chosen_display),
                    contentDescription = "Chosen display"
                )
            }

            // Navigating back
            item {
                Text(
                    text = "🔙 Navigating Back\n\nOnce you’ve selected your options, tap Next to return to the home screen.",
                    fontSize = 15.sp,
                    color = Color.White
                )
            }
            item {
                Image(
                    painter = painterResource(R.drawable.next_button),
                    contentDescription = "Next button"
                )
            }

            // Tips
            item {
                Text(
                    text = "🔔 Tips\n\n• You can switch categories anytime; selections per category are saved.\n• Adding personal options makes it more fun (restaurants, activities, etc.).\n• Great in groups for deciding what to eat or watch! 🎉",
                    fontSize = 15.sp,
                    color = Color.White
                )
            }
        }
    }
    }