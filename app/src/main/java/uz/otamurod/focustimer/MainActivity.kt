package uz.otamurod.focustimer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import uz.otamurod.focustimer.ui.TimerScreen
import uz.otamurod.focustimer.ui.theme.FocusTimerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FocusTimerTheme {
                TimerScreen()
            }
        }
    }
}
