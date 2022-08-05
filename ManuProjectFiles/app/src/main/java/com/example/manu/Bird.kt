/* Jackson North 03/08/2022 */
package com.example.manu



data class Bird(val Name: String, val fileName: String, val altName: String, val infoFile: String) {
    private var nameOfBird = Name
    //private var birdFile
    private var alternativeName = altName
    private var birdInfoFile = infoFile

    fun birdName(): String{
        return nameOfBird
    }

    /*
    fun getFile(fileType : String): File{
        try {
            val inputStream = assets.open("birdTest.txt")
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer).toString()

            string = String(buffer)
        }catch (e: IOException){
            e.printStackTrace()
        }

    }
    */


    fun getAltName(){

    }

    fun getInfoFile(){

    }
}