# Focus Timer

A small Pomodoro-style timer for Android, built step by step as the companion project for the
**Android Developer Roadmap** article series (Kotlin, Jetpack Compose, Material 3).

This repository is tagged at the end of every article, so you can check out the exact code a
given article describes.

| Tag | Article | What you get |
| --- | --- | --- |
| `article-01` | Kotlin you will use daily on Android | Data classes, null safety, lambdas, coroutines and a first working countdown |
| `article-02` | Anatomy of an Android project | Package `uz.otamurod.focustimer`, `Application` class, lifecycle logging, string resources with an Uzbek translation, debug/release build types |
| `article-03` | Compose basics | (coming) |

## What is in `article-01` (still here in `article-02`)

- `model/Settings.kt` - immutable data class with defaults (`copy()`, equality)
- `timer/TimerUiState.kt` - UI state as a data class
- `timer/TimeFormat.kt` - `formatTime(1500) -> "25:00"`
- `ui/TimerScreen.kt` - Compose UI: stateful `TimerScreen` + stateless `TimerContent` + a `Canvas` ring
- `ui/TimerViewModel.kt` - countdown with `viewModelScope`, `delay` and `StateFlow`
- `app/src/test/` - JUnit tests for the plain-Kotlin logic
- `playground/Article01.kt` - the article's mini-exercise solution (run it in the Kotlin Playground)

## Requirements

- A recent **Android Studio** that supports Android Gradle Plugin **9.4** (Studio tells you if it needs an update)
- **JDK 17** (the JDK bundled with Android Studio works)
- **Android SDK Platform 37** (Studio offers to install it on first sync)
- Gradle **9.6.0** is downloaded automatically by the wrapper

Versions are pinned in `gradle/libs.versions.toml`. They were checked against the official release
pages on 2026-10-16; check them again when you start, because libraries update often.

## Run it

1. Open the `FocusTimer` folder in Android Studio and let Gradle sync.
2. Pick an emulator or a connected device (Android 8.0 / API 26 or newer) and press **Run**.
3. Run the unit tests: `./gradlew test` (Windows: `gradlew.bat test`).

## What changed in `article-02`

- `FocusTimerApp.kt` - an `Application` subclass, registered in the manifest
- `MainActivity.kt` - lifecycle callbacks logged under the Logcat tag `FocusTimer`
- UI text moved to `res/values/strings.xml`, plus `res/values-uz/strings.xml` (Uzbek)
- `StringsParityTest` - fails the build if a translation misses a key
- `app/build.gradle.kts` - debug builds get the `.debug` application ID suffix

## Make it yours

Using this as a template for your own app? Rename the package before publishing anything
(Android Studio: right-click the package > Refactor > Rename, then update `namespace` and
`applicationId` in `app/build.gradle.kts`).

## Tag this version in git

```bash
git init
git add .
git commit -m "Article 01: Focus Timer with a first coroutine countdown"
git tag article-01
# ... later, after applying the Article 2 changes:
git commit -am "Article 02: project anatomy, lifecycle logging, string resources"
git tag article-02
git push --tags
```

## Roadmap for this app

- v1: configurable focus/break lengths, notification when a session ends, settings screen (DataStore)
- v1.1: session history and stats (Room)
- Later: home-screen widget, Play Store release

## License

MIT. Use it, learn from it, build your own version.
