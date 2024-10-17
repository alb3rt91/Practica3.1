package com.example.playjuegos

import android.os.Bundle
import android.view.Menu
import android.widget.CheckBox
import android.widget.RadioGroup
import android.widget.RatingBar
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Games : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.games)

        val cbAngryBirds = findViewById<CheckBox>(R.id.checkbox_angry_birds)
        val cbDragonFly = findViewById<CheckBox>(R.id.checkbox_dragon_fly)
        val cbHillClimbing = findViewById<CheckBox>(R.id.checkbox_hill_climbing)
        val cbRadiantDefense = findViewById<CheckBox>(R.id.checkbox_radiant_defense)
        val cbPocketSoccer = findViewById<CheckBox>(R.id.checkbox_pocket_soccer)

        val fabSubmit = findViewById<FloatingActionButton>(R.id.fab_submit)

        fabSubmit.setOnClickListener {
            val selectedGames = ArrayList<String>()

            if (cbAngryBirds.isChecked) selectedGames.add("Angry Birds")
            if (cbDragonFly.isChecked) selectedGames.add("Dragon Fly")
            if (cbHillClimbing.isChecked) selectedGames.add("Hill Climbing Racing")
            if (cbRadiantDefense.isChecked) selectedGames.add("Radiant Defense")
            if (cbPocketSoccer.isChecked) selectedGames.add("Pocket Soccer")

            if (selectedGames.isNotEmpty()) {
                Toast.makeText(
                    this, "Juegos seleccionados: ${selectedGames.joinToString(", ")}",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(this, "No has seleccionado ningún juego", Toast.LENGTH_SHORT).show()
            }
        }

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
}