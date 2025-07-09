package com.example.midexamtamar.Login

import android.os.Bundle
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

class LoginFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        auth = FirebaseAuth.getInstance()

        val emailEditText = view.findViewById<EditText>(R.id.editTextEmail)
        val passwordEditText = view.findViewById<EditText>(R.id.editTextPassword)
        val loginButton = view.findViewById<Button>(R.id.buttonLogin)
        val resetPasswordButton = view.findViewById<Button>(R.id.buttonResetPassword)
        val registerButton = view.findViewById<Button>(R.id.buttonRegister)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (email.isEmpty() || password.length < 6) {
                Toast.makeText(requireContext(), "შეიყვანე სწორი ელფოსტა და პაროლი (მინ. 6 სიმბოლო)", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(requireContext(), "წარმატებით შეხვედი!", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.homeFragment)
                    } else {
                        Toast.makeText(requireContext(), "შეცდომა: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        resetPasswordButton.setOnClickListener {

            findNavController().navigate(R.id.resetPasswordFragment)
        }

        registerButton.setOnClickListener {

            findNavController().navigate(R.id.registerFragment)
        }

        return view
    }
}
