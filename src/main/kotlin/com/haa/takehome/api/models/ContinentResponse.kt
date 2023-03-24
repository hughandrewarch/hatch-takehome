package com.haa.takehome.api.models

data class ContinentResponse(
    val code: String,
    val name: String,
    val countries: List<String>,
    val otherCountries: List<String>
)
