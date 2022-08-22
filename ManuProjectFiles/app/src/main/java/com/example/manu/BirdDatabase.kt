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
            birds.add(BirdTemp("Paradise Duck", R.drawable.bird_paradise_duck, "Often seen by students in Dunedin, Paradise Ducks have been seen to exhibit homosexual behaviour by the presence of Bill and Bill within the University of Otago Campus. It is no wonder Bill and Bill have these tendancies due to their lovely chestnut breast feathers."))
            birds.add(BirdTemp("Bellbird", R.drawable.bird_bellbird, ""))
            birds.add(BirdTemp("Blue Duck", R.drawable.bird_blue_duck, ""))
            birds.add(BirdTemp("Kakapo", R.drawable.bird_kakapo, ""))
            birds.add(BirdTemp("Kea", R.drawable.bird_kea, ""))
            birds.add(BirdTemp("Kokako", R.drawable.bird_kokako, ""))
            birds.add(BirdTemp("Morepork", R.drawable.bird_morepork, ""))
            birds.add(BirdTemp("New Zealand Falcon", R.drawable.bird_new_zealand_falcon, ""))
            birds.add(BirdTemp("Pukeko", R.drawable.bird_pukeko, ""))
            birds.add(BirdTemp("Weka", R.drawable.bird_weka, ""))
            birds.add(BirdTemp("White Heron", R.drawable.bird_white_heron, ""))
            birds.add(BirdTemp("Auckland Island Teal", R.drawable.bird_auckland_island_teal, ""))
            birds.add(BirdTemp("Australasian Bittern", R.drawable.bird_australasian_bittern, ""))
            birds.add(BirdTemp("Black Stilt", R.drawable.bird_black_stilt, ""))
            birds.add(BirdTemp("Chatham Island Oystercatcher", R.drawable.bird_chatham_island_oystercatcher, ""))
            birds.add(BirdTemp("Grey Warbler", R.drawable.bird_grey_warbler, ""))
            birds.add(BirdTemp("Huttons Shearwater", R.drawable.bird_huttons_shearwater, ""))
            birds.add(BirdTemp("Morepork", R.drawable.bird_morepork, ""))
            birds.add(BirdTemp("New Zealand Dotterel", R.drawable.bird_new_zealand_dotterel, ""))
            birds.add(BirdTemp("New Zealand Pigeon", R.drawable.bird_new_zealand_pidgeon, ""))
            birds.add(BirdTemp("Orange Fronted Parakeet", R.drawable.bird_orange_fronted__parakeet, ""))
            birds.add(BirdTemp("Kiwi", Resources.ID_NULL, ""))
            birds.add(BirdTemp("Tui", Resources.ID_NULL, "the Tui has quite a distinctive flight pattern, with louder flapping than most other birds due to its relatively short wide wings"))

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

        /**
         * Given the resource ID (which references the resource used as the question), this will return the name of the
         * matching bird.
         *
         * @return The name of the bird that owns that resource.
         */
        fun getNameUsingResourceId(resourceId: Int): String {
            for (bird in birds) {
                if (bird.getPhotoResourceId() == resourceId)
                    return bird.getBirdName()
            }

            return ""
        }

    }

}
