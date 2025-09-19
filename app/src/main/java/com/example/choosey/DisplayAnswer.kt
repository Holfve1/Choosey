package com.example.choosey

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun DisplayAnswer(text: String, isFallback: Boolean = false) {
    Column(
        modifier = Modifier
            .background(
                color = Color(0xFF64B5F6), // old colour - 0xFF003E7E
                shape = RoundedCornerShape(12.dp)
            )
            .border(1.dp, Color.Gray, shape = RoundedCornerShape(12.dp))
            .padding(12.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        if (!isFallback) {
            Text(
                text = "Choosey chose...",
                fontSize = 18.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))  // Optional spacing between texts
        }

        Text(
            text = text,
            fontSize = if (isFallback) 22.sp else 30.sp,
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