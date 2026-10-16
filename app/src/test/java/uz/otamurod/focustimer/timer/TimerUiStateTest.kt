package uz.otamurod.focustimer.timer

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class TimerUiStateTest {

    @Test
    fun newStateStartsFullAndStopped() {
        val state = TimerUiState(totalSeconds = 1500)

        assertEquals(1500, state.remainingSeconds)
        assertEquals(1f, state.progress, 0.0001f)
        assertFalse(state.isRunning)
        assertFalse(state.isFinished)
    }

    @Test
    fun progressIsFractionOfTimeLeft() {
        val state = TimerUiState(
            totalSeconds = 100,
            remainingSeconds = 25
        )

        assertEquals(0.25f, state.progress, 0.0001f)
    }

    @Test
    fun finishedWhenNoTimeLeft() {
        val state = TimerUiState(
            totalSeconds = 100,
            remainingSeconds = 0
        )

        assertTrue(state.isFinished)
        assertEquals(0f, state.progress, 0.0001f)
    }

    @Test
    fun zeroTotalDoesNotDivideByZero() {
        val state = TimerUiState(totalSeconds = 0)

        assertEquals(0f, state.progress, 0.0001f)
    }

    @Test
    fun copyChangesOnlyTheGivenField() {
        val state = TimerUiState(totalSeconds = 100)
        val running = state.copy(isRunning = true)

        assertTrue(running.isRunning)
        assertEquals(state.totalSeconds, running.totalSeconds)
        assertEquals(state.remainingSeconds, running.remainingSeconds)
    }
}
