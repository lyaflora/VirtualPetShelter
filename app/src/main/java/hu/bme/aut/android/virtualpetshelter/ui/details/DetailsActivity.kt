package hu.bme.aut.android.virtualpetshelter.ui.details

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.android.virtualpetshelter.R
import hu.bme.aut.android.virtualpetshelter.databinding.ActivityDetailsBinding
import hu.bme.aut.android.virtualpetshelter.ui.main.MainViewModel

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    private val detailsViewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val petId = intent.getIntExtra("petId", -1)

        detailsViewModel.pet.observe(
            this
        ) { pet ->
            binding.tvPetName.text = pet.name
            val imgURL = pet.primaryPhotoCropped?.full
            if (imgURL.isNullOrBlank())
                binding.ivPetImage.visibility = View.GONE
            else {
                Glide.with(this).load(imgURL).into(binding.ivPetImage)
                binding.ivPetImage.visibility = View.VISIBLE
            }
            if (pet.description == null)
                binding.tvPetDescription.visibility = View.GONE
            else {
                binding.tvPetDescription.visibility = View.VISIBLE
                binding.tvPetDescription.text = pet.description
            }
            binding.tvPetType.text = "Type: ${pet.type}"
            binding.tvPetBreed.text = "Breed: ${pet.breeds?.primary}"
            binding.tvPetGender.text = "Gender: ${pet.gender}"
            binding.tvPetAge.text = "Age: ${pet.age}"
            binding.tvPetContactEmail.text = "Contact e-mail: ${pet.contact?.email}"
            binding.tvPetContactPhone.text = "Contact phone: ${pet.contact?.phone}"
            binding.btnOpenPetfinder.setOnClickListener {
                val petFinderURL = pet.url
                val openURL = Intent(Intent.ACTION_VIEW)
                openURL.data = Uri.parse(petFinderURL)
                startActivity(openURL)
            }
        }

        detailsViewModel.getById(petId)
    }
}