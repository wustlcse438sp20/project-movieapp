package com.example.cse438.cse438_assignment2.Fragment

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.cse438.cse438_assignment2.MainActivity
import com.example.cse438.cse438_assignment2.MainpageActivity
import kotlinx.android.synthetic.main.fragment_signup.*
import com.example.cse438.cse438_assignment2.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class SignupFragment : Fragment() {
    //Default values
    private var defaultWins:Int = R.drawable.profile02;
    private var defaultLosses:Int = 0;
    private var defaultChips:Int = 100;
    //A firebase authentication
    private lateinit var auth: FirebaseAuth
    private lateinit var userName: String
    //From layout
    private lateinit var signupButton: Button
    private lateinit var signupEmail: EditText
    private lateinit var signupPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(
            R.layout.fragment_signup,
            container,
            false
        )
    }

    override fun onStart() {
        super.onStart()
        //Initialize the firebase instance
        auth = FirebaseAuth.getInstance()

        //Define the late inits
        signupButton = signup_button
        signupEmail = signup_email
        signupPassword = signup_pswd

        //set onclick listener
        signupButton.setOnClickListener {
            //set the text to string
            var email: String = signupEmail.text.toString()
            var password: String = signupPassword.text.toString()
            userName = email.substring(0,email.indexOf('@'))

            val db = FirebaseFirestore.getInstance()
            val userData = HashMap<String, Any>()
            userData["email"] = email
            userData["username"] = userName
            userData["wins"] = defaultWins
            userData["losses"] = defaultLosses
            userData["chips"] = defaultChips
            //Check if the fields are empty
            if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(this.activity, "Please fill all the fields", Toast.LENGTH_LONG).show()
            } else {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this.activity!!, OnCompleteListener{ task ->
                    if(task.isSuccessful){
                        //Store the values to the databse
                        db.document("players/${auth?.currentUser?.uid}")
                            .set(userData)
                            .addOnSuccessListener {
                                Toast.makeText(this.activity, "Player Created", Toast.LENGTH_LONG).show()
                                val intent = Intent(this.activity, MainpageActivity::class.java)
                                startActivity(intent)
                                this.activity!!.finish()
                            }
                            .addOnFailureListener {
                                Toast.makeText(this.activity, "Failed to write player data", Toast.LENGTH_SHORT).show()
                            }
                    }else {
                        Toast.makeText(this.activity, "Registration Failed", Toast.LENGTH_LONG).show()
                    }
                })
            }
        }//click listener
    }
}

