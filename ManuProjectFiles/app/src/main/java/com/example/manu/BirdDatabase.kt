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

        private lateinit var birds: ArrayList<Bird>

        /**
         * Compiles the bird database by creating and adding each individual bird.
         */
        fun compileDatabase() {
            birds = ArrayList()
            birds.add(Bird("Auckland Island Teal", R.drawable.bird_auckland_island_teal, R.raw.aucklandislandteal, R.drawable.english_auckland_island_teal, R.drawable.maori_auckland_island_teal, "Tētē kākāriki","Nationally Vulnerable", "There is an island in the Auckland Islands group called 'Disappointment Island'."))
            birds.add(Bird("Australasian Bittern", R.drawable.bird_australasian_bittern, R.raw.australasianbittern, R.drawable.english_australasian_bittern, R.drawable.maori_australasian_bittern, "Matuku-hūrepo","Nationally Critical", "These globally endangered birds are important to Māori. They appear in Māori legends, stories, and even place names."))
            birds.add(Bird("Australasian Crested Grebe", R.drawable.bird_australasian_crested_grebe, R.raw.acrestedgrebe, Resources.ID_NULL, Resources.ID_NULL, "","", "While there are fewer than 1000 in Aotearoa, there used to be around 200 in the 1980s."))
            birds.add(Bird("Bellbird", R.drawable.bird_bellbird, R.raw.bellbird, R.drawable.english_bellbird, R.drawable.maori_bellbird, "Korimako","Not Threatened", "Bellbirds help regenerate the forest in two ways. They pollinate the flowers of many native trees and shrubs and, when they feed on the fruits from this pollination process, they disperse the seeds."))
            birds.add(Bird("Black Stilt", R.drawable.bird_black_stilt, R.raw.blackstilt, R.drawable.english_black_stilt, R.drawable.maori_black_stilt, "Kakī", "Nationally Critical", "The black stilt is one of the world's rarest wading birds. By 1981 its numbers had dropped to only 23 birds in the wild. By August 2000 the numbers had recovered to only 48 birds in the wild, with another 20 birds in captivity."))
            birds.add(Bird("Blue Duck", R.drawable.bird_blue_duck, R.raw.blueduck, R.drawable.english_blue_duck, R.drawable.maori_blue_duck, "Whio", "Nationally Vulnerable", "Blue Ducks are one of only four duck species in the world who live all year round on fast-flowing rivers. From this harsh and stony environement, the Blue Duck has evolved to have the tip of their bill rubberised to avoid damage against river rocks."))
            birds.add(Bird("Chatham Island Oystercatcher", R.drawable.bird_chatham_island_oystercatcher, R.raw.chathamoystercatcher, R.drawable.english_chatham_island_oystercatcher, R.drawable.maori_chatham_island_oystercatcher, "Tōrea tai","Nationally Critical", "The Chatham Island Oystercatcher is a threatened species found only on the Chatham Islands, 800 km to the east of mainland New Zealand. When found they are typically in pairs spending the year defending their coastal territories."))
            birds.add(Bird("Grey Warbler", R.drawable.bird_grey_warbler, R.raw.greywarbler, R.drawable.english_grey_warbler, R.drawable.maori_grey_warbler, "Riroriro", "Not Threatened", "You are more likely to hear a Grey Warbler than see one as they are incredibly tiny birds weighting in at only 6 grams."))
            birds.add(Bird("Huttons Shearwater", R.drawable.bird_huttons_shearwater, R.raw.huttonsshearwater, R.drawable.english_huttons_shearwater, R.drawable.maori_huttons_shearwater, "Kaikōura tītī", "Nationally Vulnerable", "The Hutton’s Shearwater was long known to Māori, providing a major sustainable source of protein to Ngāti Kuri, Tangata Whenua of the area. “Tītī” is the Māori name for a number of different shearwater species, particularly during the downy chick stage."))
            birds.add(Bird("Kākāpō", R.drawable.bird_kakapo, R.raw.kakapo, Resources.ID_NULL, Resources.ID_NULL, "", "Nationally Critical", "It's the world's only flightless parrot. Kakapos can't fly. They use their short wings for balance and support rather than flapping. Additionally Kakapo's are incredibly friendly and were kept as pets by both the Maori and early European settlers."))
            birds.add(Bird("Kea", R.drawable.bird_kea, R.raw.kea, Resources.ID_NULL, Resources.ID_NULL, "", "Nationally Endangered", "Kea are very intelligent birds with great puzzle solving skills. Kea are often a great threat to tourists as they pull of any rubber on rental cars they can get at."))
            birds.add(Bird("Kōkako", R.drawable.bird_kokako, R.raw.kokako, Resources.ID_NULL, Resources.ID_NULL, "", "Nationally Increasing", "The wings of the Kokako are small and weak for its size which limits flight and gliding to short periods of time. Kokakos therefore prefer to run and jump from tree to tree using their strong and long black legs. "))
            birds.add(Bird("Morepork", R.drawable.bird_morepork, R.raw.morepork, R.drawable.english_morepork, R.drawable.maori_morepork, "Ruru", "Not Threatened", "The bird that stands out from the rest due to its unique characteristics is the most widespread owl species, Ruru Morepork. They are generally considered a symbol of wisdom, intelligence, and regal silence in some traditions."))
            birds.add(Bird("New Zealand Dotterel", R.drawable.bird_new_zealand_dotterel, R.raw.nzdotterel, R.drawable.english_new_zealand_dotterel, R.drawable.maori_new_zealand_dotterel, "Tūturiwhatu",  "Recovering", "Due to common human interaction with Dotterel nests they have learnt to try distract intruders from their nest by performing ‘rat runs’ or pretending they’re injured. "))
            birds.add(Bird("New Zealand Falcon", R.drawable.bird_new_zealand_falcon, R.raw.nzfalcon, R.drawable.english_new_zealand_falcon, R.drawable.maori_new_zealand_falcon, "Kārearea", "Recovering", "The New Zealand falcon is capable of flying at speeds over 100 km/h, and can catch prey larger than itself. They hunt live prey, mainly by watching from a vantage point and making a fast direct flying attack and either striking or grasping the prey with their feet which are equipped with sharp talons."))
            birds.add(Bird("New Zealand Pigeon", R.drawable.bird_new_zealand_pigeon, R.raw.nzpigeon, R.drawable.english_new_zealand_pigeon, R.drawable.maori_new_zealand_pigeon, "Kererū", "Not Threatened", "They play a very important role in the survival of many of New Zealand’s native trees.  Due to extinctions of other large birds, they are now the only native bird that is able to eat the large fruit of many native trees, including taraire and karaka.  This means they are the only birds able to spread the seeds of these native trees."))
            birds.add(Bird("North Island Brown Kiwi", R.drawable.bird_north_island_brown_kiwi, Resources.ID_NULL, R.drawable.english_north_island_brown_kiwi, R.drawable.maori_north_island_brown_kiwi, "Kiwi-nui", "Not Threatened", "Brown kiwi are flightless and nocturnal. During the day they rest in a burrow, hollow tree or log, or under thick vegetation and emerge shortly after nightfall."))
            birds.add(Bird("Orange Fronted Parakeet", R.drawable.bird_orange_fronted_parakeet, R.raw.orangefrontedparakeet, R.drawable.english_orange_fronted_parakeet, R.drawable.maori_orange_fronted_parakeet, "Kākāriki karaka", "Nationally Critical", "The orange-fronted parakeet typically feeds in the canopy of NZ beech trees, but will also forage in low vegetation and on the ground. They are typically observed feeding in flocks of mixed species, eating various seeds, beech flowers, buds and invertebrates."))
            birds.add(Bird("Paradise Duck", R.drawable.bird_paradise_duck, R.raw.paradiseduck, R.drawable.english_paradise_duck, R.drawable.maori_paradise_duck, "Pūtangitangi","Not Threatened", "Paradise Ducks start breeding in their second or third year of life. They mate for life and return to the same nesting area every year."))
            birds.add(Bird("Pūkeko", R.drawable.bird_pukeko, Resources.ID_NULL, Resources.ID_NULL, Resources.ID_NULL, "", "Not Threatened", "Pukeko are capable fliers, as indicated by their presence on many offshore islands around New Zealand. However, their flying does appear awkward and laboured at times, especially when taking off and landing, and, given a choice, they seem to prefer to walk or run than fly."))
            birds.add(Bird("Red Crowned Parakeet", R.drawable.bird_red_crowned_parakeet, R.raw.redcrownedparakeet, R.drawable.english_red_crowned_parakeet, R.drawable.maori_red_crowned_parakeet, "Kākāriki", "Relict", "Red-crowned parakeets typically nest in tree holes, particularly in old trees. Eight subspecies of red-crowned parakeets are known, though two are extinct (Lord Howe and Macquarie Island subspecies). Four are found in New Zealand."))
            birds.add(Bird("Rock Wren", R.drawable.bird_rock_wren, R.raw.rockwren, R.drawable.english_rock_wren, R.drawable.maori_rock_wren, "Pīwauwau", "Nationally Endangered", "Rock wrens are our only true alpine bird. It is unknown how they survive the harsh climate above the tree line all year round, but it is likely they continue to forage on rocky bluffs where snow has not collected and amongst large boulder fields."))
            birds.add(Bird("Silvereye", R.drawable.bird_silvereye, R.raw.silvereye, R.drawable.english_silvereye, R.drawable.maori_silvereye, "Tauhou", "Not Threatened", "The silvereye was first recorded in New Zealand in 1832 and since there is no evidence that it was artificially introduced, it is classified as a native species. Its Māori name, tauhou, means 'stranger' or more literally 'new arrival'."))
            birds.add(Bird("Stitchbird", R.drawable.bird_stitchbird, R.raw.stitchbird, R.drawable.english_stitchbird, R.drawable.maori_stitchbird, "Hihi", "Nationally Vulnerable", "Stitchbirds have a complex social structure that includes a variable mating system and a multitude of interactions between the members of a population. For example, several males and females have been reported visiting others nests; chicks from various nests get together in crèches after fledging and perform behaviours that have been interpreted as play; adult males form groups with juveniles during the winter time where perhaps hierarchies are being formed."))
            birds.add(Bird("Takahē", R.drawable.bird_takahe, R.raw.takahe, Resources.ID_NULL, Resources.ID_NULL, "", "Nationally Vulnerable", "These birds were considered to be extinct in the late 19th century until they were rediscovered in 1948. After that, they have been protected and conserved under the Takahe Recovery Programme"))
            birds.add(Bird("Tūī", R.drawable.bird_tui, R.raw.tui, Resources.ID_NULL, Resources.ID_NULL, "","Not Threatened", "Early European Settlers referred to the Tūī as the 'parson bird', presumably because the white tufts of feathers on the front of its neck resembled a priest's clerical collar."))
            birds.add(Bird("Weka", R.drawable.bird_weka, R.raw.weka, Resources.ID_NULL, Resources.ID_NULL, "", "Not Threatened", "Weka’s predatory behaviour makes them unwelcome on pest-free sanctuaries regardless that they are often naturally occurring as they are a threat to burrowing seabirds, ground-nesting birds and reptiles. "))
            birds.add(Bird("Westland Black Petrel", R.drawable.bird_westland_black_petrel, R.raw.westlandblackpetrel, R.drawable.english_westland_black_petral, R.drawable.maori_westland_black_petral, "Tāiko", "Naturally Uncommon", "The Westland petrel differs from other petrels that breed in New Zealand in being a winter breeder, with its only breeding location confined to an 8 km stretch of coastal forest in the foothills of the Paparoa range near Punakaiki."))
            birds.add(Bird("White Heron", R.drawable.bird_white_heron, R.raw.whiteheron, R.drawable.english_white_heron, R.drawable.maori_white_heron, "Kōtuku", "Nationally Critical", "During breeding seasons, they grow long, loose breeding plumes from its back and wings. The normally yellow bill turns dull black, and the facial skin between the eye and bill brightens to a bluish-green. Additionally the White heron have an elaborate courtship displays. Males build small platforms from which they advertise themselves to females."))
            birds.add(Bird("Whitehead", R.drawable.bird_whitehead, R.raw.whitehead, R.drawable.english_whitehead, R.drawable.maori_whitehead, "Pōpokotea", "Not Threatened", "Whiteheads are the only North Island host for the parasitic long-tailed cuckoo, a species regarded as ‘at risk’. The long-tailed cuckoo sneakily lays her eggs in the whitehead’s nest, fooling the whitehead into incubating the egg and raising the chick as her own."))
            birds.add(Bird("Yellow Eyed Penguin", R.drawable.bird_yellow_eyed_penguin, R.raw.yelloweyedpenguin, R.drawable.english_yellow_eyed_penguin, R.drawable.maori_yellow_eyed_penguin, "Hoiho", "Not Threatened", "Yellow-eyed Penguins are a bit of an oddity of the penguin world when it comes to breeding habits. Most penguin species form densely packed colonies. Yellow-eyed Penguins on the other hand will not build their nests within visual range of each other."))
            birds.add(Bird("Yellowhead", R.drawable.bird_yellowhead, R.raw.yellowhead, R.drawable.english_yellowhead, R.drawable.maori_yellowhead, "Mohua", "Declining", "Yellowheads are non migratory birds meaning they never leave the forests that they live in"))
        }

        /**
         * Returns all birds which have the specified resource listed.
         *
         * @return A list of all birds which have the specified resource listed.
         */
        fun getBirdsWithResource(resourceType: QuestionType): ArrayList<Bird> {
            var queriedBirds: ArrayList<Bird> = ArrayList()

            for (bird: Bird in birds) {
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
        private fun getBirdUsingPhotoResourceId(resourceId: Int): Bird {
            for (bird in birds) {
                if (bird.getPhotoResourceId() == resourceId)
                    return bird
            }

            return Bird("EMPTY_NAME", Resources.ID_NULL, Resources.ID_NULL, Resources.ID_NULL, Resources.ID_NULL, "EMPTY_MAORINAME","EMPTY_STATUS", "EMPTY_FACT")        }

        /**
         * A function to return the list of birds
         *
         * @return getBirdList The list of birds.
         */
        fun getBirdList(): ArrayList<Bird> {
            return birds
        }

        /*fun getIndexUsingName(name: String): Int {
            var matchingBird: Bird = null
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
                val questions: ArrayList<Question> = QuizGenerator.generateQuiz(QuestionType.PHOTO,
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
