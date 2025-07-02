package com.example.midexamtamar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val logoutButton: Button = view.findViewById(R.id.buttonLogout)

        logoutButton.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
            Snackbar.make(view, "log out", Snackbar.LENGTH_SHORT).show()
        }

        return view
    }
}
