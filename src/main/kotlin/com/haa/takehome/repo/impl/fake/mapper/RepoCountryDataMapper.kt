package com.haa.takehome.repo.impl.fake.mapper

import com.haa.takehome.repo.impl.fake.DataCountry
import com.haa.takehome.repo.models.RepoCountry

class RepoCountryDataMapper {
    fun map(country: DataCountry): RepoCountry {
        return RepoCountry(
            country.countryCode,
            country.countryName
        )
    }
}
