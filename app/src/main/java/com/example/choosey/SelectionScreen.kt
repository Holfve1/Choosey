package com.example.choosey
import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.SearchBarDefaults.colors
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun SelectionScreen(
    navController: NavController,
    vm: ChooseyViewModel
) {
    // Dialog state
    var showAddDialog by remember { mutableStateOf(false) }
    var newItem by remember { mutableStateOf("") }

    val categoryId by vm.currentCategoryId
    val items by vm.selections(categoryId).collectAsState(initial = emptyList())

    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("Choose Category") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF3A123E))
            .padding(20.dp)
            .padding(top = 40.dp, bottom = 40.dp),
    ) {
        SpringyBouncingLetters(word = "CHOOSEY")

        Spacer(Modifier.height(20.dp))

        // --- Dropdown centered at the top ---
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = { expanded = true }) {
                Text(
                    text = selectedText,
                    fontSize = 18.sp
                )
                Icon(Icons.Default.ArrowDropDown, contentDescription = null)
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("YES / NO", fontSize = 18.sp) },
                    onClick = {
                        vm.setCategory(1L)
                        selectedText = "YES / NO"
                        expanded = false
                    }
                )
                DropdownMenuItem(
                    text = { Text("Takeaway", fontSize = 18.sp) },
                    onClick = {
                        vm.setCategory(4L)
                        selectedText = "Takeaway"
                        expanded = false
                    }
                )
                DropdownMenuItem(
                    text = { Text("Movie Genre", fontSize = 18.sp) },
                    onClick = {
                        vm.setCategory(2L)
                        selectedText = "Movie Genre"
                        expanded = false
                    }
                )
                DropdownMenuItem(
                    text = { Text("Date Night", fontSize = 18.sp) },
                    onClick = {
                        vm.setCategory(3L)
                        selectedText = "Date Night"
                        expanded = false
                    }
                )
            }
        }

        Spacer(Modifier.height(20.dp))

        // --- Add & Next buttons centered below dropdown ---
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { showAddDialog = true },
                modifier = Modifier.padding(end = 12.dp)
            ) {
                Text("Add Option", fontSize = 18.sp)
            }
            Button(onClick = { navController.popBackStack() }) {
                Text("Next", fontSize = 18.sp)
            }
        }

        Spacer(Modifier.height(20.dp))

        // --- List of items ---
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items, key = { it.id }) { item ->
                val selected = item.isSelected
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { vm.toggleSelection(item.id) },
                    colors = CardDefaults.cardColors(
                        containerColor = if (selected) Color(0xFF99C2FF)
                        else MaterialTheme.colorScheme.surface,
                        contentColor = if (selected) MaterialTheme.colorScheme.onPrimaryContainer
                        else MaterialTheme.colorScheme.onSurface
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = if (selected) 4.dp else 1.dp
                    )
                ) {
                    Text(
                        text = item.label,
                        modifier = Modifier.padding(16.dp),
                        fontSize = 20.sp
                    )
                }
            }
        }

        // --- Selected summary ---
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            val selectedLabels = items.filter { it.isSelected }.map { it.label }
            Text(
                text = if (selectedLabels.isEmpty()) "No selections yet"
                else selectedLabels.joinToString(", "),
                fontSize = 18.sp,
                color = Color.White
            )
        }
    }

    // --- Add option dialog ---
    if (showAddDialog) {
        AlertDialog(
            onDismissRequest = {
                newItem = ""
                showAddDialog = false
            },
            title = { Text("Add an option") },
            text = {
                OutlinedTextField(
                    value = newItem,
                    onValueChange = { newItem = it },
                    label = { Text("Option name") },
                    singleLine = true
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    val trimmed = newItem.trim()
                    if (trimmed.isNotEmpty()) {
                        vm.addOption(trimmed)
                    }
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
