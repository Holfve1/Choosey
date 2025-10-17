package com.example.choosey


import android.widget.FrameLayout
import android.widget.VideoView
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri
import androidx.navigation.NavController
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController) {
    var showVideo by remember { mutableStateOf(true) }
    var startFadeOut by remember { mutableStateOf(false) }

    val alpha by animateFloatAsState(
        targetValue = if (startFadeOut) 0f else 1f,
        animationSpec = tween(durationMillis = 1000),
        label = "fadeOut"
    )

    LaunchedEffect(Unit) {
        delay(4500) // Show video for 4.5 seconds
        showVideo = false // Hide video
        delay(200) // Keep black screen visible
        startFadeOut = true
        delay(1000) // Wait for fade-out
        navController.navigate("Choosey") {
            popUpTo("Splash") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer { this.alpha = alpha }
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        if (showVideo) {
            AndroidView(
                factory = { context ->
                    FrameLayout(context).apply {
                        setBackgroundColor(android.graphics.Color.BLACK)
                        val videoView = VideoView(context).apply {
                            val videoUri = "android.resource://${context.packageName}/${R.raw.choosey_intro}".toUri()
                            setVideoURI(videoUri)
                            setZOrderOnTop(true)
                            setOnPreparedListener { it.isLooping = false }
                            start()
                        }
                        addView(videoView)
                    }
                },
                modifier = Modifier.size(300.dp)
            )
        }
    }
}

