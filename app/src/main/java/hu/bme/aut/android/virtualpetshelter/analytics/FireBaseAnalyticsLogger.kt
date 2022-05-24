package hu.bme.aut.android.virtualpetshelter.analytics

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

class FireBaseAnalyticsLogger {
    companion object {
        fun logButtonClicked(analytics: FirebaseAnalytics, buttonName: String) {
            val params = Bundle()
            params.putString("button_name", buttonName)
            analytics.logEvent("button_clicked", params)
        }

        fun logFilterSelected(analytics: FirebaseAnalytics, filterType: String, filterValue: String) {
            val params = Bundle()
            params.putString("filter_type", filterType)
            params.putString("filter_value", filterValue)
            analytics.logEvent("filter_selected", params)
        }

        fun logPetDetailsOpened(analytics: FirebaseAnalytics, petName: String, petType: String, petBreed: String, petGender: String) {
            val params = Bundle()
            params.putString("pet_name", petName)
            params.putString("pet_type", petType)
            params.putString("pet_breed", petBreed)
            params.putString("pet_gender", petGender)
            analytics.logEvent("pet_details_opened", params)
        }

        fun logPetOpenedInPetfinder(analytics: FirebaseAnalytics, petName: String, petType: String, petBreed: String, petGender: String) {
            val params = Bundle()
            params.putString("pet_name", petName)
            params.putString("pet_type", petType)
            params.putString("pet_breed", petBreed)
            params.putString("pet_gender", petGender)
            analytics.logEvent("pet_opened_in_petfinder", params)
        }
    }
}