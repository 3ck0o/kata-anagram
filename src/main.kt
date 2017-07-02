import java.io.File
import java.util.*
import kotlin.system.measureTimeMillis

val file = "wordlist.txt"

fun main(args: Array<String>) {
    val elapsedTime = measureTimeMillis {
        val reference = args[0]
        val charCount = countChars(reference)
        File(file).bufferedReader(Charsets.ISO_8859_1).useLines {
            lines ->
            lines.forEach { displayAnagram(charCount, reference.length, it) }
        }
    }
    println("Finished in $elapsedTime ms")
}

fun displayAnagram(charCount: Map<Char, Int>, referenceLength: Int, toCompare: String) {
    if(isAnagram(charCount, referenceLength, toCompare))
        println(toCompare)
}

fun isAnagram(charCount: Map<Char, Int>, referenceLength: Int, toCompare: String): Boolean {
    if (toCompare.length != referenceLength)
        return false
    for ((char, count) in charCount) {
        if (toCompare.toLowerCase().count { comparedChar -> comparedChar == char } != count)
            return false
    }
    return true
}

fun countChars(reference: String): Map<Char, Int> {
    val preparedWord: MutableMap<Char, Int> = HashMap()
    reference.toLowerCase().forEach { char -> handleCharCount(preparedWord, char) }
    return preparedWord
}

fun handleCharCount(anagramData: MutableMap<Char, Int>, char: Char) {
    if (anagramData[char] == null) {
        anagramData.put(char, 0)
    }
    anagramData.put(char, anagramData[char]!! + 1)
}