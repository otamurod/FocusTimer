package uz.otamurod.focustimer.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class SettingsTest {

    @Test
    fun hasPomodoroDefaults() {
        val settings = Settings()

        assertEquals(25, settings.focusMin)
        assertEquals(5, settings.shortBreakMin)
        assertEquals(15, settings.longBreakMin)
        assertEquals(4, settings.sessionsBeforeLong)
    }

    @Test
    fun copyChangesOneFieldAndKeepsTheRest() {
        val longer = Settings().copy(focusMin = 50)

        assertEquals(50, longer.focusMin)
        assertEquals(5, longer.shortBreakMin)
        assertEquals(15, longer.longBreakMin)
    }

    @Test
    fun equalByContentNotByReference() {
        assertEquals(
            Settings(),
            Settings()
        )
        assertNotEquals(
            Settings(),
            Settings(focusMin = 30)
        )
    }
}
