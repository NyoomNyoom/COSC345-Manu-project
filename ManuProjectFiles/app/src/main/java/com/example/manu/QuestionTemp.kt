/**
 * @author Daniel Robinson
 */

package com.example.manu

/**
 * A temporary question class.
 *
 * @param questionResourceID The question's resource ID.
 * @param options The possible answers.
 * @param answerIndex The index of the correct answer.
 */
class QuestionTemp(questionResourceID: Int, options: ArrayList<String>, answerIndex: Int) {

    private val questionResourceID: Int = questionResourceID
    private val options: ArrayList<String> = options
    private val answerIndex: Int = answerIndex

    /**
     * Returns the question's resource ID.
     *
     * @return The question's resource ID.
     */
    fun getQuestionResourceID(): Int {
        return questionResourceID
    }

    /**
     * Returns the possible answers.
     *
     * @return The possible answers.
     */
    fun getOptions(): ArrayList<String> {
        return options
    }

    /**
     * Returns the index of the correct answer.
     *
     * @return The index of the correct answer.
     */
    fun getAnswerIndex(): Int {
        return answerIndex
    }

}