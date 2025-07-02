package com.example.midexamtamar.Login

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.midexamtamar.R
import com.google.firebase.auth.FirebaseAuth

class ResetPasswordFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_reset_password, container, false)

        auth = FirebaseAuth.getInstance()

        val emailEditText = view.findViewById<EditText>(R.id.editTextTextEmailAddress2)
        val resetButton = view.findViewById<Button>(R.id.button3)

        resetButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()

            if (email.isEmpty()) {
                Toast.makeText(requireContext(), "გთხოვთ, შეიყვანეთ ელფოსტა", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(requireContext(), "გთხოვთ, შეიყვანეთ ვალიდური ელფოსტა", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(requireContext(), "პაროლის აღდგენის ლინკი გაიგზავნა ელფოსტაზე", Toast.LENGTH_LONG).show()
                        // სურვილის შემთხვევაში უკან დაბრუნება
                        findNavController().popBackStack()
                    } else {
                        Toast.makeText(requireContext(), "შეცდომა: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        return view
    }
}
