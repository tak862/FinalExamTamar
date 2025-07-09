FinalExameTamar - Android Movie App
Description:
FinalExameTamar is an Android application for managing movies. It features user authentication with Firebase, local data storage with SQLite, smooth navigation with Navigation Component and BottomNavigationView, background tasks via WorkManager, and rich UI animations.
![Screenshot 2025-07-09 141635](https://github.com/user-attachments/assets/39df5380-3558-4947-b782-f19ff6603a7a)
![Screenshot 2025-07-09 141643](https://github.com/user-attachments/assets/a116dd88-57f2-4dca-9c07-f81ad2c8534c)
![Screenshot 2025-07-09 141659](https://github.com/user-attachments/assets/85cf1fd3-3413-4a7d-a251-c3583a4898ae)
![Screenshot 2025-07-09 141707](https://github.com/user-attachments/assets/7e075e64-ce77-415c-85e1-a0f03526fed1)
![Screenshot 2025-07-09 141649](https://github.com/user-attachments/assets/8d2aa8c4-4869-47dc-b3d6-6fe7b4d25e8c)

Features
User Authentication:
Sign in, register, and reset password with Firebase Authentication.

Movie List:
Displays a list of movies using RecyclerView with images, release year, duration, and country.

Add Movie:
Add new movies with details such as name, description, release year, duration, country, and image URL.

Favorites Management:
Mark movies as favorites and view them in a dedicated favorites screen.

Navigation:
Single Activity architecture using Navigation Component combined with BottomNavigationView for easy navigation between Home, Favorites, and Profile.

Animations:
Smooth transitions with slide, fade, and scale animations on fragment navigation.

Local Data Storage:
Uses SQLiteOpenHelper for storing favorite movies locally (Room is not used).

Background Tasks:
Scheduled background notifications handled by WorkManager.

Technologies Used
Kotlin

Android Jetpack: Navigation Component, ViewBinding, RecyclerView, WorkManager

Firebase Authentication

SQLiteOpenHelper for local database

Glide for image loading

Retrofit & OkHttp for network calls

Material Design Components (BottomNavigationView, FloatingActionButton)

Setup Instructions
Clone the repository:

bash
Copy code
git clone https://github.com/tak862/FinalExameTamar.git
Open the project in Android Studio.

Add your Firebase configuration file (google-services.json).

Configure authentication and database rules on Firebase console as needed.

Build and run the app on your device or emulator.

Usage
Use the login screen to sign in or register a new account.

Browse movies on the Home screen.

Add new movies using the Add Movie screen.

Mark movies as favorites and view them on the Favorites screen.

Manage your account in the Profile screen including logout.

Notes
Local data persistence uses SQLiteOpenHelper, not Room.

The app uses Single Activity Architecture for better performance and maintainability.

Navigation between screens is handled by the Navigation Component with animated transitions.

Author
Tako Chinchaladze
