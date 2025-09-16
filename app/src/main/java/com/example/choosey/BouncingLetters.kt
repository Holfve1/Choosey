package com.example.choosey

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay


@Composable
fun SpringyBouncingLetters(
    word: String = "CHOOSEY",
    amplitude: Dp = 20.dp
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        word.forEachIndexed { index, char ->
            val offsetY = remember { Animatable(0f) }

            LaunchedEffect(Unit) {
                delay(index * 70L) // shorter stagger for faster wave
                offsetY.animateTo(
                    targetValue = 0f,
                    animationSpec = infiniteRepeatable(
                        animation = keyframes {
                            durationMillis = 1800
                            -amplitude.value at 0
                            amplitude.value at 900
                            0f at 1800
                        },
                        repeatMode = RepeatMode.Restart
                    )
                )
            }

            Text(
                text = char.toString(),
                fontSize = 50.sp,
                fontWeight = FontWeight.ExtraBold,  // bold/balloon effect
                fontFamily = FontFamily.Monospace,
                modifier = Modifier.offset(y = offsetY.value.dp)
            )
        }
    }
}