/* Will Rushton 12/08/2022 */

package com.example.manu

import android.util.Log
import android.os.Bundle
import android.view.View
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_menu.*

/* Honestly I don't know yet...
 */
class InfoGraphicActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info_graphic_activity)

        val allButtons: ArrayList<View>
        allButtons = (findViewById<View>(R.id.scrollView1) as ScrollView).touchables // Size is 48 (birds 47 + 1)

        //Log.d("Layout Check", size)
    }
}