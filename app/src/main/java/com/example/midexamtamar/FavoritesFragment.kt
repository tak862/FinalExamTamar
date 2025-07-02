package com.example.midexamtamar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.example.midexamtamar.R

class FavoritesFragment : Fragment() {

    private lateinit var favoritesTextView: TextView
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var favoritesRef: DatabaseReference
    private lateinit var favoritesListener: ValueEventListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)
        favoritesTextView = view.findViewById(R.id.favoritesTextView)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        return view
    }

    override fun onResume() {
        super.onResume()
        loadFavorites()
    }

    override fun onPause() {
        super.onPause()
        if (::favoritesRef.isInitialized && ::favoritesListener.isInitialized) {
            favoritesRef.removeEventListener(favoritesListener)
        }
    }


    private fun loadFavorites() {
        val user = auth.currentUser
        if (user == null) {
            favoritesTextView.text = "გთხოვთ შეხვიდეთ ანგარიშში, რომ ნახოთ ფავორიტები."
            return
        }

        favoritesRef = database.reference.child("favorites").child(user.uid)

        favoritesListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (!snapshot.exists()) {
                    favoritesTextView.text = "ფავორიტები არ არის დამატებული."
                    return
                }

                val sb = StringBuilder()
                for (child in snapshot.children) {
                    val name = child.child("name").getValue(String::class.java) ?: "Unknown"
                    val description = child.child("description").getValue(String::class.java) ?: ""
                    sb.append("ფილმი: $name\nაღწერა: $description\n\n")
                }
                favoritesTextView.text = sb.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                favoritesTextView.text = "მონაცემების წაკითხვა ვერ მოხერხდა: ${error.message}"
            }
        }

        favoritesRef.addValueEventListener(favoritesListener)
    }
}
