package fr.isen.tyteca.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.tyteca.androiderestaurant.databinding.ActivityHomeBinding
import java.util.logging.Logger


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setContentView(R.layout.activity_home)
        val actionBar = supportActionBar
        actionBar?.title = "Momo la frite kebab"
        val Log = Logger.getLogger(HomeActivity::class.java.name)


        binding.buttonEntree.setOnClickListener {
            val intent = Intent(this@HomeActivity, EntreeActivity::class.java)
            intent.putExtra("ActivityName","Entrées")
            startActivity(intent)
            Log.warning("entrée détruite");
            //Toast.makeText(applicationContext, "Des frites en entrées", Toast.LENGTH_SHORT).show()
        }

        binding.buttonPlat.setOnClickListener {
                val intent = Intent(this@HomeActivity,EntreeActivity::class.java)
                intent.putExtra("ActivityName","Plats")
                startActivity(intent)
                Log.warning("plat détruite");
                //Toast.makeText(applicationContext, "Des frites en plats", Toast.LENGTH_SHORT).show()
            }

        binding.buttonDessert.setOnClickListener {
                val intent = Intent(this@HomeActivity,EntreeActivity::class.java)
                intent.putExtra("ActivityName","Desserts")
                startActivity(intent)
                Log.warning("dessert détruite");
                //Toast.makeText(applicationContext, "Des frites en desserts", Toast.LENGTH_SHORT).show()
            }

        Log.warning("home détruite");
        }

    }
