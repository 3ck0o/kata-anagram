import java.io.File
import java.util.*
import kotlin.system.measureTimeMillis

val file = "wordlist.txt"

fun main(args: Array<String>) {
    val elapsedTime = measureTimeMillis {
        val anagrams: MutableMap<String, MutableList<String>> = HashMap()
        File(file).bufferedReader(Charsets.ISO_8859_1).forEachLine { line -> updateAnagramList(anagrams, line.toLowerCase()) }
        anagrams.forEach(::displayAnagram)
    }
    println("Needed $elapsedTime ms")
}

fun displayAnagram(anagram: Map.Entry<String, MutableList<String>>) {
    if (anagram.value.size > 1)
        println(anagram.value)
}

fun updateAnagramList(anagrams: MutableMap<String, MutableList<String>>, line: String) {
    val key = getKey(line)
    if (anagrams[key] == null)
        anagrams[key] = mutableListOf(line)
    else
        anagrams[key]!!.add(line)
}

fun getKey(word: String): String {
    val charArray = word.toCharArray()
    Arrays.sort(charArray)
    return String(charArray)
}