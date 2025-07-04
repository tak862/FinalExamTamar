FinalExamTamar - Movie App with Firebase and SQLite

FinalExamTamar is an Android application developed using Kotlin and based on a single-activity architecture with Jetpack’s Navigation Component. The app features user authentication with Firebase, local data persistence using SQLite (via SQLiteOpenHelper), and structured navigation via BottomNavigationView. It allows users to register, log in, reset their passwords, and manage a personal movie list, including favorites and custom-added films.

Features

Firebase Authentication: Sign Up, Sign In, Password Reset, Logout

SQLite local database support via SQLiteOpenHelper

Bottom navigation between Home, Favorites, Profile

Add movie functionality with local persistence

Profile screen with logout option

Fragment-to-fragment data passing using Safe Args

Material Design UI components with responsive layouts

ViewBinding and ConstraintLayout for UI management

Jetpack Navigation Component for structured navigation

Technologies Used

Kotlin

Firebase Authentication

SQLiteOpenHelper

Jetpack Navigation Component

ViewBinding

BottomNavigationView

ConstraintLayout

Material Design

Setup Instructions

Open the project in Android Studio.

Add your Firebase google-services.json file to the app/ directory.

Enable Firebase Authentication (Email/Password) in the Firebase Console.

Make sure the app has internet permission in the AndroidManifest.xml:
<uses-permission android:name="android.permission.INTERNET" />

Sync Gradle and run the app on an emulator or real device.

Notes

All authentication features (register, login, reset) are handled via Firebase.

Movie data added or favorited by the user is stored locally using SQLite.

The app uses a single activity (MainActivity) with fragments for each screen.

Navigation and UI management follow best practices using Jetpack and Material Design.

Network access is required for Firebase features.

Author: Tamar
Project: Final Exam – Android Movie App
