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
            birds.add(BirdTemp("Auckland Island Teal", R.drawable.bird_auckland_island_teal, ""))
            birds.add(BirdTemp("Australasian Bittern", R.drawable.bird_australasian_bittern, ""))
            birds.add(BirdTemp("Australasian Crested Grebe", R.drawable.bird_australasian_bittern, ""))
            birds.add(BirdTemp("Bellbird", R.drawable.bird_bellbird, "This is a very long fun fact specifically for Daniel to test his and Will's infographic popup. It needs to be long so the text stretches the whole popup really far. Daniel has ensured the bird's name (the popup's title) stays at the top instead of hanging near the centre and becoming tangled with the fact's text. He used 'centre_horizontal' instead of 'centre' because this ties it to the vertical axis (the centre of the horizontal axis) without tying it to the horizontal axis (the centre of the vertical axis). Anyway, I hope this fact is long enough and tests everything thoroughly. This app is looking great. Fantastic work everyone!!!"))
            birds.add(BirdTemp("Black Stilt", R.drawable.bird_black_stilt, ""))
            birds.add(BirdTemp("Blue Duck", R.drawable.bird_blue_duck, ""))
            birds.add(BirdTemp("Chatham Island Oystercatcher", R.drawable.bird_chatham_island_oystercatcher, "Since this bird has a long name, I need to test it with a fun fact."))
            birds.add(BirdTemp("Grey Warbler", R.drawable.bird_grey_warbler, ""))
            birds.add(BirdTemp("Huttons Shearwater", R.drawable.bird_huttons_shearwater, ""))
            birds.add(BirdTemp("Kakapo", R.drawable.bird_kakapo, ""))
            birds.add(BirdTemp("Kea", R.drawable.bird_kea, ""))
            birds.add(BirdTemp("Kokako", R.drawable.bird_kokako, ""))
            birds.add(BirdTemp("Morepork", R.drawable.bird_morepork, ""))
            birds.add(BirdTemp("New Zealand Dotterel", R.drawable.bird_new_zealand_dotterel, ""))
            birds.add(BirdTemp("New Zealand Falcon", R.drawable.bird_new_zealand_falcon, ""))
            birds.add(BirdTemp("New Zealand Pigeon", R.drawable.bird_new_zealand_pigeon, ""))
            birds.add(BirdTemp("Orange Fronted Parakeet", R.drawable.bird_orange_fronted_parakeet, ""))
            birds.add(BirdTemp("Paradise Duck", R.drawable.bird_paradise_duck, "Often seen by students in Dunedin, Paradise Ducks have been seen to exhibit homosexual behaviour by the presence of Bill and Bill within the University of Otago Campus. It is no wonder Bill and Bill have these tendancies due to their lovely chestnut breast feathers."))
            birds.add(BirdTemp("Pukeko", R.drawable.bird_pukeko, ""))
            birds.add(BirdTemp("Red Crowned Parakeet", R.drawable.bird_red_crowned_parakeet, ""))
            birds.add(BirdTemp("Rock Wren", R.drawable.bird_rock_wren, ""))
            birds.add(BirdTemp("Silvereye", R.drawable.bird_silvereye, ""))
            birds.add(BirdTemp("Stitchbird", R.drawable.bird_stitchbird, ""))
            birds.add(BirdTemp("Takahe", R.drawable.bird_takahe, ""))
            birds.add(BirdTemp("Weka", R.drawable.bird_weka, ""))
            birds.add(BirdTemp("Westland Black Petrel", R.drawable.bird_westland_black_petrel, ""))
            birds.add(BirdTemp("White Heron", R.drawable.bird_white_heron, ""))
            birds.add(BirdTemp("Whitehead", R.drawable.bird_whitehead, ""))
            birds.add(BirdTemp("Yellow Eyed Penguin", R.drawable.bird_yellow_eyed_penguin, ""))
            birds.add(BirdTemp("Yellowhead", R.drawable.bird_yellowhead, ""))


            birds.add(BirdTemp("North Island Brown Kiwi", R.drawable.bird_north_island_brown_kiwi, ""))
            birds.add(BirdTemp("Tui", R.drawable.bird_tui, "the Tui has quite a distinctive flight pattern, with louder flapping than most other birds due to its relatively short wide wings"))

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

        /**
         * A function to return the list of birds
         *
         * @return getBirdList the list of birds.
         */
        fun getBirdList(): ArrayList<BirdTemp>{
            return birds
        }

    }

}
