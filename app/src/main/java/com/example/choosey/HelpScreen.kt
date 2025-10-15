package com.example.choosey

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
            verticalArrangement = Arrangement.spacedBy(24.dp) // larger spacing between blocks
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
                Column {
                    Text(
                        text = "Welcome to Choosey – your fun little helper for when you just can’t decide! 🎲✨",
                        fontSize = 16.sp,
                        lineHeight = 20.sp,
                        color = Color.White
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "This guide will walk you through how to use the app.",
                        fontSize = 16.sp,
                        lineHeight = 20.sp,
                        color = Color.White
                    )
                }
            }

            // Home Screen
            item {
                Column {
                    Text(
                        text = "🏠 Home Screen",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "The big round red button is the heart of the app.",
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
            }
            item {
                Image(
                    painter = painterResource(R.drawable.choosey_button),
                    contentDescription = "Help me Choosey button"
                )
            }
            item {
                Text(
                    text = "Tap the big red button → Choosey will randomly pick from your selected options (the default category is Takeaway, but at the start, no options have been added).",
                    fontSize = 16.sp,
                    color = Color.White
                )
            }

            // Categories
            item {
                Column {
                    Text(
                        text = "📂 Choosing Categories",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "Tap the little edit icon under the category at the top of the Home Screen, this will take you to the Edit screen, where you can pick or create which category Choosey should decide on and the options that come within.",
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
            }
            item {
                Image(
                    painter = painterResource(R.drawable.category_button),
                    contentDescription = "Category button",
                    modifier = Modifier
                        .size(120.dp) // 👈 makes it square (width & height)
                        .padding(8.dp)
                )
            }
            item {
                Image(
                    painter = painterResource(R.drawable.category_select2),
                    contentDescription = "Category dropdown options"
                )
            }

            // Add option
            item {
                Column {
                    Text(
                        text = "➕ Adding Your Own Options",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "On the Category Selection screen, below the dropdown, tap “Add Option” to add your own custom choice.",
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
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
                Column {
                    Text(
                        text = "✅ Selecting Items",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "You’ll see a list of options for the chosen category. Tap an option to select it. Selected items change colour and appear in the summary at the bottom. You can also use the select all button to select all options at once or use it to deselect everything.",
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
            }
            item {
                Image(
                    painter = painterResource(R.drawable.options_list),
                    contentDescription = "Options list with selections"
                )
            }
            item {
                Image(
                    painter = painterResource(R.drawable.options_list2),
                    contentDescription = "Options list with selections"
                )
            }

            // Navigating back
            item {
                Column {
                    Text(
                        text = "🔙 Navigating Back",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "Once you’ve selected your options, tap Continue to return to the home screen.",
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
            }
            item {
                Image(
                    painter = painterResource(R.drawable.next_button),
                    contentDescription = "Next button"
                )
            }

            // Display chosen
            item {
                Column {
                    Text(
                        text = "🎉 Displaying Your Result",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "Once you’ve finished selecting the category and options, press that big red button button and you’ll see what Choosey picked:",
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
            }
            item {
                Image(
                    painter = painterResource(R.drawable.chosen_display),
                    contentDescription = "Chosen display"
                )
            }



            // Tips
            item {
                Column {
                    Text(
                        text = "🔔 Tips",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "• You can switch categories anytime; selections per category are saved.\n• Adding personal options makes it more fun (restaurants, activities, etc.).\n• Great in groups for deciding what to eat or watch! 🎉",
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}
