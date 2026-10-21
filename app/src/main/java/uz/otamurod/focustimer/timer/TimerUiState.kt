package uz.otamurod.focustimer.timer

import uz.otamurod.focustimer.model.SessionMode

/**
 * Everything the timer screen needs to draw itself.
 * It is immutable, so StateFlow emits a new value on every change (Article 1, section 2).
 * Article 3 adds [mode]: one piece of state that drives both the title and the chips.
 */
data class TimerUiState(
    val totalSeconds: Int,
    val remainingSeconds: Int = totalSeconds,
    val isRunning: Boolean = false,
    val mode: SessionMode = SessionMode.Focus,
) {
    /** Fraction of time left, from 1f (full) down to 0f (finished). */
    val progress: Float
        get() = if (totalSeconds <= 0) 0f else remainingSeconds.toFloat() / totalSeconds

    val isFinished: Boolean
        get() = remainingSeconds <= 0
}
