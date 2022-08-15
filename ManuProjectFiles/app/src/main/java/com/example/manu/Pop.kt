package com.example.manu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.WindowManager
import kotlinx.android.synthetic.main.popup_window.*

class Pop : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.popup_window)

        val dm: DisplayMetrics = DisplayMetrics()
        getWindowManager().getDefaultDisplay().getMetrics(dm)
        val width: Int = dm.widthPixels
        val height: Int = dm.heightPixels

        getWindow().setLayout((width * 0.7).toInt(), (height * 0.5).toInt())

        val params: WindowManager.LayoutParams = getWindow().getAttributes()
        params.gravity = Gravity.CENTER
        params.x = 0
        params.y = -20

        getWindow().setAttributes(params)

        //val dm:DisplayMetrics = DisplayMetrics()
        //getWindow().setLayout(800, 600)
    }

}