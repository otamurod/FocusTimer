package uz.otamurod.focustimer.model

/**
 * Timer settings. Article 1, section 2: an immutable data class with default values.
 * Change a value by creating a copy: settings.copy(focusMin = 50).
 */
data class Settings(
    val focusMin: Int = 25,
    val shortBreakMin: Int = 5,
    val longBreakMin: Int = 15,
    val sessionsBeforeLong: Int = 4,
)
