/* Jackson North 03/08/2022 */
package com.example.manu

/*
* Need to change the constructor to be a constructor with only a number inputed which refers to a
* number from a csv file that has all the information currently in the folder name and then saves
* the information to a string which will then be able to be called out by the functions in
* birdAdapter.
*
* NEED TO HAVE A LIST THAT HAS 4 BIRDS (1 CORRECT 3 WRONG) WITH THE CORRECT INDEX AS A SECOND NUMBER
* RETURNED.
*/

data class Bird(val Name: String, val fileName: String, val altName: String, val infoFile: String) {
    /*private var nameOfBird = Name
    private var birdFile = fileName
    private var alternativeName = altName
    private var birdInfoFile = infoFile

    fun birdName(): String{
        return nameOfBird
    }
/*
    fun getFile(fileType : String): String{

    }
*/
    fun getAltName(){

    }

    fun getInfoFile(){

    }*/
}