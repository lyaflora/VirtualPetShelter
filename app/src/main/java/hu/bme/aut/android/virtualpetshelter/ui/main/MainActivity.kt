package hu.bme.aut.android.virtualpetshelter.ui.main

import android.content.res.Configuration
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.android.virtualpetshelter.R
import hu.bme.aut.android.virtualpetshelter.analytics.FireBaseAnalyticsLogger
import hu.bme.aut.android.virtualpetshelter.databinding.ActivityMainBinding


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var analytics: FirebaseAnalytics

    private lateinit var binding: ActivityMainBinding
    private lateinit var petListRecyclerViewAdapter: PetListRecyclerViewAdapter

    private var nightMode: Int = 0
    private val mainViewModel: MainViewModel by viewModels()

    private fun setupRecyclerView(view: View) {
        petListRecyclerViewAdapter = PetListRecyclerViewAdapter(this)
        val rvPetList = view.findViewById<RecyclerView>(R.id.rvPetList)
        rvPetList.adapter = petListRecyclerViewAdapter
        rvPetList.layoutManager = GridLayoutManager(this, 2)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        analytics = Firebase.analytics

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView(binding.root)

        nightMode = resources.configuration.uiMode

        val toolbar = binding.petlist.appBarMain.toolbar
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val loadingIndicator = binding.petlist.loadingIndicator
        val typeSpinner = binding.spPetType
        val breedSpinner = binding.spPetBreed
        val genderSpinner = binding.spPetGender

        mainViewModel.petList.observe(
            this
        ) { petList -> petListRecyclerViewAdapter.updateList(petList) }

        mainViewModel.loading.observe(
            this
        ) { loading ->
            if (loading) {
                loadingIndicator.root.visibility = View.VISIBLE
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
            else {
                loadingIndicator.root.visibility = View.GONE
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            }
        }

        mainViewModel.loadPetListWithPhotos()

        setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener {
            drawerLayout.open()
            FireBaseAnalyticsLogger.logButtonClicked(analytics, "btn_drawer_open")
        }

        binding.btnBack.setOnClickListener {
            drawerLayout.close()
            FireBaseAnalyticsLogger.logButtonClicked(analytics, "btn_drawer_close")
        }

        mainViewModel.petTypes.observe(
            this
        ) { types ->
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                listOf("Select pet type!") + types
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                typeSpinner.adapter = adapter
            }
        }

        typeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val typedValue = TypedValue()
                theme.resolveAttribute(androidx.appcompat.R.attr.subtitleTextColor, typedValue, true);
                val color = typedValue.data;
                (parent.getChildAt(0) as TextView).setTextColor(color)
                (parent.getChildAt(0) as TextView).textSize = 20f
                val selectedType = parent.getItemAtPosition(position).toString()
                mainViewModel.updateSelectedPetType(selectedType)
                FireBaseAnalyticsLogger.logFilterSelected(analytics, "type", selectedType)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        mainViewModel.petBreeds.observe(
            this
        ) { breeds ->
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                listOf("Select pet breed!") + breeds
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                breedSpinner.adapter = adapter
            }
        }

        breedSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val typedValue = TypedValue()
                theme.resolveAttribute(androidx.appcompat.R.attr.subtitleTextColor, typedValue, true);
                val color = typedValue.data;
                (parent.getChildAt(0) as TextView).setTextColor(color)
                (parent.getChildAt(0) as TextView).textSize = 20f
                val selectedBreed = parent.getItemAtPosition(position).toString()
                mainViewModel.updateSelectedPetBreed(selectedBreed)
                FireBaseAnalyticsLogger.logFilterSelected(analytics, "breed", selectedBreed)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            listOf("Select pet gender!") + listOf("Male", "Female")
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            genderSpinner.adapter = adapter
        }

        genderSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val typedValue = TypedValue()
                theme.resolveAttribute(androidx.appcompat.R.attr.subtitleTextColor, typedValue, true);
                val color = typedValue.data;
                (parent.getChildAt(0) as TextView).setTextColor(color)
                (parent.getChildAt(0) as TextView).textSize = 20f
                val selectedGender = parent.getItemAtPosition(position).toString()
                mainViewModel.updateSelectedPetGender(selectedGender)
                FireBaseAnalyticsLogger.logFilterSelected(analytics, "gender", selectedGender)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        val currentNightMode = newConfig.uiMode
        if (currentNightMode != nightMode) {
            val thisIntent = intent
            finish()
            startActivity(thisIntent)
        }
        nightMode = currentNightMode
    }

}