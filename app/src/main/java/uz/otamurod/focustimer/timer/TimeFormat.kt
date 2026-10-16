package uz.otamurod.focustimer.timer

import java.util.Locale

/** Formats seconds as MM:SS, for example 1500 -> "25:00". Negative values show as "00:00". */
fun formatTime(totalSeconds: Int): String {
    val safe = totalSeconds.coerceAtLeast(0)
    val minutes = safe / 60
    val seconds = safe % 60
    return "%02d:%02d".format(Locale.ROOT, minutes, seconds)
}
