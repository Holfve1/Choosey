package com.example.choosey

import android.R.attr.maxHeight
import android.R.attr.maxWidth
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontVariation.weight
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Composable
fun MainButton(
    answer: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    fontSize: Int = 30 // default font size
) {
    val context = LocalContext.current
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    Button(
        onClick = {
            playDrumRoll(context) {
                onClick()
            }
        },
        interactionSource = interactionSource,
        modifier = modifier
            .size(280.dp), // default size, can be overridden
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 20.dp,
            pressedElevation = 6.dp
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
                text = "Click me to Choosey",
                fontSize = fontSize.sp,
                lineHeight = (fontSize + 5).sp,
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
    }
}



@Composable
fun SelectionButton(
    onClick: () -> Unit,
    categoryName: String,
    modifier: Modifier = Modifier
) {
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Category:",
                fontSize = 17.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
            )
        }
        Text(
            text = categoryName,
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFFB74D),
            textAlign = TextAlign.Center
        )
        IconButton(
            onClick = onClick,
            modifier = modifier
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit",
                tint = Color(0xFF3A123E),
                modifier = Modifier
                    .size(30.dp)
                    .background(Color.White, shape = CircleShape) // 👈 background here
                    .padding(4.dp) // 👈 optional, so the icon isn't squashed
            )
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

@Composable
fun DeleteButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .background(
                color = Color(0xFF424242),
                shape = CircleShape
            )
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "Delete",
            tint = Color.White,
            modifier = Modifier.size(20.dp) // shrink a bit so it fits inside
        )
    }
}

// --- Add option dialog ---
@Composable
fun AddOptionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier

)
{
    val screenWidth = maxWidth
    val screenHeight = maxHeight
    val screenDpWidth = LocalConfiguration.current.screenWidthDp
    val screenSizeCategory = when {
        screenDpWidth < 360 -> "small"
        screenDpWidth in 360..480 -> "medium"
        else -> "large"
    }
    val fontSize = when (screenSizeCategory) {
        "small" -> 12.sp
        "medium" -> 18.sp
        else -> 22.sp
    }

    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFBA68C8))
    ) {
        Text(
            text = "Add Option",
            fontSize = fontSize,
            textAlign = TextAlign.Center,
        )
    }
}
