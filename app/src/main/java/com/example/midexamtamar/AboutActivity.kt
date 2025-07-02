package com.example.midexamtamar

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AboutActivity : AppCompatActivity() {
    private lateinit var btnAddToFavorites: Button
    private lateinit var movieName: String
    private lateinit var movieDescription: String
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.about_activity)

        btnAddToFavorites = findViewById(R.id.btnAddToFavorites)

        movieName = intent.getStringExtra("name") ?: ""
        movieDescription = intent.getStringExtra("description") ?: ""

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        btnAddToFavorites.setOnClickListener {
            addToFavorites(movieName, movieDescription)
        }
    }

    private fun addToFavorites(name: String, description: String) {
        val user = auth.currentUser
        if (user == null) {
            Toast.makeText(this, "გთხოვთ შეხვიდეთ ანგარიშში, რომ დაამატოთ ფავორიტებში", Toast.LENGTH_SHORT).show()
            return
        }

        val favoritesRef = database.reference.child("favorites").child(user.uid)
        val newFavKey = favoritesRef.push().key ?: return

        val favoriteMovie = mapOf(
            "name" to name,
            "description" to description
        )

        favoritesRef.child(newFavKey).setValue(favoriteMovie)
            .addOnSuccessListener {
                Toast.makeText(this, "$name დამატებულია ფავორიტებში", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "შეცდომა: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
