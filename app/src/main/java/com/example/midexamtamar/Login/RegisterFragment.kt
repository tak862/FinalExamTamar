package com.example.midexamtamar.Login


import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.midexamtamar.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisterFragment : Fragment(R.layout.fragment_register) {
    private lateinit var nameEditText: EditText
    private lateinit var birthEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var createAccountButton: Button

    private lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nameEditText = view.findViewById(R.id.Eiditname)
        birthEditText = view.findViewById(R.id.editTextDate)
        emailEditText = view.findViewById(R.id.editTextTextEmailAddress)
        passwordEditText = view.findViewById(R.id.editTextTextPassword)
        confirmPasswordEditText = view.findViewById(R.id.editTextTextPassword2)
        createAccountButton = view.findViewById(R.id.button)

        auth = FirebaseAuth.getInstance()

        createAccountButton.setOnClickListener {
            createNewAccount()
        }
    }

    private fun createNewAccount() {
        val name = nameEditText.text.toString().trim()
        val birth = birthEditText.text.toString().trim()
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString()
        val confirmPassword = confirmPasswordEditText.text.toString()

        if (name.isEmpty() || birth.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(requireContext(), "გთხოვთ შეავსოთ ყველა ველი", Toast.LENGTH_SHORT).show()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(requireContext(), "ელ.ფოსტა არასწორია", Toast.LENGTH_SHORT).show()
            return
        }

        if (password.length < 6) {
            Toast.makeText(requireContext(), "პაროლი უნდა იყოს მინიმუმ 6 სიმბოლო", Toast.LENGTH_SHORT).show()
            return
        }

        if (password != confirmPassword) {
            Toast.makeText(requireContext(), "პაროლები არ ემთხვევა", Toast.LENGTH_SHORT).show()
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    val firebaseUser = auth.currentUser
                    val userId = firebaseUser?.uid ?: return@addOnCompleteListener

                    val database = FirebaseDatabase.getInstance()
                    val usersRef = database.getReference("users")

                    val userMap = mapOf(
                        "name" to name,
                        "birth" to birth,
                        "email" to email
                    )

                    usersRef.child(userId).setValue(userMap)
                        .addOnSuccessListener {
                            Toast.makeText(requireContext(), "მომხმარებელი წარმატებით დარეგისტრირდა!", Toast.LENGTH_SHORT).show()
                            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(requireContext(), "მონაცემების შენახვა ვერ მოხერხდა: ${e.message}", Toast.LENGTH_SHORT).show()
                        }
                } else {
                    Toast.makeText(requireContext(), "რეგისტრაციის შეცდომა: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
