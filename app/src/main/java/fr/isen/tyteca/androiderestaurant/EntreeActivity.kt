package fr.isen.tyteca.androiderestaurant

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import fr.isen.tyteca.androiderestaurant.databinding.ActivityEntreeBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.tyteca.androiderestaurant.model.DataResult
import fr.isen.tyteca.androiderestaurant.model.Items
import org.json.JSONObject


class EntreeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEntreeBinding
    private lateinit var category : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEntreeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        category = intent.getStringExtra("ActivityName") ?: ""
        val actionBar = supportActionBar
        val profileName=intent.getStringExtra("ActivityName")
        actionBar?.title = "Momo la frite kebab: " +profileName
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FCAC2A")))


        /*val customAdapter = CustomAdapter(arrayListOf(), onClickListener) {
            val intent = Intent(this@EntreeActivity,ItemActivity::class.java)
            intent.putExtra("Detail",it )
            startActivity(intent)
        }*/
        //val customAdapter = CustomAdapter(arrayListOf(), onClickListener)
        val layoutManager = LinearLayoutManager(applicationContext)
        binding.listMeal.layoutManager = layoutManager
       // binding.listMeal.adapter = customAdapter
        binding.listMeal.adapter = CustomAdapter(arrayListOf()) {
            val intent = Intent(this, ItemActivity::class.java)
            intent.putExtra("detail", it)
            startActivity(intent)
        }


        loadDishesFromAPI()
    }

    private fun loadDishesFromAPI() {
        val url = "http://test.api.catering.bluecodegames.com/menu"
        val jsonObject = JSONObject()
        jsonObject.put("id_shop","1")
        val jsonRequest = JsonObjectRequest(Request.Method.POST, url, jsonObject , {
            Log.w("EntreeActivity", "reponse : $it")
            handleAPIData(it.toString())
        }, {
            Log.w("EntreeActivity", "erreur : $it")
        })
        Volley.newRequestQueue(this).add(jsonRequest)
    }

    private fun handleAPIData(data: String){
        val dishesResult = Gson().fromJson(data, DataResult::class.java)
        val dishCategoryFiltered =dishesResult.data.firstOrNull{ it.nameFr == category}
        val adapter = binding.listMeal.adapter as CustomAdapter
        adapter.refreshList(dishCategoryFiltered?.items as ArrayList<Items>)

    }
}

