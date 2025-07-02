package com.example.midexamtamar

import Movie
import MyDatabaseHelper
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class FragmentAddFilm : Fragment(R.layout.fragment_add_film) {

    interface OnFilmAddedListener {
        fun onFilmAdded()
    }

    var listener: OnFilmAddedListener? = null

    private lateinit var editName: EditText
    private lateinit var editDescription: EditText
    private lateinit var editReleaseDate: EditText
    private lateinit var editTime: EditText
    private lateinit var editCountry: EditText
    private lateinit var editImageUrl: EditText
    private lateinit var buttonAddFilm: Button

    private lateinit var databaseHelper: MyDatabaseHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editName = view.findViewById(R.id.editName)
        editDescription = view.findViewById(R.id.editDescription)
        editReleaseDate = view.findViewById(R.id.editReleaseDate)
        editTime = view.findViewById(R.id.editTime)
        editCountry = view.findViewById(R.id.editCountry)
        editImageUrl = view.findViewById(R.id.editImageUrl)
        buttonAddFilm = view.findViewById(R.id.buttonAddFilm)

        databaseHelper = MyDatabaseHelper(requireContext())

        buttonAddFilm.setOnClickListener {
            addFilm()
        }
    }

    private fun addFilm() {
        val name = editName.text.toString().trim()
        val description = editDescription.text.toString().trim()
        val releaseDate = editReleaseDate.text.toString().toIntOrNull() ?: 0
        val time = editTime.text.toString().toDoubleOrNull() ?: 0.0
        val country = editCountry.text.toString().trim()
        val imageUrl = editImageUrl.text.toString().trim()

        if (name.isEmpty() || description.isEmpty()) {
            Toast.makeText(requireContext(), "Name and Description are required", Toast.LENGTH_SHORT).show()
            return
        }

        val movie = Movie(0, name, description, releaseDate, time, country, imageUrl)
        val success = databaseHelper.addMovie(movie)

        if (success) {
            Toast.makeText(requireContext(), "Film added successfully", Toast.LENGTH_SHORT).show()
            clearFields()
            listener?.onFilmAdded()

        } else {
            Toast.makeText(requireContext(), "Failed to add film", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearFields() {
        editName.text.clear()
        editDescription.text.clear()
        editReleaseDate.text.clear()
        editTime.text.clear()
        editCountry.text.clear()
        editImageUrl.text.clear()
    }
}
