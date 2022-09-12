/* Jackson North 03/08/2022 */
package com.example.manu

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.nio.Buffer
import kotlin.math.min

/**
 * A class which holds all the values needed to keep track of a bird for the given question. It takes in a random
 * number from BirdAdapter.kt and then searches through a list of birds to find that number and then checks
 * if the bird is valid for the quiz, and if it is, will update the values of the bird so we have all the needed
 * values that were stored in the information list.
 *
 * @param birdNumIn the index of a bird in the list of birds we have on file.
 */
data class Bird(val birdNumIn: Int){
    private var nameOfBird = ""
    private var birdFilePic = ""
    private var birdFileSong = ""
    private var alternativeName = ""
    private var birdInfoFile = ""
    private var birdNum = birdNumIn


    /**
     * A function that searches the bird-data.csv file which is a list of birds to find the bird that was
     * inputted into the class constructor and then add the values of the bird to the private variables.
     */
    fun updateValues(context: Context){
        val inputStream = context.assets.open("bird-data.csv")
        val minput = InputStreamReader(inputStream, "UTF-8")
        val reader = BufferedReader(minput)

        var birdFound = false
        val birdNumber = birdNum - 1
        val birdNumStr = birdNumber.toString()

        var birdInfo: MutableList<String> = mutableListOf()

        while(!birdFound){
            var lines = reader.readLine()
            var line = lines.split(",")

            if(line[0] == birdNumStr){
                lines.split(",").forEach{
                    birdInfo.add(it)
                }
                birdFound = true
            }
        }

        nameOfBird = birdInfo[1]
        birdFilePic = birdInfo[2]
        birdFileSong = birdInfo[3]
        alternativeName = birdInfo[4]
        birdInfoFile = birdInfo[5]

    }

    /**
     * A function return the filename of the picture for the bird
     *
     * @return birdFilePic the filename of the bird.
     */
    fun getBirdPicture(): String{
        return birdFilePic
    }

    /**
     * A function return the filename of the song for the bird
     *
     * @return birdFileSong the filename of the bird.
     */
    fun getBirdSong(): String{
        return birdFileSong
    }

    /**
     * A function that returns the name of a bird from the private variable.
     *
     * @return nameOfBird: String, the name of the bird.
     */
    fun getBirdName(): String{
        return nameOfBird
    }

    /**
     * A function that returns the file of a bird from a private variable depending on which question is asked.
     *
     * @return output: String, the correct file for the bird.
     */
    fun getFile(questionType: Int): String{
        var output = ""

        //adds the filetype to the end of the string
        if(questionType == 1){
            output = "$birdFilePic"
        }else if(questionType == 2){
            output = "$birdFileSong"
        }

        return output
    }

    /**
     * A function that returns the alternative name of a bird from the private variable.
     *
     * @return alternativeName: String, the name of the bird.
     */
    fun getAltName(): String{
        return alternativeName
    }

    /**
     * A function to get the file with the fun fact about the bird on it.
     *
     * @return birdInfoFile: String, the filename of the file that contains the birds fun fact.
     */
    fun getInfoFile(): String{
        return birdInfoFile
    }

    /**
     * A function that returns the index that the bird is in the list.
     *
     * @return birdNum: Int, the index of the bird.
     */
    fun getBirdNum(): Int{
        return birdNum
    }

    /**
     * Overriding the default to string method. Which returns all the birds data in a line separated by
     * commas.
     *
     * @return nameOfBird, AlternativeName, birdNum, birdFilePic, birdFileSong, birdInfoFile.
     */
    override fun toString(): String {
        return "$nameOfBird, $alternativeName, $birdNum, $birdFilePic, $birdFileSong, $birdInfoFile"
    }

    /**
     * A function which sets the picture file of the bird to a new name which is parsed into the function.
     */
    fun setPicFileName(newName: String){
        birdFilePic = newName
    }

    /**
     * A function which sets the sound file of the bird to a new name which is parsed into the function.
     */
    fun setSongFileName(newName: String){
        birdFileSong = newName
    }

}