FinalExamTamar - Movie List Android App

FinalExamT is an Android application that fetches and displays a list of movies from a remote API using Retrofit. Movie images are efficiently loaded with Picasso. Users can tap on any movie item to view detailed information on a separate screen (AboutActivity). The app also supports swipe gestures on movie items, allowing users to add movies to their favorites or delete them from the list, with immediate visual feedback via Snackbar. Additionally, Firebase is integrated to provide user authentication.

Technologies Used
Kotlin

AndroidX RecyclerView

Retrofit 2

Picasso

Firebase Authentication

Material Design Snackbar

ConstraintLayout & LinearLayout

Features
Dynamically fetches movie list data from a REST API.

Displays movie images and details in a RecyclerView.

Opens a detail screen with extended movie information when a movie item is tapped.

Supports swipe gestures to add movies to favorites or delete them from the list, providing smooth and immediate user feedback.

Uses Firebase Authentication to manage user login and secure access.

(Optional) Supports pagination for infinite scrolling through the movie list.

Setup Instructions
Clone the repository:

bash
Copy
Edit
git clone https://github.com/tak862/FinalExamTamar.git
Open the project in Android Studio.

Make sure the app has internet permission in the AndroidManifest.xml:

xml
Copy
Edit
<uses-permission android:name="android.permission.INTERNET" />
Verify all required dependencies (Retrofit, Picasso, Firebase Authentication, Material Components, etc.) are included in your build.gradle.

Confirm that the API base URL and endpoints are correctly set up in your Retrofit client and service interface.

Set up Firebase Authentication in your Firebase console and add the google-services.json file to your project.

Run the app on an emulator or physical device.

Notes
Movie image URLs must be valid and accessible over the internet to load properly.

If images fail to load, check internet permissions and URL correctness.

Swipe gestures allow users to quickly delete movies or add them to favorites, with instant feedback via Snackbar.

Firebase Authentication ensures secure user login and personalized experience.

Author: Tamar
Project: Final Exam - Android Application
