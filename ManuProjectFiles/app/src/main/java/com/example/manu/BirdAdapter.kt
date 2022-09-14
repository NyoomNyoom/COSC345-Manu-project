/* Jackson North 03/08/2022 */
package com.example.manu

import android.content.Context
import  android.util.Log
import  kotlin.random.Random

/* Class takes an empty list in of type bird, and then the function create quiz to be called which
will create the quiz. */

/**
 * BirdAdapter takes an empty list of type Bird, and then creates a quiz and adds each question into the emtpy list
 */
class BirdAdapter(val questionList : MutableList<Question>) {
    private var questionAmount: Int = 0


    //Creates a quiz using the amount of questions and type that the creator wants.
    fun createQuiz(context: Context, questionAmount: Int, questionType: Int): MutableList<Question>{
        this.questionAmount = questionAmount

        if(questionType !in 1..4){
            Log.d("Question Error", "Please enter a valid question number(1-4).")
        }

        for(i in 0..questionAmount-1){
            val chosenBird = randomBird(questionType, context)

            questionList.add(Question(chosenBird, questionType))
            questionList[i].addOption(chosenBird)

            for(k in 0..3){
                val optionBird = randomBird(questionType, context)

                if(questionList[i].noDuplicateOptions(optionBird)){
                    questionList[i].addOption(optionBird)
                }
            }
            questionList[i].shuffleOptions()
        }

        return questionList
    }

    /**
     * Creates a quiz using forced values. For testing purposes only.
     *
     * @param questionAmount the amount of questions wanted.
     * @param questionType the question type of the quiz.
     * @param forceBird the bird that you are forcing the quiz to use.
     *
     * @return mutableList<Question> the list of all questions.
     */
    fun createQuizForced(questionAmount: Int, questionType: Int, forceBird: Int): MutableList<Question>{

       // if(questionType !in 1..4){
         //   Log.d("Question Error", "Please enter a valid question number(1-4).")
        //}

        for(i in 0..questionAmount-1) {
            questionList.add(Question(createBird(forceBird), questionType))
            //questionList[i],addOption()
            for (k in 0..3) {
                questionList[i].addOption(createBird(forceBird))
            }
            questionList[i].shuffleOptions()
        }

        return questionList
    }


    //function that finds a random bird for the question type.
    private fun randomBird(questionType : Int, context: Context): Bird {
        var birdNum = Random.nextInt(0,47)
        var tempBird = createBird(birdNum)
        lateinit var birdOut: Bird


        tempBird.updateValues(context)

        if (questionType == 0){
            return createBird(birdNum)
        }

        if(isValid(questionType,tempBird)){
            birdOut = tempBird
        }else{
            return randomBird(questionType, context)
        }

        return birdOut
    }


    //function checks if the bird that is given is a valid bird for the question.

    /**
     * A function to check if a bird is valid for a certain questionType.
     *
     * @param questionType the type of question the quiz is using.
     * @param bird the bird that is being checked
     *
     * @return whether the bird is vaild or not.
     */
    fun isValid(questionType: Int, bird: Bird): Boolean{
        var validBird= false
        if(questionType == 1){
            if(bird.getBirdPicture() != "null"){
                validBird = true
            }
        }else if(questionType == 2){
            if(bird.getBirdSong() != "null"){
                validBird = true
            }
        }

        return validBird
    }


    /**
     * A function to create a bird using a number.
     *
     * @param birdNumber the number of the bird that was chosen
     *
     * @return Bird, returns the created bird object.
     */
    private fun createBird(birdNumber : Int): Bird{
        return Bird(birdNumber)
    }


    /**
     * A function that overrides the default toString function.
     *
     * @return returns the correct birds toString value.    
     */
    override fun toString(): String {
        var correctBirdStr: String = ""

        questionList.forEach(){
            correctBirdStr += it.correctBirdObject.toString() +"\n"
        }

        return "$correctBirdStr"
    }

}