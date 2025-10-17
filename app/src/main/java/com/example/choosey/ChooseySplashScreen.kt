package com.example.choosey

import android.widget.FrameLayout
import android.widget.VideoView
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.min
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    var startFadeOut by remember { mutableStateOf(false) }
    var videoDone by remember { mutableStateOf(false) }

    // Fade only the video layer; keep the root fully opaque
    val fadeAlpha by animateFloatAsState(
        targetValue = if (startFadeOut) 0f else 1f,
        animationSpec = tween(durationMillis = 800),
        label = "videoFade"
    )

    // When the video completes, fade out then navigate
    LaunchedEffect(videoDone) {
        if (videoDone) {
            startFadeOut = true
            delay(800) // match tween duration
            navController.navigate("Choosey") {
                popUpTo("Splash") { inclusive = true }
            }
        }
    }

    // Root stays black so nothing white peeks through
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        BoxWithConstraints {
            val boxSize = min(maxWidth, maxHeight) * 0.7f // responsive, centered
            Box(
                modifier = Modifier
                    .size(boxSize)
                    .align(Alignment.Center)
                    .alpha(fadeAlpha), // ← fade the video container only
                contentAlignment = Alignment.Center
            ) {
                AndroidView(
                    factory = { context ->
                        FrameLayout(context).apply {
                            setBackgroundColor(android.graphics.Color.BLACK)
                            val videoView = VideoView(context).apply {
                                val videoUri =
                                    "android.resource://${context.packageName}/${R.raw.choosey_intro}".toUri()
                                setVideoURI(videoUri)
                                setOnPreparedListener {
                                    it.isLooping = false
                                    start()
                                }
                                setOnCompletionListener {
                                    // Trigger fade + navigation via LaunchedEffect
                                    videoDone = true
                                }
                            }
                            addView(
                                videoView,
                                FrameLayout.LayoutParams(
                                    FrameLayout.LayoutParams.MATCH_PARENT,
                                    FrameLayout.LayoutParams.MATCH_PARENT
                                )
                            )
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}