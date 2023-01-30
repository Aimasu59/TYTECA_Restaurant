package fr.isen.tyteca.androiderestaurant

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.TextView
import com.squareup.picasso.Picasso
import fr.isen.tyteca.androiderestaurant.databinding.ActivityItemBinding
import fr.isen.tyteca.androiderestaurant.model.Items
import android.widget.ImageView
import com.google.android.material.snackbar.Snackbar

@Suppress("DEPRECATION")
class ItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityItemBinding
    private lateinit var item: Items
    private lateinit var name: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_item)
        item = intent.getSerializableExtra("detail") as Items
        name = item.nameFr.toString()

        supportActionBar?.title = "" + name
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FCAC2A")))
        binding.nomPlat.text= name

        val ingredients = item.ingredients

        if (item.ingredients.isNotEmpty()) {
            val ingredientsString = java.lang.StringBuilder()
            ingredients.forEach { ingredients ->
                ingredientsString.append(ingredients.nameFr)
                ingredientsString.append(",")
            }
            binding.listIngredient.text = ingredientsString

        }



        val prix = item.prices
        val priceString = java.lang.StringBuilder()
        val priceunique = item.prices[0].price?.toDouble()

        var addition = 0
        val number = addition * priceunique!!
        binding.boutonPlus.setOnClickListener {
            addition++
            binding.totalText.text =
                Editable.Factory.getInstance().newEditable(addition.toString())

            if (item.prices.isNotEmpty()) {
                prix.forEach { prix ->
                    priceString.append(prix.price)
                    priceString.append("€")
                }
                val number = addition * priceunique!!
                binding.prixText.text = number.toString() + "€"
                binding.prixText.setOnClickListener{
                    val view = findViewById<View>(android.R.id.content)
                    Snackbar.make(view, "Vous avez pris "+number.toString()+"€", Snackbar.LENGTH_SHORT).show()
                }
            }
        }



        binding.boutonMoins.setOnClickListener {
            if(addition >0) {
                addition--
                binding.totalText.setText(
                    Editable.Factory.getInstance().newEditable(addition.toString())
                )
                val number = addition * priceunique!!
                binding.prixText.text = number.toString() + "€"
                binding.prixText.setOnClickListener{
                    val view = findViewById<View>(android.R.id.content)
                    Snackbar.make(view, "Vous avez pris "+number.toString()+"€", Snackbar.LENGTH_SHORT).show()
                }
            }
        }

        setupViewPager2()

    }

    private fun setupViewPager2() {
        val list: MutableList<String> = ArrayList()
        for(photos in item.images) {
            list.add(photos)
        }




        // Set adapter to viewPager.
        binding.carrousel.adapter = ViewpagerAdapter(this, list)
    }


}

