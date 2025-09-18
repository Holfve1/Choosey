package com.example.choosey

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SelectionScreen(navController: NavController, vm: ChooseyViewModel) {
    val options = remember {
        mutableStateListOf(
            "Chinese",
            "Indian",
            "Pizza",
            "Fish & Chips",
            "Burger",
            "Turkish",
            "Thai",
            "Sushi",
            "Italian",
            "Mexican",
            "Chicken",
            "Japanese",
            "Vegan",
            "Vietnamese",
            "Steakhouse"
        )
    }

    for (choice in vm.choices) {
        if (choice !in options) options.add(choice)
    }

    var showAddDialog by remember { mutableStateOf(false) }
    var newItem by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, top = 70.dp, end = 20.dp, bottom = 50.dp)
    ) {
        SpringyBouncingLetters(word = "Choosey")

        Spacer(Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = { navController.popBackStack() }) { Text("Back") }
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = { showAddDialog = true }) { Text("Add") }
        }

        Spacer(Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(options, key = { it }) { option ->
                    val selected = option in vm.choices
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { vm.toggle(option) },
                        colors = CardDefaults.cardColors(
                            containerColor = if (selected) Color(0xFF99C2FF) else MaterialTheme.colorScheme.surface,
                            contentColor = if (selected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurface
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = if (selected) 4.dp else 1.dp
                        )
                    ) {
                        Text(
                            text = option,
                            modifier = Modifier.padding(16.dp),
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
        ) {
            Text(
                text = if (vm.choices.isEmpty()) "No selections yet" else vm.choices.joinToString(", "),
                fontSize = 16.sp
            )
        }
    }

    if (showAddDialog) {
        AlertDialog(
            onDismissRequest = { showAddDialog = false },
            title = { Text("Add item") },
            text = {
                TextField(
                    value = newItem,
                    onValueChange = { newItem = it },
                    singleLine = true
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    val t = newItem.trim()
                    if (t.isNotEmpty()) options.add(t)
                    newItem = ""
                    showAddDialog = false
                }) { Text("Add") }
            },
            dismissButton = {
                TextButton(onClick = {
                    newItem = ""
                    showAddDialog = false
                }) { Text("Cancel") }
            }
        )
    }
}
