package hu.bme.aut.android.virtualpetshelter.ui.main

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import hu.bme.aut.android.virtualpetshelter.analytics.FireBaseAnalyticsLogger
import hu.bme.aut.android.virtualpetshelter.databinding.PetListItemBinding
import hu.bme.aut.android.virtualpetshelter.model.Pet
import hu.bme.aut.android.virtualpetshelter.ui.details.DetailsActivity


class PetListRecyclerViewAdapter(private val context: Context) : RecyclerView.Adapter<PetListRecyclerViewAdapter.ViewHolder>() {

    private lateinit var analytics: FirebaseAnalytics

    private lateinit var binding: PetListItemBinding

    private var petList = mutableListOf<Pet>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = PetListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        analytics = Firebase.analytics
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pet = petList[position]

        holder.pet = pet
        holder.tvPetName.text = pet.name

        val imgURL = pet.primaryPhotoCropped?.small

        if (imgURL.isNullOrBlank()) {
            holder.ivPetImage.visibility = View.GONE
            Log.d("NoPicDoggo", "${pet.name!!} | URL: ${pet.primaryPhotoCropped?.small}")
        } else {
            Glide.with(context).load(imgURL).into(holder.ivPetImage)
            holder.ivPetImage.visibility = View.VISIBLE
        }
    }

    fun addItem(position: Int, pet: Pet) {
        petList.add(position, pet)
        notifyItemInserted(position)
    }

    fun updateList(pets: List<Pet>) {
        petList.clear()
        petList.addAll(pets)
        notifyDataSetChanged()
    }

    fun addAll(pets: List<Pet>) {
        petList.addAll(pets)
        notifyDataSetChanged()
    }

    fun deleteRow(position: Int) {
        petList.filterIndexedTo (petList) { index, pet -> index != position }
        notifyItemRemoved(position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivPetImage: ImageView = binding.ivPetImage
        val tvPetName: TextView = binding.tvPetName

        var pet: Pet? = null

        init {
            itemView.setOnClickListener {
                FireBaseAnalyticsLogger.logPetDetailsOpened(analytics, pet?.name!!, pet?.type!!, pet?.breeds?.primary!!, pet?.gender!!)
                val detailsIntent = Intent(context, DetailsActivity::class.java)
                detailsIntent.putExtra("petId", pet?.id)
                context.startActivity(detailsIntent)
            }
        }
    }

    override fun getItemCount(): Int {
        return petList.size
    }
}