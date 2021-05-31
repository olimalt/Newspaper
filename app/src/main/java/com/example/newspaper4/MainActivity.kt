package com.example.newspaper4

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_research.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Load first fragment by default
        loadFragment(AccueilFragment())

        bottomNavigationView.setOnNavigationItemSelectedListener {
            menuItem ->
            when {
                menuItem.itemId== R.id.navigationAccueil -> {
                    loadFragment(AccueilFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                menuItem.itemId== R.id.navigationRecherche -> {
                    loadFragment(ResearchFragment())

                    return@setOnNavigationItemSelectedListener true
                }
                menuItem.itemId== R.id.navigationListe -> {
                    loadFragment(ListFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                else -> return@setOnNavigationItemSelectedListener false
            }
        }




    }

    fun loadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().also {fragmentTransaction ->
            fragmentTransaction.replace(R.id.fragmentContainer,fragment)
            fragmentTransaction.commit()
        }
    }
}