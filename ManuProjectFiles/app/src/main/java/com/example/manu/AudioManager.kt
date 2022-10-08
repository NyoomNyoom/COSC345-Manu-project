/**
 * @author Daniel Robinson
 */

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

        /**
         * The persistent media player for playing sounds across all activities in the app.
         */
        private var mediaPlayer = MediaPlayer()

        /**
         * Plays, on loop, the audio resource provided.
         *
         * @param context The calling method's context (e.g., an AppCompatActivity).
         * @param audioResourceId The desired audio resource's resource ID.
         */
        fun playAudio(context: Context, audioResourceId: Int) {
            if(mediaPlayer.isPlaying){
                mediaPlayer.pause()
            }
            mediaPlayer = MediaPlayer.create(context, audioResourceId)
            mediaPlayer.isLooping = true

            if(!mediaPlayer.isPlaying){
                mediaPlayer.start()
            }
        }

        /**
         * Resumes playing the current audio resource.
         */
        fun resumeAudio() {
            if(!mediaPlayer.isPlaying){
                mediaPlayer.start()
            }
        }

        /**
         * Pauses the current playing audio track such that it can be resumed from the same place in the track.
         */
        fun pauseAudio() {
            if(mediaPlayer.isPlaying){
                mediaPlayer.pause()
            }
        }

        /**
         * Stops playing the current audio track. Calling this function means you cannot use resumeAudio on the current
         * track, instead you must call playAudio again.
         */
        fun stopAudio() {
            if(mediaPlayer.isPlaying) {
                mediaPlayer.stop()
            }
        }



    }

}