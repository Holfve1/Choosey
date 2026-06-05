# Choosey

Choosey is an Android choice generator built with Kotlin, Jetpack Compose, and SQLite via Room. It helps you pick from custom or pre-seeded categories when you are stuck deciding between options.

## Features

- Generate a random choice from the currently selected category
- Manage multiple categories of decisions
- Add your own categories and options
- Toggle which options are included before making a choice
- Includes built-in starter categories such as:
  - Takeaway
  - Movie Genre
  - Date Night
  - Yes / No
- Helpful in-app help screen
- Animated splash screen and playful UI built with Jetpack Compose
- Local persistence using Room so choices and categories are stored on device

## Tech Stack

- Kotlin
- Jetpack Compose
- Android Navigation Compose
- Room
- SQLite
- Coroutines
- Gradle Kotlin DSL
- KSP

## Project Structure

- `app/` – Android application module
- `app/src/main/java/com/example/choosey/` – UI, navigation, view model, repository wiring, and app logic
- `app/src/main/java/com/example/choosey/data/db/` – Room database and DAOs
- `app/src/main/java/com/example/choosey/data/repo/` – Repository layer
- `app/src/main/res/` – App resources, theme files, images, and XML config

## Requirements

- Android Studio with Android SDK installed
- Minimum SDK: 24
- Target SDK: 36
- Java 11

## Getting Started

1. Clone the repository:

   ```bash
   git clone https://github.com/Holfve1/Choosey.git
   ```

2. Open the project in Android Studio.
3. Let Gradle sync the project dependencies.
4. Run the app on an emulator or Android device.

## How It Works

Choosey stores categories and selections locally using a Room database named `choosey.db`.

On first launch, the app seeds the database with a few starter categories and options. Users can then:

- choose a category
- add new categories
- add or remove options
- toggle which options are active
- press the main button to get a random result from the selected items

## Default Seeded Categories

The app currently seeds these categories on first launch:

- Takeaway
- Movie Genre
- Date Night
- Yes / No

## Notes

- This project uses a single Android app module.
- Data is stored locally on device.
- The UI is built with Compose and uses a custom theme and animated elements.

## Future Improvements

Some possible future enhancements:

- Edit existing option names
- Export or import categories
- Better duplicate handling and validation feedback in the UI
- Tests for repository and database behavior
- Screenshots or demo GIFs in this README

## License

No license has been added to this repository yet.
