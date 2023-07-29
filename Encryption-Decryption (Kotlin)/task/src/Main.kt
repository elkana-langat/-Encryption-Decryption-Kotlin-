package encryptdecrypt

import java.io.File

fun main(args: Array<String>) {
    // operation
    var operation = "enc"
    // message to encrypt
    var message = ""
    // key to use during encryption
    var key = 0
    // the filename of the input data
    var inputFilename = ""
    // the filename of the output data
    var outputFilename = ""
    // the algorithm to be used
    var algo = "shift"

    // length of the passed arguments
    val length = args.size
    // the input data to be processed
    var data = ""

    for (i in 0 until length step 2) {
        if (args[i] == "-mode") {
            operation = args[i + 1]
        } else if (args[i] == "-key") {
            key = args[i + 1].toInt()
        } else if (args[i] == "-data") {
            message = args[i + 1]
        } else if (args[i] == "-in") {
            inputFilename = args[i + 1]
        } else if (args[i] == "-out") {
            outputFilename = args[i + 1]
        } else if (args[i] == "-alg") {
            algo = args[i + 1]
        }
    }

    if (message != "" && inputFilename != "") {
        data = message
    } else if (message != "" && inputFilename == "") {
        data = message
    } else if (message == "" && inputFilename != "") {
        val file = File(inputFilename)
        if (file.exists()) { // checks if file exists
            val lines = file.readText()
            data = lines
        }
    }

    if (algo == "shift") {
        if (operation == "enc") {
            shiftEncryption(data, key, outputFilename)
        } else if (operation == "dec") {
            shiftDecryption(data, key, outputFilename)
        }
    } else if (algo == "unicode") {
        if (operation == "enc") {
            unicodeEncryption(data, key, outputFilename)
        } else if (operation == "dec") {
            unicodeDecryption(data, key, outputFilename)
        }
    }

}

// the encryption and decryption algorithm of unicode

fun unicodeEncryption(message: String, key: Int, fileName: String = "") {
    val result = StringBuilder()
    for (char in message) {
        if (char.isLetter()) {
            // get the ascii value of the character
            val asciiValue = char.code

            val offset = asciiValue + key

            result.append(offset.toChar())
        } else {
            result.append(char + key)
        }
    }

    // check if the filename was passed
    if (fileName != "") {
        File(fileName).writeText(result.toString())
    } else {
        println(result)
    }
}

fun unicodeDecryption(message: String, key: Int, fileName: String = "") {
    val result = StringBuilder()
    for (char in message) {
        if (char.isLetter()) {
            // get the ascii value of the character
            val asciiValue = char.code

            val offset = asciiValue - key

            result.append(offset.toChar())
        } else {
            result.append(char - key)
        }
    }

    // check if the filename was passed
    if (fileName != "") {
        File(fileName).writeText(result.toString())
    } else {
        println(result)
    }
}


// the encryption and decryption algorithm of shift

fun shiftEncryption(message: String, key: Int, fileName: String = "") {
    val result = StringBuilder()
    for (char in message) {
        if (char.isLetter()) {
            // get the ascii value of the character
            val asciiValue = char.code

            var offset = 0

            if (char.isLowerCase()) {
                offset = (asciiValue - 'a'.code + key) % 26 + 'a'.code
            } else if (char.isUpperCase()) {
                offset = (asciiValue - 'A'.code + key) % 26 + 'A'.code
            }

            result.append(offset.toChar())
        } else {
            result.append(char)
        }
    }

    // check if the filename was passed
    if (fileName != "") {
        File(fileName).writeText(result.toString())
    } else {
        println(result)
    }
}

fun shiftDecryption(message: String, key: Int, fileName: String = "") {
    val result = StringBuilder()
    for (char in message) {
        if (char.isLetter()) {
            // get the ascii value of the character
            val asciiValue = char.code

            var offset = 0

            if (char.isLowerCase()) {
                offset = ((asciiValue - 'a'.code - key + 26) % 26 + 'a'.code)
            } else if (char.isUpperCase()) {
                offset = ((asciiValue - 'A'.code - key + 26) % 26 + 'A'.code)
            }

            result.append(offset.toChar())
        } else {
            result.append(char)
        }
    }

    // check if the filename was passed
    if (fileName != "") {
        File(fileName).writeText(result.toString())
    } else {
        println(result)
    }
}
