/**
 * @author Daniel Robinson
 */

package com.example.manu

/**
 * Defines the types of resources used as quiz questions.
 */
enum class QuestionType {
    /** The quiz will present a photo of a bird, and the player must identify it. */
    PHOTO,

    /** The quiz will play a sound of a bird call, and the player must identify the bird. */
    SOUND,

    /** The quiz will present the English name of a bird, and the player must select the Māori name for that bird. */
    ENGLISH,

    /** The quiz will present the Māori name of a bird, and the player must select the English name for that bird. */
    MAORI,

    /** The quiz will present all types of questions. */
    ALL
}