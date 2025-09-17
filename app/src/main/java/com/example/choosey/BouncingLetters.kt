package com.example.choosey

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
    amplitude: Dp = 30.dp
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        word.forEachIndexed { index, char ->
            val offsetY = remember { Animatable(0f) }

            LaunchedEffect(Unit) {
                delay(index * 85L) // stagger per letter
                offsetY.animateTo(
                    targetValue = -amplitude.value,
                    animationSpec = infiniteRepeatable(
                        animation = tween(
                            durationMillis = 700, // time to move up
                        ),
                        repeatMode = RepeatMode.Reverse
                    )
                )
            }

            Text(
                text = char.toString(),
                fontSize = 50.sp,
                fontFamily = FredokaOneFont,
                modifier = Modifier.offset(y = offsetY.value.dp),
                color = Color.White
            )
        }
    }
}