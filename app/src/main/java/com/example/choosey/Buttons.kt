package com.example.choosey

import android.R.attr.onClick
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
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
fun SelectionButton(
    onClick: () -> Unit,
    categoryName: String
) {
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = categoryName,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
        Button(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(12.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 6.dp,
                pressedElevation = 2.dp
            ),
            border = BorderStroke(1.dp, Color.Gray),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFB74D),
                contentColor = Color.White
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box {
                    Text(
                        text = "Change Category:",
                        fontSize = 18.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }

                //            Spacer(modifier = Modifier.height(8.dp))


                }
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
fun InfoButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Default.Info,
            contentDescription = "help",
            tint = Color.White,
            modifier = Modifier.size(34.dp)
        )
    }
}