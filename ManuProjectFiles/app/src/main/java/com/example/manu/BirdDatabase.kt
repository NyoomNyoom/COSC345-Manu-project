/**
 * @author Daniel Robinson
 */

package com.example.manu

import android.content.res.Resources

/**
 * Contains the bird database and handles queries to it.
 */
class BirdDatabase {

    companion object {  // Makes the functions static.

        private lateinit var birds: ArrayList<BirdTemp>

        /**
         * Compiles the bird database by creating and adding each individual bird.
         */
        fun compileDatabase() {
            birds = ArrayList()
            birds.add(BirdTemp("Randall Original", R.drawable.randall_original))
            birds.add(BirdTemp("Randall Burlesque", R.drawable.randall_burlesque))
            birds.add(BirdTemp("Randall Zeke", R.drawable.randall_zeke))
            birds.add(BirdTemp("Randall Icarus", R.drawable.randall_icarus))
            birds.add(BirdTemp("Randall Vanilla", R.drawable.randall_vanilla))
            birds.add(BirdTemp("Randall No Photo", Resources.ID_NULL))
        }

        /**
         * Returns all birds which have the specified resource listed.
         *
         * @return A list of all birds which have the specified resource listed.
         */
        fun getBirdsWithResource(resourceType: QuestionType): ArrayList<BirdTemp> {
            var queriedBirds: ArrayList<BirdTemp> = ArrayList()

            /*
             * All birds with photos.
             */
            if (resourceType == QuestionType.PHOTO) {
                for (bird: BirdTemp in birds) {
                    if (bird.getPhotoResourceId() != Resources.ID_NULL) {
                        queriedBirds.add(bird)
                    }
                }
            }

            return queriedBirds
        }

    }

}