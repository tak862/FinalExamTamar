package com.example.midexamtamar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FavoritesViewModel : ViewModel() {

    // ვინაიდან აღარ არის DB, ვაგროვებთ სიას MutableLiveData-ში
    private val _favoritesList = MutableLiveData<List<String>>(emptyList())
    val favoritesList: LiveData<List<String>> get() = _favoritesList

    // ფავორიტის დამატება
    fun addFavorite(movieName: String) {
        val currentList = _favoritesList.value ?: emptyList()
        if (!currentList.contains(movieName)) {
            _favoritesList.value = currentList + movieName
        }
    }

    // ფავორიტის წაშლა
    fun removeFavorite(movieName: String) {
        val currentList = _favoritesList.value ?: emptyList()
        _favoritesList.value = currentList.filter { it != movieName }
    }
}
