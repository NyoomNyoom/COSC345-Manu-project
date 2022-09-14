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
            birds.add(BirdTemp("Auckland Island Teal", R.drawable.bird_auckland_island_teal, R.raw.aucklandislandteal, "Tētē kākāriki", "There is an island in the Auckland Islands group called 'Disappointment Island'."))
            birds.add(BirdTemp("Australasian Bittern", R.drawable.bird_australasian_bittern, Resources.ID_NULL, "Matuku-hūrepo", "These globally endangered birds are important to Māori. They appear in Māori legends, stories, and even place names."))
            birds.add(BirdTemp("Australasian Crested Grebe", R.drawable.bird_australasian_crested_grebe, Resources.ID_NULL, "", "While there are fewer than 1000 in Aotearoa, there used to be around 200 in the 1980s."))
            birds.add(BirdTemp("Bellbird", R.drawable.bird_bellbird, Resources.ID_NULL, "Korimako", "Bellbirds help regenerate the forest in two ways. They pollinate the flowers of many native trees and shrubs and, when they feed on the fruits from this pollination process, they disperse the seeds."))
            birds.add(BirdTemp("Black Stilt", R.drawable.bird_black_stilt, Resources.ID_NULL, "Kakī", ""))
            birds.add(BirdTemp("Blue Duck", R.drawable.bird_blue_duck, Resources.ID_NULL, "Whio", ""))
            birds.add(BirdTemp("Chatham Island Oystercatcher", R.drawable.bird_chatham_island_oystercatcher, Resources.ID_NULL, "Tōrea tai", "Since this bird has a long name, I need to test it with a fun fact."))
            birds.add(BirdTemp("Grey Warbler", R.drawable.bird_grey_warbler, Resources.ID_NULL, "Riroriro", ""))
            birds.add(BirdTemp("Huttons Shearwater", R.drawable.bird_huttons_shearwater, Resources.ID_NULL, "Kaikōura tītī", ""))
            birds.add(BirdTemp("Kākāpō", R.drawable.bird_kakapo, Resources.ID_NULL, "Kākāpō", ""))
            birds.add(BirdTemp("Kea", R.drawable.bird_kea, Resources.ID_NULL, "", ""))
            birds.add(BirdTemp("Kōkako", R.drawable.bird_kokako, Resources.ID_NULL, "Kōkako", ""))
            birds.add(BirdTemp("Morepork", R.drawable.bird_morepork, Resources.ID_NULL, "Ruru", ""))
            birds.add(BirdTemp("New Zealand Dotterel", R.drawable.bird_new_zealand_dotterel, Resources.ID_NULL, "Tūturiwhatu",  ""))
            birds.add(BirdTemp("New Zealand Falcon", R.drawable.bird_new_zealand_falcon, Resources.ID_NULL, "Kārearea", ""))
            birds.add(BirdTemp("New Zealand Pigeon", R.drawable.bird_new_zealand_pigeon, Resources.ID_NULL, "Kererū", ""))
            birds.add(BirdTemp("North Island Brown Kiwi", R.drawable.bird_north_island_brown_kiwi, Resources.ID_NULL, "Kiwi-nui", ""))
            birds.add(BirdTemp("Orange Fronted Parakeet", R.drawable.bird_orange_fronted_parakeet, Resources.ID_NULL, "Kākāriki karaka", ""))
            birds.add(BirdTemp("Paradise Duck", R.drawable.bird_paradise_duck, Resources.ID_NULL, "Pūtangitangi", "Paradise Ducks start breeding in their second or third year of life. They mate for life and return to the same nesting area every year."))
            birds.add(BirdTemp("Pūkeko", R.drawable.bird_pukeko, Resources.ID_NULL, "Pūkeko", ""))
            birds.add(BirdTemp("Red Crowned Parakeet", R.drawable.bird_red_crowned_parakeet, Resources.ID_NULL, "Kākāriki", ""))
            birds.add(BirdTemp("Rock Wren", R.drawable.bird_rock_wren, Resources.ID_NULL, "Pīwauwau", ""))
            birds.add(BirdTemp("Silvereye", R.drawable.bird_silvereye, Resources.ID_NULL, "Tauhou", ""))
            birds.add(BirdTemp("Stitchbird", R.drawable.bird_stitchbird, Resources.ID_NULL, "Hihi", ""))
            birds.add(BirdTemp("Takahē", R.drawable.bird_takahe, Resources.ID_NULL, "Takahē", ""))
            birds.add(BirdTemp("Tūī", R.drawable.bird_tui, Resources.ID_NULL, "Tūī", "Early European Settlers referred to the Tūī as the 'parson bird', presumably because the white tufts of feathers on the front of its neck resembled a priest's clerical collar."))
            birds.add(BirdTemp("Weka", R.drawable.bird_weka, Resources.ID_NULL, "", ""))
            birds.add(BirdTemp("Westland Black Petrel", R.drawable.bird_westland_black_petrel, Resources.ID_NULL, "Tāiko", ""))
            birds.add(BirdTemp("White Heron", R.drawable.bird_white_heron, Resources.ID_NULL, "Kōtuku", ""))
            birds.add(BirdTemp("Whitehead", R.drawable.bird_whitehead, Resources.ID_NULL, "Pōpokotea", ""))
            birds.add(BirdTemp("Yellow Eyed Penguin", R.drawable.bird_yellow_eyed_penguin, Resources.ID_NULL, "Hoiho", ""))
            birds.add(BirdTemp("Yellowhead", R.drawable.bird_yellowhead, Resources.ID_NULL, "Mohua", ""))
        }

        /**
         * Returns all birds which have the specified resource listed.
         *
         * @return A list of all birds which have the specified resource listed.
         */
        fun getBirdsWithResource(resourceType: QuestionType): ArrayList<BirdTemp> {
            var queriedBirds: ArrayList<BirdTemp> = ArrayList()

            for (bird: BirdTemp in birds) {
                if (resourceType == QuestionType.PHOTO) {
                    if (bird.getPhotoResourceId() != Resources.ID_NULL)
                        queriedBirds.add(bird)
                } else if (resourceType == QuestionType.SOUND) {
                    if (bird.getSongResourceID() != Resources.ID_NULL)
                        queriedBirds.add(bird)
                } else if (resourceType == QuestionType.MAORI || resourceType == QuestionType.ENGLISH) {
                    if (bird.getmaoriName() != "")
                        queriedBirds.add(bird)
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

            return BirdTemp("EMPTY_NAME", Resources.ID_NULL, Resources.ID_NULL,"EMPTY_MAORINAME", "EMPTY_FACT")
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
