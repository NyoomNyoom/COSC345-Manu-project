/**
 * @author Daniel Robinson
 */

package com.example.manu

/**
 * Represents a question and the required data.
 *
 * @param questionResourceId The question's resource ID.
 * @param options The possible answers.
 * @param answerIndex The index of the correct answer.
 */
class Question(questionResourceId: Int, options: ArrayList<String>, answerIndex: Int) {

    private val questionResourceId: Int = questionResourceId
    private val options: ArrayList<String> = options
    private val answerIndex: Int = answerIndex

    /**
     * Returns the question's resource ID.
     *
     * @return The question's resource ID.
     */
    fun getQuestionResourceId(): Int {
        return questionResourceId
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

    /**
     * Overrides the default toString() method.
     *
     * @return A string representation of the object.
     */
    override fun toString(): String {
        return "$questionResourceId, $options, $answerIndex"
    }

}