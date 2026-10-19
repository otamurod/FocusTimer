package uz.otamurod.focustimer

import android.app.Application
import android.util.Log

/**
 * One instance per app process. Android creates it before any Activity, which makes it the
 * place for one-time setup (later: notification channels, dependency container, crash reporting).
 *
 * It is registered in AndroidManifest.xml with android:name="uz.otamurod.focustimer.FocusTimerApp".
 */
class FocusTimerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "Application.onCreate: process started")
    }

    private companion object {
        const val TAG = "FocusTimer"
    }
}
