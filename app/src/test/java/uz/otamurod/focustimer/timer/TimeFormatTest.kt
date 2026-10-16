package uz.otamurod.focustimer.timer

import org.junit.Assert.assertEquals
import org.junit.Test

class TimeFormatTest {

    @Test
    fun formatsZero() {
        assertEquals("00:00", formatTime(0))
    }

    @Test
    fun formatsSecondsOnly() {
        assertEquals("00:59", formatTime(59))
    }

    @Test
    fun formatsWholeMinute() {
        assertEquals("01:00", formatTime(60))
    }

    @Test
    fun formatsDefaultFocusSession() {
        assertEquals("25:00", formatTime(25 * 60))
    }

    @Test
    fun negativeValuesShowAsZero() {
        assertEquals("00:00", formatTime(-5))
    }
}
