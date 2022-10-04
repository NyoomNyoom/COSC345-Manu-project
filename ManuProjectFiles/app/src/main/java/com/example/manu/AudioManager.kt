package com.example.manu

import android.content.Context
import android.media.MediaPlayer

/**
 * Manages the audio for this app. It plays, on loop, up to one audio track at a time.
 */
class AudioManager {

    /**
     * Manages the audio for this app. It plays, on loop, up to one audio track at a time.
     */
    companion object {

        var mediaPlayer = MediaPlayer()

        /**
         * Plays, on loop, the audio resource provided.
         *
         * @param context The calling method's context (e.g., an AppCompatActivity).
         * @param audioResourceId The desired audio resource's resource ID.
         */
        fun playAudio(context: Context, audioResourceId: Int) {
            mediaPlayer.pause()
            mediaPlayer = MediaPlayer.create(context, audioResourceId)
            mediaPlayer.isLooping = true
            mediaPlayer.start()
        }

        /**
         * Resumes playing the current audio resource.
         */
        fun resumeAudio() {
            mediaPlayer.start()
        }

        /**
         * Pauses the current playing audio track such that it can be resumed from the same place in the track.
         */
        fun pauseAudio() {
            mediaPlayer.pause()
        }

        /**
         * Stops playing the current audio track. Calling this function means you cannot use resumeAudio on the current
         * track, instead you must call playAudio again.
         */
        fun stopAudio() {
            mediaPlayer.stop()
        }

    }

}