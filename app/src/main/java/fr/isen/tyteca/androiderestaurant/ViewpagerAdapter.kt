package fr.isen.tyteca.androiderestaurant

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.tyteca.androiderestaurant.databinding.ItemHolderBinding
import fr.isen.tyteca.androiderestaurant.model.Items

class ViewpagerAdapter(
    private val context: Context,
    private val labelList: MutableList<String>
) : RecyclerView.Adapter<ViewpagerAdapter.ViewPagerHolder>() {
    class ViewPagerHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageCarrousel: ImageView = view.findViewById(R.id.imageCarrousel)
        var CarousselLayout : View = view.findViewById(R.id.CarousselLayout)


    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        val binding = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_holder, parent, false)
        return ViewPagerHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        val label = labelList[position]
            if (label.isNotEmpty()) {
                Picasso.get().load(label).into(holder.imageCarrousel)
            }


    }

    override fun getItemCount(): Int {
        return labelList.size
    }


    }


