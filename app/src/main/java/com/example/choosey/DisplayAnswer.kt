package com.example.choosey

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp



@Composable
fun DisplayAnswer(
    text: String,
    isFallback: Boolean = false,
    fontSize: Int = 50 // Accept font size as an Int (sp)
) {
    Column {
        Text(
            text = text,
            fontSize = fontSize.sp,
            lineHeight = (fontSize + 5).sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Preview
@Composable
fun DisplayAnswerPreview() {
    DisplayAnswer(text = "test")
}