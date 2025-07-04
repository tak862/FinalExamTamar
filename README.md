FinalExamTamar - Movie List Android App with Firebase

FinalExamTamar is an Android application developed using Kotlin. The app includes user authentication using Firebase and provides a structured navigation flow with multiple fragments. Users can register, log in, reset their password, and navigate between different parts of the app through a bottom navigation bar. Main features include a movie list on the home screen, a favorites section, a profile page, and the ability to add a new movie.

Technologies Used

Kotlin
Firebase Authentication
Jetpack Navigation Component
ViewBinding
ConstraintLayout
BottomNavigationView
Material Design Components

Features

User login, registration, and logout using Firebase Authentication

Password reset functionality via email

Bottom navigation bar with Home, Favorites, and Profile sections

Ability to add a new movie through a dedicated screen

Navigation between fragments using Jetpack's Navigation Component

Bottom navigation hidden on login/register/reset screens for clarity

Responsive and modern UI with Material Design principles

Setup Instructions

Clone the repository and open it in Android Studio

Add your google-services.json file to the app/ directory

Make sure Internet permission is declared in AndroidManifest.xml

Enable Firebase Email/Password Authentication in the Firebase Console

Sync Gradle and run the app on an emulator or real device with internet access

Notes

Firebase Authentication handles secure user sessions.
Bottom navigation is contextually shown or hidden depending on the active screen.
All navigation is handled within a single activity using a NavHostFragment.

Author: Tamar
Project: Final Exam – Android Movie App with Firebase Authentication
