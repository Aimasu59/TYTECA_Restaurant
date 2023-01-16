package fr.isen.tyteca.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.snackbar.Snackbar


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val action = findViewById<Button>(R.id.buttonEntree)
        action.setOnClickListener {
        Snackbar.make(it, "Des frites en entrées", Snackbar.LENGTH_SHORT).show()

            val action2 = findViewById<Button>(R.id.buttonPlat)
            action2.setOnClickListener {
                Snackbar.make(it, "Des frites en plats", Snackbar.LENGTH_SHORT).show()
            }
            val action3 = findViewById<Button>(R.id.buttonDessert)
            action3.setOnClickListener {
                Snackbar.make(it, "Des frites en desserts", Snackbar.LENGTH_SHORT).show()
            }
        }

    }
}