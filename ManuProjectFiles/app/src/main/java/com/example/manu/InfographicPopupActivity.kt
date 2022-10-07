/**
 * @author Daniel Robinson
 */

package com.example.manu

import android.app.Activity
import android.content.res.Resources
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.view.WindowCompat
import kotlinx.android.synthetic.main.activity_infographic_popup.*


/**
 * Controls the infographic popup that displays when you click on a bird in the infographics screen.
 */
class InfographicPopupActivity : Activity() {

    private var hasBirdSong = false
    private lateinit var buttonPress: Animation
    private lateinit var mediaPlayer: MediaPlayer

    /**
     * This is run when the class is instantiated. It sets up infographic popup layout.
     *
     * @param Bundle Saves information between separate loads of this activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infographic_popup)

        buttonPress = AnimationUtils.loadAnimation(this, R.anim.button_press)

        // Hide the navigation and status bars.
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        WindowCompat.setDecorFitsSystemWindows(window, true)  // Places the layout outside the navbar and status bar.

        btn_close.setOnClickListener { closeInfographicPopup() }

        txt_bird_name.text = intent.getStringExtra("birdName")
        txt_bird_fact.text = intent.getStringExtra("birdFact")
        txt_maori_name.text = intent.getStringExtra("translatedName")
        txt_endangerment.text = intent.getStringExtra("endangerment")

        // Get image ID for popup.
        val picture: Int = intent.getIntExtra("imageResourceId", Resources.ID_NULL)
        bird_photo.setImageResource(picture)

        val songResourceId = intent.getIntExtra("songResourceId", Resources.ID_NULL)
        if (songResourceId != Resources.ID_NULL) {
            mediaPlayer = MediaPlayer.create(this, songResourceId)
            mediaPlayer.isLooping = true
            mediaPlayer.start()
            hasBirdSong = true
        }
    }

    private fun closeInfographicPopup() {
        // Only pause a bird song if one is playing, otherwise the app crashes.
        if (hasBirdSong) {
            mediaPlayer.pause()
        }

        btn_close.startAnimation(buttonPress)
        finish()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)  // Must occur after we close the popup.
    }

    override fun onPause() {
        super.onPause()
        if (hasBirdSong)
            mediaPlayer.pause()
    }

    override fun onResume() {
        super.onResume()
        if (hasBirdSong)
            mediaPlayer.start()
    }

    /**
     * Disables any effects of the Android system's back button.
     */
    override fun onBackPressed() {
        return
    }

}