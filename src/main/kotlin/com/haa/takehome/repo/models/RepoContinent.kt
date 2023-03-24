package com.haa.takehome.repo.models

data class RepoContinent (
    val code: String,
    val name: String,
    val countries: List<RepoCountry>
)
