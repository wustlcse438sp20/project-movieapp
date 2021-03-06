package com.example.cse438.cse438_assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cse438.cse438_assignment2.Fragments.HomeFragment
import com.example.cse438.cse438_assignment2.Fragments.PlaylistFragment
import com.example.cse438.cse438_assignment2.Fragments.ProfileFragment
import com.example.sql_studio.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainpageActivity : AppCompatActivity() {
    var fragment: String?=""
    var email:String?=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainpage)

        val intent = intent
        email = intent.getStringExtra("email")
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(HomeFragment(), "Search")
        adapter.addFragment(PlaylistFragment(), "Watch List")
        adapter.addFragment(ProfileFragment(), "My Profile")
        viewPager?.adapter = adapter
        tabs.setupWithViewPager(viewPager)
        var bundle: Bundle? = intent.extras


    }
}
