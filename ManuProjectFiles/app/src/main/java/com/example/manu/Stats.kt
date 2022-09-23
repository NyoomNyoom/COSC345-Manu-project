/**
 * @author Jackson North
 */

package com.example.manu

class Stats(questionTypeIn: QuestionType, numQuestionsIn: Int, numRightIn: Int, totalPlayedIn: Int) {
    private val questionType = questionTypeIn
    private val numQuestions = numQuestionsIn
    private var numRight = numRightIn
    private var totalPlayed = totalPlayedIn

    /**
     * @param valueIn an int of questsions right
     *
     * Updates the quantity of questions the user got right
     */
    fun updateNumRight(valueIn: Int) {
        numRight += valueIn
    }

    /**
     * @param valueIn the quantity of games played
     *
     * Updates the quantity of games played by the user
     */
    fun updateTotalPlayed(valueIn: Int){
        totalPlayed += valueIn
    }

    /**
     * @return questionType
     *
     * The type of quiz requested
     */
    fun getQuestionType(): QuestionType{
        return questionType
    }

    /**
     * @return int numQuestions
     *
     * Returns the quantity of questions a user has played
     */
    fun getQuestionLength(): Int {
        return numQuestions
    }

    /**
     * @return int numRight
     *
     * Returns the quantity of questions a user has gotten right
     */
    fun getNumRight(): Int{
        return numRight
    }

    /**
     * @return int totalPlayed
     *
     * Get quantity of matches a user has played
     */
    fun getTotalPlayed(): Int{
        return totalPlayed
    }

    /**
     * @return int average
     *
     * Calculates and returns average score of user
     */
    fun getAverage(): Int{
        if (totalPlayed == 0) {
            return 0
        }
        return numRight / totalPlayed
    }

    /**
     * Resets the games played and recorded score averages back to 0
     */
    fun resetValues(){
        totalPlayed = 0
        numRight = 0
    }

    /**
     * @return quantity of quizzes played by the user
     */
    fun getTotalQuizzesPlayed(): Int{
        if (numQuestions == 0) {
            return 0
        }
        return totalPlayed % numQuestions
    }

    /**
     * @return String the string form of stats
     */
    override fun toString(): String {
        return "$questionType,$numQuestions,$numRight,$totalPlayed\n"
    }

}
