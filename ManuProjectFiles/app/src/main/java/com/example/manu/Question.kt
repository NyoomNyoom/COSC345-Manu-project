/* Jackson North 06/08/2022 */
package com.example.manu

class Question(private val birdIn: Bird, val questionType: Int) {
    var correctBirdObject = birdIn
    var optionList = mutableListOf<Bird>()
    var correctIndex = 0



    fun addOption (bird: Bird){
        optionList.add(bird)
    }

    //A function to shuffle the list of options and then re-finds the index of the correct option.
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

    fun getName(): String{
        return correctBirdObject.getBirdName()
    }

    fun getFile(): String{
        return correctBirdObject.getFile(questionType)
    }

    fun getAltName(): String{
        return correctBirdObject.getAltName()
    }

    override fun toString(): String {
        return "$correctBirdObject, $correctIndex"
    }
}