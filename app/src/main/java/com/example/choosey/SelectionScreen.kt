package com.example.choosey
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.clickable
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
    val categoryId by vm.currentCategoryId
    val items by vm.selections(categoryId).collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF3A123E))
            .padding(20.dp)
    ) {
        SpringyBouncingLetters(word = "Choosey")

        Spacer(Modifier.height(12.dp))
        // --- Category switcher ---
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { vm.setCategory(1L) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (categoryId == 1L) Color(0xFF99C2FF)
                    else MaterialTheme.colorScheme.primary
                )
            ) { Text("Takeaway") }

            Button(
                onClick = { vm.setCategory(2L) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (categoryId == 2L) Color(0xFF99C2FF)
                    else MaterialTheme.colorScheme.primary
                )
            ) { Text("Movie Genre") }
            Button(onClick = { navController.popBackStack() }) { Text("Back") }
//            Spacer(modifier = Modifier.weight(1f))
//            Button(onClick = { showAddDialog = true }) { Text("Add") }
        }
        
        Spacer(Modifier.height(12.dp))

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
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }

//    if (showAddDialog) {
//        AlertDialog(
//            onDismissRequest = { showAddDialog = false },
//            title = { Text("Add item") },
//            text = {
//                TextField(
//                    value = newItem,
//                    onValueChange = { newItem = it },
//                    singleLine = true
//                )
//            },
//            confirmButton = {
//                TextButton(onClick = {
//                    val t = newItem.trim()
//                    if (t.isNotEmpty()) options.add(t)
//                    newItem = ""
//                    showAddDialog = false
//                }) { Text("Add") }
//            },
//            dismissButton = {
//                TextButton(onClick = {
//                    newItem = ""
//                    showAddDialog = false
//                }) { Text("Cancel") }
//            }
//        )
//    }
}
