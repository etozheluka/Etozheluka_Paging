package com.space.paging_adapter_advanced.data.models

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val page: Int,
    @SerializedName(value = "results")
    val movies: List<Movie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)