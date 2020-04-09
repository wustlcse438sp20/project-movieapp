package com.example.cse438.cse438_assignment2.Fragment

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.cse438.cse438_assignment2.MainActivity
import kotlinx.android.synthetic.main.fragment_signup.*
import com.example.cse438.cse438_assignment2.R


class SignupFragment : Fragment() {


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
        submitsignup.setOnClickListener {
            val email = signupEmail.text.toString()
            val password = signupPassword.text.toString()
            val username = signupUsername.text.toString()
            if (email != "" && password != "" && username != "") {
                startActivity(Intent(activity, MainActivity::class.java))
            }
        }


    }


}

