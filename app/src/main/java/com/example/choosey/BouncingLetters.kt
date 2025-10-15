package com.example.choosey

import android.R.attr.text
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.choosey.ui.theme.FredokaOneFont
import kotlinx.coroutines.delay



@Composable
fun SpringyBouncingLetters(
    word: String = "CHOOSEY",
    amplitude: Dp = 30.dp,
    fontSize: Int = 60 // Accept font size as an Int (sp)
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val colors = listOf(
            Color(0xFFE57373), // red
            Color(0xFF64B5F6), // blue
            Color(0xFF81C784), // green
            Color(0xFFFFB74D), // orange
            Color(0xFFBA68C8)  // purple
        )

        word.forEachIndexed { index, char ->
            val offsetY = remember { Animatable(0f) }

            LaunchedEffect(Unit) {
                delay(index * 85L)
                offsetY.animateTo(
                    targetValue = -amplitude.value,
                    animationSpec = infiniteRepeatable(
                        animation = tween(durationMillis = 700),
                        repeatMode = RepeatMode.Reverse
                    )
                )
            }

            val color = colors[index % colors.size]

            Text(
                text = char.toString(),
                fontSize = fontSize.sp,
                fontFamily = FredokaOneFont,
                modifier = Modifier.offset(y = offsetY.value.dp),
                color = color
            )
        }
    }
}
