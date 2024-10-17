package com.example.playjuegos

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private var isFabMovedUp = false // Variable para saber si el FAB está arriba

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configuramos la Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Botones de la pantalla principal
        val jugador = findViewById<Button>(R.id.button1)
        val preferences = findViewById<Button>(R.id.button2)
        val play = findViewById<Button>(R.id.button)

        // Configuramos los listeners para los botones
        jugador.setOnClickListener { lanzarNewplayer() }
        preferences.setOnClickListener { lanzarPreferencesActivity() }
        play.setOnClickListener { lanzarPlay() }
    }

    // Método para lanzar la actividad Newplayer
    private fun lanzarNewplayer() {
        val intent = Intent(this, Newplayer::class.java)
        startActivity(intent)
    }

    // Método para lanzar la actividad PreferencesActivity
    private fun lanzarPreferencesActivity() {
        val intent = Intent(this, PreferencesActivity::class.java)
        startActivity(intent)
    }

    // Método para lanzar la actividad Games
    private fun lanzarPlay() {
        val intent = Intent(this, Games::class.java)
        startActivity(intent)
    }

    // Método para configurar los Chips de plataformas
    private fun configurarChipGroup() {
        val chipGroupPlatforms = findViewById<ChipGroup>(R.id.chipGroupPlatforms)

        for (i in 0 until chipGroupPlatforms.childCount) {
            val chip = chipGroupPlatforms.getChildAt(i) as Chip
            chip.setOnClickListener {
                Toast.makeText(this, "Seleccionaste: ${chip.text}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Método para configurar los TextView de géneros
    private fun configurarGeneros() {
        val generoAccion = findViewById<TextView>(R.id.genero_accion)
        generoAccion.setOnClickListener {
            Toast.makeText(this, "Acción", Toast.LENGTH_SHORT).show()
        }

        val generoAventura = findViewById<TextView>(R.id.genero_aventura)
        generoAventura.setOnClickListener {
            Toast.makeText(this, "Aventura", Toast.LENGTH_SHORT).show()
        }

        val generoDeportes = findViewById<TextView>(R.id.genero_deportes)
        generoDeportes.setOnClickListener {
            Toast.makeText(this, "Deportes", Toast.LENGTH_SHORT).show()
        }

        val generoDisparos = findViewById<TextView>(R.id.genero_disparos)
        generoDisparos.setOnClickListener {
            Toast.makeText(this, "Disparos", Toast.LENGTH_SHORT).show()
        }

        val generoEstrategia = findViewById<TextView>(R.id.genero_estrategia)
        generoEstrategia.setOnClickListener {
            Toast.makeText(this, "Estrategia", Toast.LENGTH_SHORT).show()
        }

        val generoLucha = findViewById<TextView>(R.id.genero_lucha)
        generoLucha.setOnClickListener {
            Toast.makeText(this, "Lucha", Toast.LENGTH_SHORT).show()
        }

        val generoMusical = findViewById<TextView>(R.id.genero_musical)
        generoMusical.setOnClickListener {
            Toast.makeText(this, "Musical", Toast.LENGTH_SHORT).show()
        }

        val generoRol = findViewById<TextView>(R.id.genero_rol)
        generoRol.setOnClickListener {
            Toast.makeText(this, "Rol", Toast.LENGTH_SHORT).show()
        }

        val generoSimulacion = findViewById<TextView>(R.id.genero_simulacion)
        generoSimulacion.setOnClickListener {
            Toast.makeText(this, "Simulación", Toast.LENGTH_SHORT).show()
        }
    }

    // Inflamos el menú de opciones
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    // Manejamos la selección de elementos en el menú
    @SuppressLint("ClickableViewAccessibility")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        // Si el usuario selecciona "Search", inflamos el nuevo layout
        return when (id) {
            R.id.action_search -> {
                // Aquí inflamos el layout con ChipGroup y BottomAppBar
                setContentView(R.layout.chip_group)

                // Aseguramos que los elementos sean configurados después de inflar el layout
                configurarChipGroup()
                configurarGeneros()

                // Configuramos el FAB después de inflar el layout chip_group.xml
                val fabAdd = findViewById<FloatingActionButton>(R.id.fab_add)
                fabAdd?.setOnClickListener {
                    if (!isFabMovedUp) {
                        // Si el FAB no está arriba, lo subimos
                        val moveUp = ObjectAnimator.ofFloat(it, View.TRANSLATION_Y, -100f)
                        moveUp.duration = 200
                        moveUp.start()
                        isFabMovedUp = true
                    }
                }

                // Detectar toque en cualquier parte de la pantalla para mover el FAB abajo
                val mainLayout = findViewById<View>(R.id.coordinatorLayout)
                mainLayout?.setOnTouchListener { _, _ ->
                    if (isFabMovedUp) {
                        // Si el FAB está arriba, lo bajamos
                        fabAdd?.let {
                            val moveDown = ObjectAnimator.ofFloat(it, View.TRANSLATION_Y, 0f)
                            moveDown.duration = 200
                            moveDown.start()
                            isFabMovedUp = false
                        }
                    }
                    false
                }

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
