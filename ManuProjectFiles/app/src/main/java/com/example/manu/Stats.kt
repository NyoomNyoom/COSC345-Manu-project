/**
 * @author Jackson North
 */

package com.example.manu

/**
 * A class that makes an object of type Stats.
 *
 * @author Jackson North
 *
 * @param questionTypeIn the questionType the stats are for.
 * @param numQuestionsIn the length of the quiz.
 * @param numRightIn the total number of questions that the user got right.
 * @param totalPlayedIn the total number of questions played.
 */
class Stats(questionTypeIn: QuestionType, numQuestionsIn: Int, numRightIn: Int, totalPlayedIn: Int) {
    private val questionType = questionTypeIn
    private val numQuestions = 10
    private var numRight = numRightIn
    private var totalPlayed = totalPlayedIn

    /**
     * A function that updates the number of correct questions. It takes in a value then increments
     * numRight by that much.
     *
     * @param valueIn the amount the user wants to increment the correct number of questions by.
     */
    fun updateNumRight(valueIn: Int) {
        numRight += valueIn
    }

    /**
     * A function to update the amount of questions played. The function takes in a value
     * then increments totalPlayed by that much.
     *
     * @param valueIn the quantity of games played.
     */
    fun updateTotalPlayed(valueIn: Int){
        totalPlayed += valueIn
    }

    /**
     * A function to get the questionType of the object.
     *
     * @return questionType The type of quiz requested.
     */
    fun getQuestionType(): QuestionType{
        return questionType
    }

    /**
     * A function to get the quiz length of the object.
     *
     * @return numQuestions The length of the quiz the object represents.
     */
    fun getQuestionLength(): Int {
        return numQuestions
    }

    /**
     * A function to get the number of right questions.
     *
     * @return numRight the total number of questions the user got right for the question.
     */
    fun getNumRight(): Int{
        return numRight
    }

    /**
     * A function to get the total number of questions played.
     *
     * @return totalPlayed the total number of questions the user played.
     */
    fun getTotalPlayed(): Int{
        return totalPlayed
    }

    /**
     * A function to calculate the average score.
     *
     * @return numRight/totalPlayed The average score.
     */
    fun getAverage(): Int{
        if (totalPlayed == 0) {
            return 0
        }

        return (numRight / totalPlayed)
    }

    /**
     * Resets the totalPlayed and numRight back to 0
     */
    fun resetValues(){
        totalPlayed = 0
        numRight = 0
    }

    /**
     *A function to get the total number of quizzes played.
     *
     * @return totalPlayed%numQuestions The total number of questions.
     */
    fun getTotalQuizzesPlayed(): Int{
        if (numQuestions == 0) {
            return 0
        }
        return (totalPlayed / numQuestions)
    }

    /**
     * Rewritten the toString method to return a list of the variables.
     *
     * @return String the string form of stats
     */
    override fun toString(): String {
        return "$questionType,$numQuestions,$numRight,$totalPlayed\n"
    }

}
