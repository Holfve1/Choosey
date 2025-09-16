package com.example.choosey

import android.renderscript.ScriptGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color

@Composable
fun SelectionScreen(navController: NavController) {
    val options = listOf("Burgers", "Chinese", "Chicken", "Pizza", "Indian", "Japanese", "Sushi")
    val choices = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        // List area
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
                    val selected = option in choices
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            if (selected) choices.remove(option) else choices.add(option)
                        },
                        colors = CardDefaults.cardColors(
                            containerColor = if (selected)
                                Color(0xFF99c2ff)
                            else
                                MaterialTheme.colorScheme.surface,
                            contentColor = if (selected)
                                MaterialTheme.colorScheme.onPrimaryContainer
                            else
                                MaterialTheme.colorScheme.onSurface
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

        // Selection summary
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
        ) {
            Text(
                text = if (choices.isEmpty()) "No selections yet" else choices.joinToString(", "),
                fontSize = 16.sp
            )
        }
    }
}
