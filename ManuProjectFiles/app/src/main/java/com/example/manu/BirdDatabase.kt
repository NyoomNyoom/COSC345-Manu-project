package com.example.manu

import android.content.res.Resources

class BirdDatabase {

    companion object {

        private lateinit var birds: ArrayList<BirdTemp>

        fun compileDatabase() {
            birds = ArrayList()
            birds.add(BirdTemp("Randall Original", R.drawable.randall_original))
            birds.add(BirdTemp("Randall Burlesque", R.drawable.randall_burlesque))
            birds.add(BirdTemp("Randall Zeke", R.drawable.randall_zeke))
            birds.add(BirdTemp("Randall Icarus", R.drawable.randall_icarus))
            birds.add(BirdTemp("Randall Vanilla", R.drawable.randall_vanilla))
            birds.add(BirdTemp("Randall No Photo", Resources.ID_NULL))
        }

        fun getBirdDatabase(): ArrayList<BirdTemp> {
            return birds
        }

    }

}