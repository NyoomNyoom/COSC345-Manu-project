/**
 * @author Madeline McCane
 */

package com.example.manu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.method.LinkMovementMethod
import android.widget.TextView
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.view.WindowCompat
import kotlinx.android.synthetic.main.activity_quiz.*
import kotlinx.android.synthetic.main.credits.*
import kotlinx.android.synthetic.main.credits.btn_back

/**
 * Loads and controls the credits screen.
 */
class CreditActivity : AppCompatActivity() {

    private lateinit var buttonPress: Animation

    /**
     * This is run when the class is instantiated. It loads and sets up the credits screen.
     *
     * @param Bundle Saves information between separate loads of this activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.credits)

        AudioManager.resumeAudio()

        // Hide the navigation and status bars.
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        WindowCompat.setDecorFitsSystemWindows(window, true)  // Places the layout outside the navbar and status bar.

        loadButtonPressAnimation()

        btn_back.setOnClickListener {
            btn_back.isClickable = false

            btn_back.startAnimation(buttonPress)
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("soundFlag", true)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()

            object : CountDownTimer(300, 100) {

                override fun onTick(millisUntilFinished: Long) {}

                override fun onFinish() {
                    btn_back.isClickable = true
                }
            }.start()
        }

        // Declaring and Initializing the TextViews from the layout file
        val textView1 = findViewById<TextView>(R.id.doc_link)
        val textView2 = findViewById<TextView>(R.id.ait_link)
        val textView3 = findViewById<TextView>(R.id.a_bittern_link)
        val textView4 = findViewById<TextView>(R.id.bellbird_link)
        val textView5 = findViewById<TextView>(R.id.black_stilt_link)
        val textView6 = findViewById<TextView>(R.id.oystercatcher_link)
        val textView7 = findViewById<TextView>(R.id.grey_warbler_link)
        val textView8 = findViewById<TextView>(R.id.shearwater_link)
        val textView9 = findViewById<TextView>(R.id.kakapo_link)
        val textView10 = findViewById<TextView>(R.id.kokako_link)
        val textView11 = findViewById<TextView>(R.id.morepork_link)
        val textView12 = findViewById<TextView>(R.id.nz_dotterel_link)
        val textView13 = findViewById<TextView>(R.id.nz_falcon_link)
        val textView14 = findViewById<TextView>(R.id.nz_pigeon_link)
        val textView15 = findViewById<TextView>(R.id.orange_parakeet_link)
        val textView16 = findViewById<TextView>(R.id.rock_wren_link)
        val textView17 = findViewById<TextView>(R.id.stitchbird_link)
        val textView18 = findViewById<TextView>(R.id.takahe_link)
        val textView19 = findViewById<TextView>(R.id.tui_link)
        val textView20 = findViewById<TextView>(R.id.weka_link)
        val textView21 = findViewById<TextView>(R.id.westland_petrel_link)
        val textView22 = findViewById<TextView>(R.id.white_heron_link)
        val textView23 = findViewById<TextView>(R.id.whitehead_link)
        val textView24 = findViewById<TextView>(R.id.yellow_eyed_link)
        val textView25 = findViewById<TextView>(R.id.yellowhead_link)
        val textView26 = findViewById<TextView>(R.id.by_2_link)
        val textView27 = findViewById<TextView>(R.id.by_4_link)
        val textView28 = findViewById<TextView>(R.id.by_sa_2_link)
        val textView29 = findViewById<TextView>(R.id.by_sa_4_link)

        // Finding and displaying the content that consists a URL as a hyperlink
        textView1.movementMethod = LinkMovementMethod.getInstance()
        textView2.movementMethod = LinkMovementMethod.getInstance()
        textView3.movementMethod = LinkMovementMethod.getInstance()
        textView4.movementMethod = LinkMovementMethod.getInstance()
        textView5.movementMethod = LinkMovementMethod.getInstance()
        textView6.movementMethod = LinkMovementMethod.getInstance()
        textView7.movementMethod = LinkMovementMethod.getInstance()
        textView8.movementMethod = LinkMovementMethod.getInstance()
        textView9.movementMethod = LinkMovementMethod.getInstance()
        textView10.movementMethod = LinkMovementMethod.getInstance()
        textView11.movementMethod = LinkMovementMethod.getInstance()
        textView12.movementMethod = LinkMovementMethod.getInstance()
        textView13.movementMethod = LinkMovementMethod.getInstance()
        textView14.movementMethod = LinkMovementMethod.getInstance()
        textView15.movementMethod = LinkMovementMethod.getInstance()
        textView16.movementMethod = LinkMovementMethod.getInstance()
        textView17.movementMethod = LinkMovementMethod.getInstance()
        textView18.movementMethod = LinkMovementMethod.getInstance()
        textView19.movementMethod = LinkMovementMethod.getInstance()
        textView20.movementMethod = LinkMovementMethod.getInstance()
        textView21.movementMethod = LinkMovementMethod.getInstance()
        textView22.movementMethod = LinkMovementMethod.getInstance()
        textView23.movementMethod = LinkMovementMethod.getInstance()
        textView24.movementMethod = LinkMovementMethod.getInstance()
        textView25.movementMethod = LinkMovementMethod.getInstance()
        textView26.movementMethod = LinkMovementMethod.getInstance()
        textView27.movementMethod = LinkMovementMethod.getInstance()
        textView28.movementMethod = LinkMovementMethod.getInstance()
        textView29.movementMethod = LinkMovementMethod.getInstance()


    }

    private fun loadButtonPressAnimation() {
        buttonPress = AnimationUtils.loadAnimation(this, R.anim.button_press)
    }

    /**
     * Pauses the audio when the app is quit or the screen closes.
     */
    override fun onPause() {
        super.onPause()
        AudioManager.pauseAudio()
    }

    /**
     * Resumes audio when the app is opened again.
     */
    override fun onResume() {
        super.onResume()
        AudioManager.resumeAudio()
    }

    /**
     * Disables any effects of the Android system's back button.
     */
    override fun onBackPressed() {
        return
    }

}