package com.example.choosey
import android.R
import android.graphics.Paint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun SelectionScreen(
    navController: NavController,
    vm: ChooseyViewModel
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF3A123E))
    ) {
        val screenWidth = maxWidth
        val screenHeight = maxHeight
        val screenDpWidth = LocalConfiguration.current.screenWidthDp
        val screenSizeCategory = when {
            screenDpWidth < 360 -> "small"
            screenDpWidth in 360..480 -> "medium"
            else -> "large"
        }

        val horizontalPadding = when (screenSizeCategory) {
            "small" -> 12.dp
            "medium" -> 20.dp
            else -> 32.dp
        }

        val verticalPadding = when (screenSizeCategory) {
            "small" -> 12.dp
            "medium" -> 20.dp
            else -> 24.dp
        }

        val fontSize = when (screenSizeCategory) {
            "small" -> 12.sp
            "medium" -> 18.sp
            else -> 22.sp
        }

        val titleFontSize = when (screenSizeCategory) {
            "small" -> 32
            "medium" -> 48
            else -> 64
        }

        val buttonHeight = when (screenSizeCategory) {
            "small" -> 38.dp
            "medium" -> 52.dp
            else -> 60.dp
        }

        val cardHeight = when (screenSizeCategory) {
            "small" -> 46.dp
            "medium" -> 62.dp
            else -> 70.dp
        }

        val categories by vm.categories.collectAsState(initial = emptyList())
        val categoryId by vm.currentCategoryId
        val items by vm.selections(categoryId).collectAsState(initial = emptyList())

        var showAddDialog by remember { mutableStateOf(false) }
        var newItem by remember { mutableStateOf("") }

        var expanded by remember { mutableStateOf(false) }
        var selectedText by remember { mutableStateOf("Choose Category") }

        var showAddCategoryDialog by remember { mutableStateOf(false) }
        var newCategoryName by remember { mutableStateOf("") }

        var showDeleteDialog by remember { mutableStateOf(false) }
        var categoryToDelete by remember { mutableStateOf<Long?>(null) }

        var showDeleteOptionDialog by remember { mutableStateOf(false) }
        var optionToDelete by remember { mutableStateOf<Long?>(null) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = horizontalPadding, vertical = verticalPadding)
        ) {
            SpringyBouncingLetters(word = "CHOOSEY", fontSize = titleFontSize)

            Spacer(Modifier.height(verticalPadding))

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Button(
                    onClick = { expanded = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(buttonHeight),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFB74D))
                ) {
                    Text(text = selectedText, fontSize = fontSize)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                }

                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    DropdownMenuItem(
                        text = { Text("Add Category ➕", fontSize = fontSize) },
                        onClick = {
                            expanded = false
                            showAddCategoryDialog = true
                        }
                    )

                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        thickness = 1.dp,
                        color = Color.LightGray
                    )

                    categories.forEach { category ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            DropdownMenuItem(
                                text = { Text(category.name, fontSize = fontSize) },
                                onClick = {
                                    vm.setCategory(category.id)
                                    selectedText = category.name
                                    expanded = false
                                },
                                modifier = Modifier.weight(1f)
                            )

                            if (category.id > 4) {
                                IconButton(onClick = {
                                    categoryToDelete = category.id
                                    showDeleteDialog = true
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Delete category",
                                        tint = Color.Black
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(verticalPadding))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AddOptionButton(
                    onClick = { showAddDialog = true },
                    modifier = Modifier.height(buttonHeight)
                )

                Button(
                    onClick = { navController.navigate("Choosey") },
                    modifier = Modifier.height(buttonHeight),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE57373))
                ) {
                    Text(text = "Continue", fontSize = fontSize, textAlign = TextAlign.Center)
                }
            }

            Spacer(Modifier.height(verticalPadding))

            val allSelected = items.isNotEmpty() && items.all { it.isSelected }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                onClick = { vm.setAllSelected(categoryId, select = !allSelected) },
                colors = CardDefaults.cardColors(
                    containerColor = if (allSelected) Color(0xFF81C784) else Color.Gray,
                    contentColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = if (allSelected) 4.dp else 1.dp
                )
            ) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text(
                        text = if (!allSelected) "SELECT ALL" else "DESELECT ALL",
                        modifier = Modifier.padding(16.dp),
                        fontSize = fontSize,
                        textAlign = TextAlign.Center
                    )
                }
            }

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(items, key = { it.id }) { item ->
                    val selected = item.isSelected

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(cardHeight),
                        onClick = { vm.toggleSelection(item.id) },
                        colors = CardDefaults.cardColors(
                            containerColor = if (selected) Color(0xFF81C784) else Color.Gray,
                            contentColor = Color.White
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = if (selected) 4.dp else 1.dp
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 14.dp, vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = item.label, fontSize = fontSize)
                            DeleteButton(onClick = {
                                optionToDelete = item.id
                                showDeleteOptionDialog = true
                            })
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(verticalPadding))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 80.dp)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                val selectedLabels = items.filter { it.isSelected }.map { it.label }
                Text(
                    text = if (selectedLabels.isEmpty()) "No selections yet"
                    else "Selected: ${selectedLabels.joinToString(", ")}",
                    fontSize = fontSize,
                    color = Color.White
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontalPadding),
                contentAlignment = Alignment.CenterStart
            ) {
                InfoButton(onClick = { navController.navigate("help") })
            }

            // Dialogs
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
                            onValueChange = {
                                if (it.length <= 17) newItem = it
                            },
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

            if (showAddCategoryDialog) {
                AlertDialog(
                    onDismissRequest = {
                        newCategoryName = ""
                        showAddCategoryDialog = false
                    },
                    title = { Text("Add a new category") },
                    text = {
                        OutlinedTextField(
                            value = newCategoryName,
                            onValueChange = { newCategoryName = it },
                            label = { Text("Category name") },
                            singleLine = true
                        )
                    },
                    confirmButton = {
                        TextButton(onClick = {
                            val trimmed = newCategoryName.trim()
                            if (trimmed.isNotEmpty()) {
                                vm.addCategory(trimmed) { newId ->
                                    vm.setCategory(newId)
                                    selectedText = trimmed
                                }
                            }
                            newCategoryName = ""
                            showAddCategoryDialog = false
                        }) { Text("Add") }
                    },
                    dismissButton = {
                        TextButton(onClick = {
                            newCategoryName = ""
                            showAddCategoryDialog = false
                        }) { Text("Cancel") }
                    }
                )
            }

            if (showDeleteOptionDialog && optionToDelete != null) {
                AlertDialog(
                    onDismissRequest = {
                        showDeleteOptionDialog = false
                        optionToDelete = null
                    },
                    title = { Text("Delete Option") },
                    text = { Text("Are you sure you want to delete this option?") },
                    confirmButton = {
                        TextButton(onClick = {
                            vm.deleteById(optionToDelete!!)
                            showDeleteOptionDialog = false
                            optionToDelete = null
                        }) {
                            Text("Delete", color = Color.Red)
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = {
                            showDeleteOptionDialog = false
                            optionToDelete = null
                        }) {
                            Text("Cancel")
                        }
                    }
                )
            }

            if (showDeleteDialog && categoryToDelete != null) {
                AlertDialog(
                    onDismissRequest = {
                        showDeleteDialog = false
                        categoryToDelete = null
                    },
                    title = { Text("Delete Category") },
                    text = { Text("Are you sure you want to delete this category and all its options?") },
                    confirmButton = {
                        TextButton(onClick = {
                            vm.deleteCategory(categoryToDelete!!)
                            if (categoryToDelete == categoryId) {
                                vm.setCategory(1L)
                                selectedText = "Choose Category"
                            }
                            showDeleteDialog = false
                            categoryToDelete = null
                        }) {
                            Text("Delete", color = Color.Red)
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = {
                            showDeleteDialog = false
                            categoryToDelete = null
                        }) {
                            Text("Cancel")
                        }
                    }
                )
            }
        }
    }
}




