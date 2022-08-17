/* Jackson North 06/08/2022 */
package com.example.manu

/**
 * A class that handles the list of options for a question and holds references to the bird types.
 *
 * @param birdIn is the bird that is the correct answer for the question.
 * @param questionType is the type of question the question is (IE 1 for picture only) so we can grab the correct file.
 */
data class Question(private val birdIn: Bird, val questionType: Int) {

    var correctBirdObject = birdIn
    var optionList = mutableListOf<Bird>()
    var correctIndex = 0


    /**
     * A function that adds an option to the option list. This is called from the BirdAdapter class and feeds 3
     * random birds into the option list.
     *
     * @param bird the random Bird object to be added to the option MutableList.
     */

    fun addOption (bird: Bird){
        optionList.add(bird)

    }

    /**
     * A function that shuffles the option list and then re finds the correct index of the bird that is the
     * answer for the question.
     */
    fun shuffleOptions (){
        optionList.shuffle()
        var i = 0
        optionList.forEach{
            if(it.getBirdNum() == correctBirdObject.getBirdNum()){
                correctIndex = i
            }
            i++
        }
    }

    /**
     * A function which returns the name of the correct bird.
     */
    fun getName(): String{
        return correctBirdObject.getBirdName()
    }

    /**
     * A function which returns the filename of the correct bird corresponding to the correct question type.
     */
    fun getFile(): String{
        return correctBirdObject.getFile(questionType)
    }

    /**
     * A function which the alternative name for the correct bird.
     */
    fun getAltName(): String{
        return correctBirdObject.getAltName()
    }

    /**
     * A function that overrides the default to string method.
     */
    override fun toString(): String {
        return "$correctBirdObject, $correctIndex"
    }

}