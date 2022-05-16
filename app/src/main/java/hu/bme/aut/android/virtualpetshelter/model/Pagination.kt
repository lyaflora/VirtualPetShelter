package hu.bme.aut.android.virtualpetshelter.model

import com.google.gson.annotations.SerializedName

data class Pagination (

  @SerializedName("count_per_page" ) var countPerPage : Int?   = null,
  @SerializedName("total_count"    ) var totalCount   : Int?   = null,
  @SerializedName("current_page"   ) var currentPage  : Int?   = null,
  @SerializedName("total_pages"    ) var totalPages   : Int?   = null,
  @SerializedName("_links"         ) var Links        : Links? = Links()

)