package com.haa.takehome.repo.adapter.data

import com.haa.takehome.domain.models.Continent
import com.haa.takehome.repo.models.RepoContinent

class ContinentAdapter(private val countryAdapter: CountryAdapter) {
    fun adapt(continent: RepoContinent): Continent {
        return Continent(
            continent.code,
            continent.name,
            continent.countries.map(countryAdapter::adapt)
        )
    }
}
