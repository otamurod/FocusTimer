package uz.otamurod.focustimer.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import uz.otamurod.focustimer.model.SessionMode
import uz.otamurod.focustimer.model.Settings
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.otamurod.focustimer.timer.TimerUiState
import kotlin.time.Duration.Companion.seconds

/**
 * Article 1, section 5: a first countdown with coroutines.
 *
 * Note: counting down by subtracting one every second is fine for learning,
 * but it drifts and stops if the app is killed. Article 5 replaces it with an
 * end timestamp, so the remaining time is always derived from the clock.
 */
class TimerViewModel(
    private val settings: Settings = Settings(),
) : ViewModel() {

    private val _state = MutableStateFlow(stateFor(SessionMode.Focus))
    val state: StateFlow<TimerUiState> = _state.asStateFlow()

    private var tickJob: Job? = null

    fun start() {
        if (tickJob?.isActive == true) {
            return        // already running
        }
        if (_state.value.isFinished) {
            reset()        // start over after a finished session
        }

        _state.update { it.copy(isRunning = true) }
        tickJob = viewModelScope.launch {
            while (_state.value.remainingSeconds > 0) {
                delay(1.seconds)
                _state.update {
                    it.copy(remainingSeconds = it.remainingSeconds - 1)
                }
            }
            _state.update {
                it.copy(isRunning = false)
            }
        }
    }

    fun pause() {
        tickJob?.cancel()
        tickJob = null
        _state.update {
            it.copy(isRunning = false)
        }
    }

    fun reset() {
        tickJob?.cancel()
        tickJob = null
        _state.value = stateFor(_state.value.mode)
    }

    /** Article 3: switching mode stops the timer and loads that mode's length. */
    fun selectMode(mode: SessionMode) {
        if (mode == _state.value.mode) {
            return
        }

        tickJob?.cancel()
        tickJob = null
        _state.value = stateFor(mode)
    }

    private fun stateFor(mode: SessionMode) = TimerUiState(
        totalSeconds = settings.minutesFor(mode) * 60,
        mode = mode,
    )
}
