package fr.isen.tyteca.androiderestaurant.model

import com.google.gson.annotations.SerializedName


data class DataResult (

  @SerializedName("data" ) var data : ArrayList<Data> = arrayListOf()

)
  :java.io.Serializable