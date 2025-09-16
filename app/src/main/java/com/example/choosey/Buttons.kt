package com.example.choosey

import android.R.attr.onClick
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

//@Composable
//fun MainButton(answer: String, onClick: () -> Unit) {
//
//    Button(
//        onClick = onClick,
//
//        modifier = Modifier
//            .height(250.dp)
//            .width(250.dp)
//            .shadow(
//                elevation = 20.dp,
//                shape = CircleShape,
//                ambientColor = Color.Black,
//                spotColor = Color.Black
//            ),
//        elevation = ButtonDefaults.buttonElevation(
//            defaultElevation = 12.dp,
//            pressedElevation = 6.dp
//        ),
//        colors = ButtonDefaults.buttonColors(
//            containerColor = Color(0x66000000),
//            contentColor = Color.White
//        ),
//        border = BorderStroke(2.dp, Color.Black),
//        shape = CircleShape
//    ) {
//        Text(
//            answer,
//            fontSize = 30.sp,
//            lineHeight = 35.sp,
//            textAlign = TextAlign.Center
//        )
//    }
//}

@Composable
fun MainButton(answer: String, onClick: () -> Unit) {

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    Button(
        onClick = onClick,
        interactionSource = interactionSource,
        modifier = Modifier
            .height(250.dp)
            .width(250.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 20.dp, // visible raised effect
            pressedElevation = 6.dp   // pressed effect
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF146EB4), // base button color
            contentColor = Color.White
        ),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.DarkGray)
    ) {
        // Box to apply shine and pressed-in effect inside the button content
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .background(Color.Transparent), // let Button draw background
            contentAlignment = Alignment.Center
        ) {
            // 🌟 Glossy top-left shine
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .background(
                        Brush.radialGradient(
                            colors = listOf(
                                Color.White.copy(alpha = if (isPressed) 0.15f else 0.35f),
                                Color.Transparent
                            ),
                            center = Offset(60f, 60f), // top-left corner shine
                            radius = 180f
                        )
                    )
            )

            // Button label
            Text(
                text = answer,
                fontSize = 30.sp,
                lineHeight = 35.sp,
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainButtonPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MainButton(answer = "Help Me Choosey") { println("Pressed!") }
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
