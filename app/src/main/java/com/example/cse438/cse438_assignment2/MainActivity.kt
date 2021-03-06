package com.example.cse438.cse438_assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cse438.cse438_assignment2.Fragment.LoginFragment
import com.example.cse438.cse438_assignment2.Fragment.SignupFragment
import com.example.cse438.cse438_assignment2.Fragments.HomeFragment
import com.example.cse438.cse438_assignment2.Fragments.PlaylistFragment
import com.example.cse438.cse438_assignment2.Fragments.ProfileFragment
import com.example.sql_studio.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var fragment: String?=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(LoginFragment(), "LOGIN")
        adapter.addFragment(SignupFragment(), "SIGN UP")
        viewPager?.adapter = adapter
        tabs.setupWithViewPager(viewPager)
        var bundle: Bundle? = intent.extras


    }
}
