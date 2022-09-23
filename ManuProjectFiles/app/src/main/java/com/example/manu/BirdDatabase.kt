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
            birds.add(BirdTemp("Auckland Island Teal", R.drawable.bird_auckland_island_teal, R.raw.aucklandislandteal, R.drawable.english_auckland_island_teal, R.drawable.maori_auckland_island_teal, "Tētē kākāriki","Nationally Vulnerable", "There is an island in the Auckland Islands group called 'Disappointment Island'."))
            birds.add(BirdTemp("Australasian Bittern", R.drawable.bird_australasian_bittern, R.raw.australasianbittern, R.drawable.english_australasian_bittern, R.drawable.maori_australasian_bittern, "Matuku-hūrepo","Nationally Critical", "These globally endangered birds are important to Māori. They appear in Māori legends, stories, and even place names."))
            birds.add(BirdTemp("Australasian Crested Grebe", R.drawable.bird_australasian_crested_grebe, R.raw.acrestedgrebe, Resources.ID_NULL, Resources.ID_NULL, "","", "While there are fewer than 1000 in Aotearoa, there used to be around 200 in the 1980s."))
            birds.add(BirdTemp("Bellbird", R.drawable.bird_bellbird, R.raw.bellbird, R.drawable.english_bellbird, R.drawable.maori_bellbird, "Korimako","Not Threatened", "Bellbirds help regenerate the forest in two ways. They pollinate the flowers of many native trees and shrubs and, when they feed on the fruits from this pollination process, they disperse the seeds."))
            birds.add(BirdTemp("Black Stilt", R.drawable.bird_black_stilt, R.raw.blackstilt, R.drawable.english_black_stilt, R.drawable.maori_black_stilt, "Kakī", "Nationally Critical", ""))
            birds.add(BirdTemp("Blue Duck", R.drawable.bird_blue_duck, R.raw.blueduck, R.drawable.english_blue_duck, R.drawable.maori_blue_duck, "Whio", "Nationally Vulnerable", ""))
            birds.add(BirdTemp("Chatham Island Oystercatcher", R.drawable.bird_chatham_island_oystercatcher, R.raw.chathamoystercatcher, R.drawable.english_chatham_island_oystercatcher, R.drawable.maori_chatham_island_oystercatcher, "Tōrea tai","Nationally Critical", "Since this bird has a long name, I need to test it with a fun fact."))
            birds.add(BirdTemp("Grey Warbler", R.drawable.bird_grey_warbler, R.raw.greywarbler, R.drawable.english_grey_warbler, R.drawable.maori_grey_warbler, "Riroriro", "Not Threatened", ""))
            birds.add(BirdTemp("Huttons Shearwater", R.drawable.bird_huttons_shearwater, R.raw.huttonsshearwater, R.drawable.english_huttons_shearwater, R.drawable.maori_huttons_shearwater, "Kaikōura tītī", "Nationally Vulnerable", ""))
            birds.add(BirdTemp("Kākāpō", R.drawable.bird_kakapo, R.raw.kakapo, Resources.ID_NULL, Resources.ID_NULL, "", "Endemic", ""))
            birds.add(BirdTemp("Kea", R.drawable.bird_kea, R.raw.kea, Resources.ID_NULL, Resources.ID_NULL, "", "Nationally Endangered", ""))
            birds.add(BirdTemp("Kōkako", R.drawable.bird_kokako, R.raw.kokako, Resources.ID_NULL, Resources.ID_NULL, "", "Nationally Increasing", ""))
            birds.add(BirdTemp("Morepork", R.drawable.bird_morepork, R.raw.morepork, R.drawable.english_morepork, R.drawable.maori_morepork, "Ruru", "Not Threatened", ""))
            birds.add(BirdTemp("New Zealand Dotterel", R.drawable.bird_new_zealand_dotterel, R.raw.nzdotterel, R.drawable.english_new_zealand_dotterel, R.drawable.maori_new_zealand_dotterel, "Tūturiwhatu",  "Recovering", ""))
            birds.add(BirdTemp("New Zealand Falcon", R.drawable.bird_new_zealand_falcon, R.raw.nzfalcon, R.drawable.english_new_zealand_falcon, R.drawable.maori_new_zealand_falcon, "Kārearea", "Recovering", ""))
            birds.add(BirdTemp("New Zealand Pigeon", R.drawable.bird_new_zealand_pigeon, R.raw.nzpigeon, R.drawable.english_new_zealand_pigeon, R.drawable.maori_new_zealand_pigeon, "Kererū", "Not Threatened", ""))
            birds.add(BirdTemp("North Island Brown Kiwi", R.drawable.bird_north_island_brown_kiwi, Resources.ID_NULL, R.drawable.english_north_island_brown_kiwi, R.drawable.maori_north_island_brown_kiwi, "Kiwi-nui", "Not Threatened", ""))
            birds.add(BirdTemp("Orange Fronted Parakeet", R.drawable.bird_orange_fronted_parakeet, R.raw.orangefrontedparakeet, R.drawable.english_orange_fronted_parakeet, R.drawable.maori_orange_fronted_parakeet, "Kākāriki karaka", "Nationally Critical", ""))
            birds.add(BirdTemp("Paradise Duck", R.drawable.bird_paradise_duck, R.raw.paradiseduck, R.drawable.english_paradise_duck, R.drawable.maori_paradise_duck, "Pūtangitangi","Not Threatened", "Paradise Ducks start breeding in their second or third year of life. They mate for life and return to the same nesting area every year."))
            birds.add(BirdTemp("Pūkeko", R.drawable.bird_pukeko, Resources.ID_NULL, Resources.ID_NULL, Resources.ID_NULL, "", "Not Threatened", ""))
            birds.add(BirdTemp("Red Crowned Parakeet", R.drawable.bird_red_crowned_parakeet, R.raw.redcrownedparakeet, R.drawable.english_red_crowned_parakeet, R.drawable.maori_red_crowned_parakeet, "Kākāriki", "Relict", ""))
            birds.add(BirdTemp("Rock Wren", R.drawable.bird_rock_wren, R.raw.rockwren, R.drawable.english_rock_wren, R.drawable.maori_rock_wren, "Pīwauwau", "Nationally Endangered", ""))
            birds.add(BirdTemp("Silvereye", R.drawable.bird_silvereye, R.raw.silvereye, R.drawable.english_silvereye, R.drawable.maori_silvereye, "Tauhou", "Not Threatened", ""))
            birds.add(BirdTemp("Stitchbird", R.drawable.bird_stitchbird, R.raw.stitchbird, R.drawable.english_stitchbird, R.drawable.maori_stitchbird, "Hihi", "Nationally Vulnerable", ""))
            birds.add(BirdTemp("Takahē", R.drawable.bird_takahe, R.raw.takahe, Resources.ID_NULL, Resources.ID_NULL, "", "Nationally Vulnerable", ""))
            birds.add(BirdTemp("Tūī", R.drawable.bird_tui, R.raw.tui, Resources.ID_NULL, Resources.ID_NULL, "","Not Threatened", "Early European Settlers referred to the Tūī as the 'parson bird', presumably because the white tufts of feathers on the front of its neck resembled a priest's clerical collar."))
            birds.add(BirdTemp("Weka", R.drawable.bird_weka, R.raw.weka, Resources.ID_NULL, Resources.ID_NULL, "", "Not Threatened", ""))
            birds.add(BirdTemp("Westland Black Petrel", R.drawable.bird_westland_black_petrel, R.raw.westlandblackpetrel, R.drawable.english_westland_black_petral, R.drawable.maori_westland_black_petral, "Tāiko", "Naturally Uncommon", ""))
            birds.add(BirdTemp("White Heron", R.drawable.bird_white_heron, R.raw.whiteheron, R.drawable.english_white_heron, R.drawable.maori_white_heron, "Kōtuku", "Nationally Critical", ""))
            birds.add(BirdTemp("Whitehead", R.drawable.bird_whitehead, R.raw.whitehead, R.drawable.english_whitehead, R.drawable.maori_whitehead, "Pōpokotea", "Not Threatened", ""))
            birds.add(BirdTemp("Yellow Eyed Penguin", R.drawable.bird_yellow_eyed_penguin, R.raw.yelloweyedpenguin, R.drawable.english_yellow_eyed_penguin, R.drawable.maori_yellow_eyed_penguin, "Hoiho", "Not Threatened", ""))
            birds.add(BirdTemp("Yellowhead", R.drawable.bird_yellowhead, R.raw.yellowhead, R.drawable.english_yellowhead, R.drawable.maori_yellowhead, "Mohua", "Declining", ""))
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
                    if (bird.getSongResourceId() != Resources.ID_NULL)
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
                else if (bird.getSongResourceId() == resourceId)
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

            return BirdTemp("EMPTY_NAME", Resources.ID_NULL, Resources.ID_NULL, Resources.ID_NULL, Resources.ID_NULL, "EMPTY_MAORINAME","EMPTY_STATUS", "EMPTY_FACT")        }

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
