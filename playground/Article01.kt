// Article 1 - mini-exercise, worked solution.
// Paste this file into https://play.kotlinlang.org and run it.
// (It is outside the Gradle source sets, so Android Studio does not compile it.)

import kotlinx.coroutines.*

data class Session(val id: Int, val minutes: Int, val type: String)

fun describe(session: Session?): String =
    session?.let { "#${it.id}: ${it.type} for ${it.minutes} min" } ?: "No session"

fun main() = runBlocking {
    val sessions = listOf(
        Session(1, 25, "focus"),
        Session(2, 5, "break"),
        Session(3, 25, "focus"),
        Session(4, 5, "break"),
        Session(5, 25, "focus"),
    )

    val focusMinutes = sessions.filter { it.type == "focus" }.sumOf { it.minutes }
    println("Focus minutes: $focusMinutes")      // 75

    println(describe(sessions.first()))          // #1: focus for 25 min
    println(describe(null))                      // No session

    val report = StringBuilder().apply {
        sessions.forEach { appendLine(describe(it)) }
    }
    print(report)

    for (i in 3 downTo 1) {
        println("Starting in $i")
        delay(1_000)
    }
    println("Go!")
}
