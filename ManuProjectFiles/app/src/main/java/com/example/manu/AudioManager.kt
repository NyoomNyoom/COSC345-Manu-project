package com.example.manu

import android.content.Context
import android.media.MediaPlayer

class AudioManager {

    companion object {

        var mediaPlayer = MediaPlayer()

        fun playAudio(context: Context, audioResourceId: Int) {
            mediaPlayer.pause()
            mediaPlayer = MediaPlayer.create(context, audioResourceId)
            mediaPlayer.isLooping = true
            mediaPlayer.start()
        }

        fun resumeAudio() {
            mediaPlayer.start()
        }

        fun pauseAudio() {
            mediaPlayer.pause()
        }

        fun stopAudio() {
            mediaPlayer.stop()
        }

    }

}