package uz.otamurod.focustimer.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import uz.otamurod.focustimer.R
import uz.otamurod.focustimer.timer.TimerUiState
import uz.otamurod.focustimer.timer.formatTime
import uz.otamurod.focustimer.ui.theme.FocusTimerTheme

/** Stateful entry point: connects the ViewModel to the UI. */
@Composable
fun TimerScreen(
    modifier: Modifier = Modifier,
    viewModel: TimerViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    TimerContent(
        state = state,
        onStart = viewModel::start,
        onPause = viewModel::pause,
        onReset = viewModel::reset,
        modifier = modifier,
    )
}

/**
 * Stateless UI: it only draws the state it is given and reports events upward
 * through lambdas (Article 1, section 3). That makes it easy to preview and test.
 */
@Composable
fun TimerContent(
    state: TimerUiState,
    onStart: () -> Unit,
    onPause: () -> Unit,
    onReset: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = stringResource(R.string.title_focus),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )

            Spacer(Modifier.height(32.dp))

            Box(contentAlignment = Alignment.Center) {
                TimerRing(
                    progress = state.progress,
                    modifier = Modifier.size(280.dp),
                )
                Text(
                    text = formatTime(state.remainingSeconds),
                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }

            Spacer(Modifier.height(40.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                val buttonTextId = if (state.isRunning) {
                    R.string.action_pause
                } else {
                    R.string.action_start
                }

                Button(onClick = if (state.isRunning) onPause else onStart) {
                    Text(stringResource(buttonTextId))
                }

                Spacer(Modifier.width(16.dp))

                OutlinedButton(onClick = onReset) {
                    Text(stringResource(R.string.action_reset))
                }
            }
        }
    }
}

/** Circular progress ring drawn with Canvas. progress: 1f = full ring, 0f = empty. */
@Composable
fun TimerRing(
    progress: Float,
    modifier: Modifier = Modifier,
) {
    val trackColor = MaterialTheme.colorScheme.surfaceVariant
    val progressColor = MaterialTheme.colorScheme.primary

    Canvas(modifier = modifier) {
        val strokeWidth = 12.dp.toPx()
        val stroke = Stroke(width = strokeWidth, cap = StrokeCap.Round)
        val inset = strokeWidth / 2
        val arcTopLeft = Offset(inset, inset)
        val arcSize = Size(size.width - strokeWidth, size.height - strokeWidth)

        // Background track (full circle).
        drawArc(
            color = trackColor,
            startAngle = -90f,
            sweepAngle = 360f,
            useCenter = false,
            topLeft = arcTopLeft,
            size = arcSize,
            style = stroke,
        )
        // Remaining time, starting at 12 o'clock.
        drawArc(
            color = progressColor,
            startAngle = -90f,
            sweepAngle = 360f * progress.coerceIn(0f, 1f),
            useCenter = false,
            topLeft = arcTopLeft,
            size = arcSize,
            style = stroke,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TimerContentPreview() {
    FocusTimerTheme {
        TimerContent(
            state = TimerUiState(
                totalSeconds = 1500,
                remainingSeconds = 1125,
                isRunning = true
            ),
            onStart = {},
            onPause = {},
            onReset = {},
        )
    }
}
