/**
 * @author Daniel Robinson
 */

package com.example.manu

import android.content.res.Resources

/**
 * Contains the bird database and handles queries to it.
 */
class BirdDatabase {

    companion object {  // Makes its embedded functions static.

        private lateinit var birds: ArrayList<BirdTemp>

        /**
         * Compiles the bird database by creating and adding each individual bird.
         */
        fun compileDatabase() {

            birds = ArrayList()
            birds.add(BirdTemp("Auckland Island Teal",R.drawable.bird_auckland_island_teal, "There is an island in the Auckland Islands group called 'Disappointment Island'."))
            birds.add(BirdTemp("Australasian Bittern", R.drawable.bird_australasian_bittern, "These globally endangered birds are important to Māori. They appear in Māori legends, stories, and even place names."))
            birds.add(BirdTemp("Australasian Crested Grebe", R.drawable.bird_australasian_crested_grebe, "While there are fewer than 1000 in Aotearoa, there used to be around 200 in the 1980s."))
            birds.add(BirdTemp("Bellbird", R.drawable.bird_bellbird, "Bellbirds help regenerate the forest in two ways. They pollinate the flowers of many native trees and shrubs and, when they feed on the fruits from this pollination process, they disperse the seeds."))
            birds.add(BirdTemp("Black Stilt", R.drawable.bird_black_stilt, ""))
            birds.add(BirdTemp("Blue Duck", R.drawable.bird_blue_duck, ""))
            birds.add(BirdTemp("Chatham Island Oystercatcher", R.drawable.bird_chatham_island_oystercatcher, "Since this bird has a long name, I need to test it with a fun fact."))
            birds.add(BirdTemp("Grey Warbler", R.drawable.bird_grey_warbler, ""))
            birds.add(BirdTemp("Huttons Shearwater", R.drawable.bird_huttons_shearwater, ""))
            birds.add(BirdTemp("Kākāpō", R.drawable.bird_kakapo, ""))
            birds.add(BirdTemp("Kea", R.drawable.bird_kea, ""))
            birds.add(BirdTemp("Kōkako", R.drawable.bird_kokako, ""))
            birds.add(BirdTemp("Morepork", R.drawable.bird_morepork, ""))
            birds.add(BirdTemp("New Zealand Dotterel", R.drawable.bird_new_zealand_dotterel, ""))
            birds.add(BirdTemp("New Zealand Falcon", R.drawable.bird_new_zealand_falcon, ""))
            birds.add(BirdTemp("New Zealand Pigeon", R.drawable.bird_new_zealand_pigeon, ""))
            birds.add(BirdTemp("North Island Brown Kiwi", R.drawable.bird_north_island_brown_kiwi, ""))
            birds.add(BirdTemp("Orange Fronted Parakeet", R.drawable.bird_orange_fronted_parakeet, ""))
            birds.add(BirdTemp("Paradise Duck", R.drawable.bird_paradise_duck, "Paradise Ducks start breeding in their second or third year of life. They mate for life and return to the same nesting area every year."))
            birds.add(BirdTemp("Pūkeko", R.drawable.bird_pukeko, ""))
            birds.add(BirdTemp("Red Crowned Parakeet", R.drawable.bird_red_crowned_parakeet, ""))
            birds.add(BirdTemp("Rock Wren", R.drawable.bird_rock_wren, ""))
            birds.add(BirdTemp("Silvereye", R.drawable.bird_silvereye, ""))
            birds.add(BirdTemp("Stitchbird", R.drawable.bird_stitchbird, ""))
            birds.add(BirdTemp("Takahē", R.drawable.bird_takahe, ""))
            birds.add(BirdTemp("Tūī", R.drawable.bird_tui, "Early European Settlers referred to the Tūī as the 'parson bird', presumably because the white tufts of feathers on the front of its neck resembled a priest's clerical collar."))
            birds.add(BirdTemp("Weka", R.drawable.bird_weka, ""))
            birds.add(BirdTemp("Westland Black Petrel", R.drawable.bird_westland_black_petrel, ""))
            birds.add(BirdTemp("White Heron", R.drawable.bird_white_heron, ""))
            birds.add(BirdTemp("Whitehead", R.drawable.bird_whitehead, ""))
            birds.add(BirdTemp("Yellow Eyed Penguin", R.drawable.bird_yellow_eyed_penguin, ""))
            birds.add(BirdTemp("Yellowhead", R.drawable.bird_yellow_head, ""))
            //birdFrequencyTest()
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
         * Returns the bird with the specified photo resource ID.
         *
         * @param resourceId The photo's resource ID.
         *
         * @return The bird with the photo resource ID.
         */
        private fun getBirdUsingPhotoResourceId(resourceId: Int): BirdTemp {
            for (bird in birds) {
                if (bird.getPhotoResourceId() == resourceId)
                    return bird
            }

            return BirdTemp("EMPTY_NAME", Resources.ID_NULL, "EMPTY_FACT")
        }

        /**
         * A function to return the list of birds
         *
         * @return getBirdList The list of birds.
         */
        fun getBirdList(): ArrayList<BirdTemp> {
            return birds
        }

        /*fun getIndexUsingName(name: String): Int {
            var matchingBird: BirdTemp = null
            for (bird in birds) {
                if (bird.getBirdName() == name) {
                    matchingBird = bird
                }
            }
            return birds.indexOf(matchingBird)
        }*/

        /**
         * Generates quizzes and calculates the frequency at which each bird in the database appears.
         *
         * @param quizzes The number of quizzes to run.
         * @param questionsPerQuiz The number of questions per quiz.
         *
         * @return An array of integers where each integer represents the number of times that bird appeared in a quiz.
         * The bird is the bird in the database at the same index as the integer.
         */
        fun birdFrequencyTest(quizzes: Int, questionsPerQuiz: Int): IntArray {
            var birdFrequencies = IntArray(getBirdList().size){0}
            for (quizNum in 1..quizzes) {
                val questions: ArrayList<QuestionTemp> = QuizGenerator.generateQuiz(QuestionType.PHOTO,
                    questionsPerQuiz, 4)
                for (questionNum in 0 until questionsPerQuiz) {
                    val question = questions[questionNum]
                    birdFrequencies[birds.indexOf(getBirdUsingPhotoResourceId(question.getQuestionResourceId()))]++
                }
            }

            return birdFrequencies
        }

    }

}
