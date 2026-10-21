package uz.otamurod.focustimer.ui.playground

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.otamurod.focustimer.ui.theme.FocusTimerTheme

// Article 3 playground. Nothing here is used by the real app screen.
// To try it on a device, temporarily replace TimerScreen() in MainActivity with
// ComposeBasicsDemo(), run the app, tap the buttons, then rotate the device.

/** Stateless: draws the count it is given and reports taps upward. */
@Composable
fun ClickCounterContent(
    label: String,
    clicks: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    // SideEffect runs after every successful recomposition of this function.
    SideEffect {
        Log.d("FocusTimer", "$label recomposed, clicks=$clicks")
    }

    Button(onClick = onClick, modifier = modifier) {
        Text("$label: $clicks")
    }
}

/** Stateful, with plain remember: the count is lost when the Activity is recreated. */
@Composable
fun RememberCounter(modifier: Modifier = Modifier) {
    var clicks by remember { mutableIntStateOf(0) }

    ClickCounterContent(
        label = "remember",
        clicks = clicks,
        onClick = { clicks++ },
        modifier = modifier
    )
}

/** Stateful, with rememberSaveable: the count also survives rotation. */
@Composable
fun RememberSaveableCounter(modifier: Modifier = Modifier) {
    var clicks by rememberSaveable { mutableIntStateOf(0) }
    ClickCounterContent(
        label = "rememberSaveable",
        clicks = clicks,
        onClick = { clicks++ },
        modifier = modifier
    )
}

/** The same two modifiers in a different order give a different result. */
@Composable
fun ModifierOrderDemo(modifier: Modifier = Modifier) {
    val color = MaterialTheme.colorScheme.primaryContainer
    Row(
        modifier = modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        Text(
            text = "padding, then background",
            modifier = Modifier
                .padding(16.dp)
                .background(color),
        )
        
        Text(
            text = "background, then padding",
            modifier = Modifier
                .background(color)
                .padding(16.dp),
        )
    }
}

@Composable
fun ComposeBasicsDemo(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        RememberCounter()
        RememberSaveableCounter()
        ModifierOrderDemo()
    }
}

@Preview(showBackground = true)
@Composable
private fun ComposeBasicsDemoPreview() {
    FocusTimerTheme { ComposeBasicsDemo() }
}
