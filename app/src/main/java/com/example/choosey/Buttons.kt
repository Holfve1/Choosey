package com.example.choosey

import android.R.attr.onClick
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

@Composable
fun MainButton(answer: String, onClick: () -> Unit) {

    Button(
        onClick = onClick,

        modifier = Modifier
            .height(250.dp)
            .width(250.dp)
            .shadow(
                elevation = 20.dp,
                shape = CircleShape,
                ambientColor = Color.Black,
                spotColor = Color.Black
            ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 12.dp,
            pressedElevation = 6.dp
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0x66000000),
            contentColor = Color.White
        ),
        border = BorderStroke(2.dp, Color.Black),
        shape = CircleShape
    ) {
        Text(
            answer,
            fontSize = 30.sp,
            lineHeight = 35.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun SelectionButton(onClick: () -> Unit) {
    Button(onClick = onClick,
        modifier = Modifier
            .height(150.dp)
            .width(150.dp),
        )
    {
        Text("Edit Selection")
    }
}
