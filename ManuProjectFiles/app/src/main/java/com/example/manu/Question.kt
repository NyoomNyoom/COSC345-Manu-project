/* Jackson North 06/08/2022 */
package com.example.manu

class Question(val birdIn: Bird) {
    var birdObject = birdIn
    var optionList = mutableListOf<Bird>()
    var correctIndex = 1

    fun getOptions(optionListIn: MutableList<Bird>){

    }
}