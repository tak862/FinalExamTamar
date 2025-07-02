package com.example.midexamtamar

import Movie
import MyDatabaseHelper
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.navigation.fragment.findNavController

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fabAdd: FloatingActionButton
    private lateinit var databaseHelper: MyDatabaseHelper
    private lateinit var adapter: ItemAdapter
    private var itemList = mutableListOf<Movie>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        databaseHelper = MyDatabaseHelper(requireContext())
        recyclerView = view.findViewById(R.id.recyclerView)
        fabAdd = view.findViewById(R.id.fabAdd)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = ItemAdapter(itemList)
        recyclerView.adapter = adapter

        loadItemsFromDatabase()

        fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_fragmentAddFilm)
        }
    }

    override fun onResume() {
        super.onResume()
        loadItemsFromDatabase()
    }

    private fun loadItemsFromDatabase() {
        itemList.clear()
        itemList.addAll(databaseHelper.getAllMovies())
        adapter.notifyDataSetChanged()
    }
}
