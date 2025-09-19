package com.example.choosey

import android.R.attr.onClick
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable

fun MainButton(answer: String, onClick: () -> Unit) {
    val context = LocalContext.current

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    Button(
        onClick = {
            playDrumRoll(context) {
                // after the drum roll completes
                onClick()
            }
        },
        interactionSource = interactionSource,
        modifier = Modifier
            .height(250.dp)
            .width(250.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 20.dp, // visible raised effect
            pressedElevation = 6.dp   // pressed effect
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFB22222),
            contentColor = Color.White
        ),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.Gray)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .background(Color.Transparent),
            contentAlignment = Alignment.Center
        ) {
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
                            center = Offset(60f, 60f),
                            radius = 180f
                        )
                    )
            )

            Text(
                text = "Help me Choosey",
                fontSize = 30.sp,
                lineHeight = 35.sp,
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
    }
}

@Composable
fun SelectionButton(onClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    Button(
        onClick = onClick,
        interactionSource = interactionSource,
        modifier = Modifier
            .height(150.dp)
            .width(150.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 20.dp, // visible raised effect
            pressedElevation = 6.dp   // pressed effect
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFFFC107), // base button color
            contentColor = Color.White
        ),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.Gray)
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

                Text("Choose your Selection",
                    fontSize = 15.sp,
                    lineHeight = 20.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White)

        }
    }
}



//@Preview(showBackground = true)
//@Composable
//fun MainButtonPreview() {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(32.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        MainButton(answer = "Help Me Choosey") { println("Pressed!") }
//    }
//}

@Composable
fun InfoButton(onClick: () -> Unit

) {
    IconButton(
        onClick = onClick,
    ) {
        Icon(
            imageVector = Icons.Default.Info,
            contentDescription = "help",
            tint = Color.White,
            modifier = Modifier.size(28.dp)
        )
    }
}