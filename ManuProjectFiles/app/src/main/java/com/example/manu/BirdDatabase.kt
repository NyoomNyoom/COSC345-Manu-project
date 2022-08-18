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
            birds.add(BirdTemp("Bellbird", R.drawable.bird_bellbird))
            birds.add(BirdTemp("Blue Duck", R.drawable.bird_blue_duck))
            birds.add(BirdTemp("Kakapo", R.drawable.bird_kakapo))
            birds.add(BirdTemp("Kea", R.drawable.bird_kea))
            birds.add(BirdTemp("Kokako", R.drawable.bird_kokako))
            birds.add(BirdTemp("Morepork", R.drawable.bird_morepork))
            birds.add(BirdTemp("New Zealand Falcon", R.drawable.bird_new_zealand_falcon))
            birds.add(BirdTemp("Pukeko", R.drawable.bird_pukeko))
            birds.add(BirdTemp("Weka", R.drawable.bird_weka))
            birds.add(BirdTemp("White Heron", R.drawable.bird_white_heron))
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

        fun getNameUsingResourceId(resourceId: Int): String {
            for (bird in birds) {
                if (bird.getPhotoResourceId() == resourceId)
                    return bird.getBirdName()
            }

            return ""
        }

    }

}