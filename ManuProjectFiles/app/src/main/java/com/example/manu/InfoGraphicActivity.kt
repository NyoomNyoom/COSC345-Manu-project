/**
 * @author Will Rushton, 12/08/2022
 */

package com.example.manu

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.WindowCompat
import kotlinx.android.synthetic.main.info_graphic_activity.*


/**
 * Collects and adds the bird information into the infographic scene.
 **/
class InfoGraphicActivity : AppCompatActivity() {

    private lateinit var gestureDetector: GestureDetectorCompat
    private lateinit var buttonPress: Animation
    private var cardViews: ArrayList<View> = ArrayList()

    /**
     * This is run when the class is instantiated. It sets up the encyclopedia screen.
     *
     * @param Bundle Saves information between separate loads of this activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info_graphic_activity)

        AudioManager.resumeAudio()

        buttonPress = AnimationUtils.loadAnimation(this, R.anim.button_press)

        // Hide the navigation and status bars.
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        WindowCompat.setDecorFitsSystemWindows(window, true)  // Places the layout outside the navbar and status bar.

        gestureDetector = GestureDetectorCompat(this, GestureListener())

        val allButtons: ArrayList<View>
        allButtons = (findViewById<View>(R.id.scrollView1) as ScrollView).touchables // Size is 48 (birds 47 + 1)
        val size = allButtons.size // Checking how many items there are.

        var allTextViews = getAllTextViews(findViewById(R.id.scrollView1))

        var birds: ArrayList<Bird> = BirdDatabase.getBirdsWithResource(QuestionType.PHOTO)
        //var cardViews: ArrayList<MaterialCardView> = ArrayList()

        storeCardViews()

        for(i in 0 until size){
            // Get bird image and add it to button
            allButtons[i].setBackgroundColor(Color.RED)
            try {
                allButtons[i].setBackgroundResource(birds[i].getPhotoResourceId())
                allTextViews?.get(i)?.setText(birds[i].getBirdName())
                if(allTextViews?.get(i)?.text == "null") {
                    allButtons[i].visibility = View.GONE
                }
            } catch (e: Exception){
                allButtons[i].visibility = View.GONE
            } // Sets the backgroundColor

            allButtons[i].setOnClickListener {
                //allButtons[i].startAnimation(buttonPress)  // To animate image only, use this and delete next line.
                cardViews[i].startAnimation(buttonPress)
                
                var intent = Intent(this, InfographicPopupActivity::class.java)
                // Fill name and song and image
                intent.putExtra("birdName", birds[i].getBirdName())
                intent.putExtra("songResourceId", birds[i].getSongResourceId())

                // Pass image ID to popup
                intent.putExtra("imageResourceId", birds[i].getPhotoResourceId())

                // Get maori name
                if(birds[i].getMaoriName() == "") {
                    intent.putExtra("translatedName", "")
                } else {
                    intent.putExtra("translatedName", "Māori Name: " + birds[i].getMaoriName())
                }

                // Get Fun fact
                if(birds[i].getFunFact() == "") {
                    intent.putExtra("birdFact", "More information is yet to come...")
                } else {
                    intent.putExtra("birdFact", birds[i].getFunFact())
                }

                // Get Endangerment status
                if(birds[i].getEndangerment() == "") {
                    intent.putExtra("endangerment", "Endangerment: N/A")
                } else {
                    intent.putExtra("endangerment", "Endangerment: " + birds[i].getEndangerment())
                }

                startActivity(intent)
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            }
        }

        btn_back.setOnClickListener {
            btn_back.startAnimation(buttonPress)
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("soundFlag", true)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            //don't finish the activity, saves reloading
        }
    }

    /**
     * Add all the TextViews to the Infographic screen.
     */
    fun getAllTextViews(v: View?): List<TextView>? {
        val result: MutableList<TextView> = ArrayList()
        if (v is ViewGroup) {
            val viewGroup = v
            for (i in 0 until viewGroup.childCount) {
                result.addAll(getAllTextViews(viewGroup.getChildAt(i))!!)
            }
        } else if (v is TextView) {
            result.add(v)
        }
        return result
    }

    /**
     * Overwrites the current gestures of scroll view
     */
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        var handled = super.dispatchTouchEvent(ev)
        handled = gestureDetector.onTouchEvent(ev!!)
        return handled
    }

    /**
     * Checks for touch events on the screen
     */
    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (gestureDetector.onTouchEvent(event)) {
            true
        }
        else{
            super.onTouchEvent(event)
        }
    }

    /**
     * Calculates if a gesture has been made and in what direction
     */
    inner class GestureListener : GestureDetector.SimpleOnGestureListener()
    {

        private val SWIPE_THRESHOLD = 100
        private val SWIPE_VELOCITY_THRESHOLD = 100

        override fun onFling(downEvent: MotionEvent?, moveEvent: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
            var diffX = moveEvent?.x?.minus(downEvent!!.x) ?: 0.0F
            var diffY = moveEvent?.y?.minus(downEvent!!.y) ?: 0.0F

            return if(Math.abs(diffX) > Math.abs(diffY)) {
                // this is a left or right swipe
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0 ){
                        // right swipe
                    } else {
                        // left swipe
                        this@InfoGraphicActivity.onSwipeLeft()
                    }
                    true
                } else {
                    super.onFling(downEvent, moveEvent, velocityX, velocityY)
                }
            } else {
                // this is a top or bottom swipe
                if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        // swipe top
                    } else {
                        // swipe bottom
                    }
                    true
                } else {
                    super.onFling(downEvent, moveEvent, velocityX, velocityY)
                }
            }
        }
    }

    /**
     * Returns to menu when left gesture initiatied.
     */
    private fun onSwipeLeft() {
        var intent = Intent(this, MenuActivity::class.java)
        intent.putExtra("soundFlag", true)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        finish()
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

    /**
     * Stores all card views for easy indexing. This is clearly a poor way of doing this, but was easier than collecting
     * all card
     */
    private fun storeCardViews() {
        cardViews.add(card0)
        cardViews.add(card1)
        cardViews.add(card2)
        cardViews.add(card3)
        cardViews.add(card4)
        cardViews.add(card5)
        cardViews.add(card6)
        cardViews.add(card7)
        cardViews.add(card8)
        cardViews.add(card9)
        cardViews.add(card10)
        cardViews.add(card11)
        cardViews.add(card12)
        cardViews.add(card13)
        cardViews.add(card14)
        cardViews.add(card15)
        cardViews.add(card16)
        cardViews.add(card17)
        cardViews.add(card18)
        cardViews.add(card19)
        cardViews.add(card20)
        cardViews.add(card21)
        cardViews.add(card22)
        cardViews.add(card23)
        cardViews.add(card24)
        cardViews.add(card25)
        cardViews.add(card26)
        cardViews.add(card27)
        cardViews.add(card28)
        cardViews.add(card29)
        cardViews.add(card30)
        cardViews.add(card31)
    }
}