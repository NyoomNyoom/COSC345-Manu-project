package com.example.manu

/**
 * Stores information about a bird.
 *
 * @param birdName The bird's primary name.
 * @param photoResourceId The photo's resource ID (a drawable resource).
 */
class BirdTemp(birdName: String, photoResourceId: Int, soundResourceId: Int, maoriName: String, /*endangermentStatusIn: Boolean, */ funFact: String) {

    private val birdName: String = birdName
    private val photoResourceId: Int = photoResourceId
    private val soundResourceId: Int = soundResourceId
    private val maoriName: String = maoriName
    private val funFact = funFact
    //private val endangermentStatus = endangermentStatusIn

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
     * Returns the songs resource ID.
     *
     * @return the songs resource ID.
     */
    fun getSongResourceId(): Int {
        return soundResourceId
    }

    /**
     * Returns the birds Maori name.
     *
     * @return the birds maori name.
     */
    fun getmaoriName(): String {
        return maoriName
    }

    /**
     * Returns the endangerment status of the bird. True if the bird is endangered, false otherwise.
     *
     * @return the endangerment status of the bird.
     */
    /*
    fun getEndangerment(): Boolean{
        return endangermentStatus
    }
    */


    /**
     * Our toString method for the bird temp, that just returns the values of bird separated by commas.
     *
     * @return bird values separated by commas.
     */
    override fun toString(): String {
        return "($birdName, $photoResourceId, $soundResourceId, $maoriName)"
    }

}