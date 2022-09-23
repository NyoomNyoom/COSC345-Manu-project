package com.example.manu

class Stats(questionTypeIn: QuestionType, numQuestionsIn: Int, numRightIn: Int, totalPlayedIn: Int) {
    private val questionType = questionTypeIn
    private val numQuestions = numQuestionsIn
    private var numRight = numRightIn
    private var totalPlayed = totalPlayedIn

    fun updateNumRight(valueIn: Int) {
        numRight += valueIn
    }

    fun updateTotalPlayed(valueIn: Int){
        totalPlayed += valueIn
    }

    fun getQuestionType(): QuestionType{
        return questionType
    }

    fun getQuestionLength(): Int {
        return numQuestions
    }

    fun getNumRight(): Int{
        return numRight
    }

    fun getTotalPlayed(): Int{
        return totalPlayed
    }

    fun getAverage(): Int{
        if (totalPlayed == 0) {
            return 0
        }
        return numRight / totalPlayed
    }

    fun resetValues(){
        totalPlayed = 0
        numRight = 0
    }

    fun getTotalQuizzesPlayed(): Int{
        if (numQuestions == 0) {
            return 0
        }
        return totalPlayed % numQuestions
    }


    override fun toString(): String {
        return "$questionType,$numQuestions,$numRight,$totalPlayed\n"
    }


}
