package fr.isen.tyteca.androiderestaurant

import android.app.DownloadManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import fr.isen.tyteca.androiderestaurant.databinding.ActivityEntreeBinding
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.JsonRequest
import com.android.volley.toolbox.StringRequest
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
       /* val textView = findViewById<TextView>(R.id.itemTextView)

        val queue = Volley.newRequestQueue(this)
        val url = "http://test.api.catering.bluecodegames.com/menu"

        val stringRequest = JsonObjectRequest(
            Request.Method.POST, url,JSONObject("id_shop", "1")
            Response.Listener<String> { response ->
                // Display the first 500 characters of the response string.
                textView.text = "Response is: ${response.substring(0, 500)}"
            },
            Response.ErrorListener { textView.text = "That didn't work!" })

        // Add the request to the RequestQueue.
        queue.add(stringRequest)*/

        val onClickListener: OnClickListener = object : OnClickListener {
            override fun onItemClick(item: Items) {
                val intent = Intent(this@EntreeActivity, ItemActivity::class.java)
                startActivity(intent)
            }
        }

        /*val customAdapter = CustomAdapter(arrayListOf(), onClickListener) {
            val intent = Intent(this@EntreeActivity,ItemActivity::class.java)
            intent.putExtra("Detail",it )
            startActivity(intent)
        }*/
        //val customAdapter = CustomAdapter(arrayListOf(), onClickListener)
        val layoutManager = LinearLayoutManager(applicationContext)
        binding.listMeal.layoutManager = layoutManager
       // binding.listMeal.adapter = customAdapter
        binding.listMeal.adapter = CustomAdapter(arrayListOf(), onClickListener) /*{
            val intent = Intent(this@EntreeActivity, ItemActivity::class.java)
            intent.putExtra("detail", it)
        }*/


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

