package com.haa.takehome.domain.models

data class Continent (
    val code: String,
    val name: String,
    val countries: List<Country>
)