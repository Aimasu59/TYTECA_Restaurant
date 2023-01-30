package fr.isen.tyteca.androiderestaurant


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.squareup.picasso.Picasso
import fr.isen.tyteca.androiderestaurant.model.Items

internal class CustomAdapter(private var listPlat: ArrayList<Items>, val onItemClickListener: (Items) -> Unit) :
    RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {
    internal class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemTextView: TextView = view.findViewById(R.id.itemTextView)
        var itemTextView2: TextView = view.findViewById(R.id.itemTextView2)
        var imageViewcellule: ImageView = view.findViewById(R.id.imageViewcellule)
        var mainLayout : View = view.findViewById(R.id.mainLayout)


    }



    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listPlat.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = listPlat[position]
        holder.itemTextView.text = item.nameFr
        holder.itemTextView.setOnClickListener {
            onItemClickListener(item)
        }
        holder.itemTextView2.text = item.prices[0].price + "€"
        holder.itemTextView2.setOnClickListener {
            onItemClickListener(item)
        }
        if ( item.images[0].isNotEmpty()) {
            Picasso.get().load(item.images[0]).into(holder.imageViewcellule)
        }
    }
    fun refreshList(dishesFromAPI: ArrayList<Items>){
        listPlat = dishesFromAPI
        notifyDataSetChanged()
    }
}

