package com.example.cse438.cse438_assignment2

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_password.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.submitlogin

class PasswordActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        setContentView(R.layout.activity_password)
    }

    override fun onStart() {
        super.onStart()
        submitlogin.setOnClickListener {
            val email = changeEmail.text.toString()
            val oldPw = oldPassword.text.toString()
            val newPw = newPassword.text.toString()
            if (email != "" && oldPw != "") {

                mAuth?.signInWithEmailAndPassword(email, oldPw)
                    ?.addOnCompleteListener {
                        if (it.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(ContentValues.TAG, "signInWithEmail:success")
                            val user = mAuth?.getCurrentUser()
                            user!!.updatePassword(newPw).addOnCompleteListener {
                                if (it.isSuccessful) {
                                    Toast.makeText(
                                        this, "change password success.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    mAuth?.signOut()
                                } else {
                                    Log.w(ContentValues.TAG, "updatePassword:failure", it.exception)
                                    Toast.makeText(
                                        this, "Authentication failed.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                            }


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(ContentValues.TAG, "updatePassword:failure", it.exception)
                            Toast.makeText(
                                this, "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    }
            } else {
                Toast.makeText(
                    this, "all the fields are required",
                    Toast.LENGTH_SHORT
                ).show()
            }
            startActivity(Intent(this, MainActivity::class.java))

        }
    }

}
