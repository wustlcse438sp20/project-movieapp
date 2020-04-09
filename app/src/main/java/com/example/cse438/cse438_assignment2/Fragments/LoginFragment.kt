package com.example.cse438.cse438_assignment2.Fragment

import android.content.ContentValues
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
import com.example.cse438.cse438_assignment2.MainpageActivity
import com.example.cse438.cse438_assignment2.R


import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {


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
        submitlogin.setOnClickListener {
            val email = loginEmail.text.toString()
            val password = loginPassword.text.toString()
            if (email != "" && password != "") {
                startActivity(Intent(activity, MainpageActivity::class.java))

            } else {
                Toast.makeText(
                    this.context, "all the fields are required",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        submitchangepassword.setOnClickListener {
//            startActivity(Intent(activity, PasswordActivity::class.java))
        }


    }


}
