package com.example.manu

/**
 * Stores information about a bird.
 *
 * @param birdName The bird's primary name.
 * @param photoResourceId The photo's resource ID (a drawable resource).
 */
class Bird(birdName: String, photoResourceId: Int, soundResourceId: Int, englishNameImageResourceId: Int, maoriNameImageResourceId: Int, maoriName: String, endangermentStatusIn: String, funFact: String) {

    private val birdName: String = birdName
    private val photoResourceId: Int = photoResourceId
    private val soundResourceId: Int = soundResourceId
    private val englishNameImageResourceId: Int = englishNameImageResourceId
    private val maoriNameImageResourceId: Int = maoriNameImageResourceId
    private val maoriName: String = maoriName
    private val funFact = funFact
    private val endangermentStatus: String = endangermentStatusIn

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
     * @return The songs resource ID.
     */
    fun getSongResourceId(): Int {
        return soundResourceId
    }

    /**
     * Returns the resource ID of the image containing the bird's English name.
     *
     * @return The resource ID of the image containing the bird's English name.
     */
    fun getEnglishNameImageResourceId(): Int {
        return englishNameImageResourceId
    }

    /**
     * Returns the resource ID of the image containing the bird's Māori name.
     *
     * @return The resource ID of the image containing the bird's Māori name.
     */
    fun getMaoriNameImageResourceId(): Int {
        return maoriNameImageResourceId
    }

    /**
     * Returns the birds Maori name.
     *
     * @return The birds maori name.
     */
    fun getmaoriName(): String {
        return maoriName
    }

    /**
     * Returns the endangerment status of the bird. True if the bird is endangered, false otherwise.
     *
     * @return the endangerment status of the bird.
     */

    fun getEndangerment(): String{
        return endangermentStatus
    }



    /**
     * Our toString method for the bird temp, that just returns the values of bird separated by commas.
     *
     * @return bird values separated by commas.
     */
    override fun toString(): String {
        return "($birdName, $photoResourceId, $soundResourceId, $maoriName)"
    }

}