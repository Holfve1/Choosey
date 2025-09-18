package com.example.choosey

import android.content.Context
import android.media.MediaPlayer

fun playDrumRoll(context: Context, onComplete: () -> Unit) {
    val mediaPlayer = MediaPlayer.create(context, R.raw.drumroll) // put drumroll.mp3 in res/raw/
    mediaPlayer.setOnCompletionListener {
        onComplete()
        it.release() // cleanup
    }
    mediaPlayer.start()
}
