package com.example.manu

/**
 * Stores information about a bird.
 *
 * @param birdName The bird's primary name.
 * @param photoResourceId The photo's resource ID (a drawable resource).
 */
class BirdTemp(birdName: String, photoResourceId: Int, funFactID: String/*, soundResourceId: Int, māoriName: String, englishName: String*/) {

    private val birdName: String = birdName
    private val photoResourceId: Int = photoResourceId
    //private val soundResourceId: Int = soundResourceId
    //val māoriName: String = māoriName
    //private val englishName: String = englishName

    private val funFact = funFactID

    /**
     * Returns the bird's name.
     *
     * @return The bird's name.
     */
    fun getBirdName(): String {
        return birdName
    }

    /**
     * Returns the photo's resource ID (a drawable resource).
     *
     * @return The photo's resource ID (a drawable resource).
     */
    fun getPhotoResourceId(): Int {
        return photoResourceId
    }

    /**
     * Returns the birds fun fact.
     *
     * @return the birds fun fact.
     */
    fun getFunFact(): String {
        return funFact
    }


    /**
     * Our toString method for the bird temp, that just returns the values of bird separated by commas.
     *
     * @return bird values separated by commas.
     */
    override fun toString(): String {
        return "$birdName, $photoResourceId"
    }

}