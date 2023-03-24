package com.haa.takehome.repo.adapter.data

import com.haa.takehome.domain.models.Country
import com.haa.takehome.repo.models.RepoCountry

class CountryAdapter {
    fun adapt(country: RepoCountry): Country {
        return Country(
            country.code,
            country.name
        )
    }
}
