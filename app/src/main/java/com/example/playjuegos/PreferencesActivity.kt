package com.example.playjuegos

import android.os.Bundle
import android.view.Menu
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RatingBar
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PreferencesActivity : AppCompatActivity() {

    private lateinit var radioGroup: RadioGroup
    private lateinit var seekBar: SeekBar
    private lateinit var ratingBar: RatingBar
    private lateinit var fab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.preferences)

        radioGroup = findViewById(R.id.rg_games)
        seekBar = findViewById(R.id.seekBar)
        ratingBar = findViewById(R.id.ratingBar)
        fab = findViewById(R.id.fab_submit)

        ratingBar.onRatingBarChangeListener = RatingBar.OnRatingBarChangeListener { ratingBar, rating, fromUser ->
            seekBar.progress = rating.toInt()
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                ratingBar.rating = progress.toFloat()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        fab.setOnClickListener {
            val selectedGameId = radioGroup.checkedRadioButtonId
            if (selectedGameId == -1) {
                // Ningún juego seleccionado
                Toast.makeText(this, "No has pulsado ninguna opción", Toast.LENGTH_SHORT).show()
            } else {
                // Juego seleccionado
                val selectedGame = findViewById<RadioButton>(selectedGameId)
                val gameName = selectedGame.text.toString()
                val score = seekBar.progress

                // Mostrar el mensaje con el juego seleccionado y la puntuación
                Toast.makeText(this, "$gameName Puntuación: $score", Toast.LENGTH_SHORT).show()
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
