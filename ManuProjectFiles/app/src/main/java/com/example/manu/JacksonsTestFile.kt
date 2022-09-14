
package com.example.manu

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log  // ADDED: Allows you to print debugging statements.
import kotlinx.android.synthetic.main.test.*
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader


class JacksonsTestFile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)  // Calls the superclass' creation method to inherit its functionality.
        setContentView(R.layout.test)  // Change "activity_main" to your desired view.
        Log.d("HelloWorld", "Well, that worked!")  // ADDED: Print text to prove it works.

        var mp = MediaPlayer()



        createBirdBtn.setOnClickListener {
            /*
            var questions = mutableListOf<Question>()
            var birdadapter = BirdAdapter(questions)

            birdadapter.createQuiz(this,1,1)
            ListToStringTV.text = birdadapter.toString().

            IVbirdpics.setImageResource(questions[0].correctBirdObject.getPhotoResourceID())
            */

            val bird = Bird(0)
            bird.updateValues(this)

            ListToStringTV.text = bird.getAltName()



            mp.setDataSource(this, Uri.parse("android.resource://"+this.packageName+"/"+bird.getSongResourceID()))
            mp.prepare()
            mp.start()

        }

        pauseButton.setOnClickListener {
            mp.stop()
            mp.release()
            mp = MediaPlayer()
        }
    }
}