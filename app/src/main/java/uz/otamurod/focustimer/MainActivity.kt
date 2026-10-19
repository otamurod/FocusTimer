package uz.otamurod.focustimer

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import uz.otamurod.focustimer.ui.TimerScreen
import uz.otamurod.focustimer.ui.theme.FocusTimerTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val savedInstanceState = if (savedInstanceState == null) {
            "null: fresh start"
        } else {
            "set: recreated"
        }

        log("onCreate (savedInstanceState is ${savedInstanceState})")

        enableEdgeToEdge()
        setContent {
            FocusTimerTheme {
                TimerScreen()
            }
        }
    }

    // The lifecycle logging below is for learning (Article 2). Run the app, rotate the
    // device, press Home, and watch Logcat filtered by the tag "FocusTimer".
    override fun onStart() {
        super.onStart()
        log("onStart: visible")
    }

    override fun onResume() {
        super.onResume()
        log("onResume: in the foreground, can receive input")
    }

    override fun onPause() {
        log("onPause: losing focus")
        super.onPause()
    }

    override fun onStop() {
        log("onStop: no longer visible")
        super.onStop()
    }

    override fun onDestroy() {
        log("onDestroy (isChangingConfigurations = $isChangingConfigurations)")
        super.onDestroy()
    }

    private fun log(message: String) = Log.d(TAG, "MainActivity.$message")

    private companion object {
        const val TAG = "FocusTimer"
    }
}
