package hu.bme.aut.android.virtualpetshelter.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is search list Fragment"
    }
    val text: LiveData<String> = _text
}