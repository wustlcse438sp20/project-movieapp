package com.example.cse438.cse438_assignment2.Fragment

import android.content.ContentValues
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
import com.example.cse438.cse438_assignment2.MainpageActivity
import com.example.cse438.cse438_assignment2.PasswordActivity
import com.example.cse438.cse438_assignment2.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth



import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {
    //A firebase authentication
    private lateinit var auth: FirebaseAuth

    //From layout
    private lateinit var loginButton: Button
    private lateinit var loginEmail: EditText
    private lateinit var loginPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onStart() {
        super.onStart()

        //Initialize the firebase instance
        auth = FirebaseAuth.getInstance()

        //variables
        loginButton = submitlogin
        loginEmail = login_email
        loginPassword = login_password

        //set onclick listener
        loginButton.setOnClickListener {
            var email: String = loginEmail.text.toString()
            var password: String = loginPassword.text.toString()

            if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(this.activity, "Please fill all the fields", Toast.LENGTH_LONG).show()
            } else{
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this.activity!!, OnCompleteListener { task ->
                    if(task.isSuccessful) {
                        Toast.makeText(this.activity, "Successfully Logged In", Toast.LENGTH_LONG).show()
                        val intent = Intent(this.activity, MainpageActivity::class.java)
                        startActivity(intent)
                        this.activity!!.finish()
                    }else {
                        Toast.makeText(this.activity, "Login Failed", Toast.LENGTH_LONG).show()
                    }
                })
            }
        }//end of login button listener

        submitchangepassword.setOnClickListener {
            startActivity(Intent(activity, PasswordActivity::class.java))
        }
    }


}
