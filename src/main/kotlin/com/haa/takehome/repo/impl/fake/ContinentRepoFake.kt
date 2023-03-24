package com.haa.takehome.repo.impl.fake

import com.haa.takehome.domain.models.Continent
import com.haa.takehome.repo.adapter.data.ContinentAdapter
import com.haa.takehome.repo.impl.fake.exception.CountryNotFoundException
import com.haa.takehome.repo.impl.fake.exception.MultipleCountriesFoundException
import com.haa.takehome.repo.impl.fake.mapper.RepoCountryDataMapper
import com.haa.takehome.repo.models.RepoContinent
import com.haa.takehome.repo.ports.ContinentRepo

class ContinentRepoFake(
    private val adapter: ContinentAdapter,
    private val mapper: RepoCountryDataMapper
): ContinentRepo {

    private var fakeData: List<DataCountry> = emptyList()

    override fun getContinentsByCountryCodes(vararg countryCodes: String): Map<String, Continent> {
        val countryToContinentMap = countryCodes.map(::filterCountries)
            .associateBy { it.countryCode }

        val continentMap = countryToContinentMap.values
            .distinctBy { it.continentCode }
            .map(::buildContinent)
            .associateBy { it.code }

        return countryToContinentMap.entries
            .associate { it.key to it.value.continentCode }
            .mapValues { continentMap[it.value]!! }
    }

    fun initData(countries: List<DataCountry>) {
        this.fakeData = countries
    }

    private fun filterCountries(countryCode: String): DataCountry {
        val filteredCountries = fakeData
            .filter { it.countryCode == countryCode }

        if (filteredCountries.size > 1) {
            throw MultipleCountriesFoundException(countryCode)
        } else if (filteredCountries.isEmpty()) {
            throw CountryNotFoundException(countryCode)
        }

        return filteredCountries.first()
    }

    private fun buildContinent(filteredCountry: DataCountry): Continent {
        val countries = fakeData.filter { it.continentCode == filteredCountry.continentCode }
            .map(mapper::map)

        return adapter.adapt(
            RepoContinent(
                filteredCountry.continentCode,
                filteredCountry.continentName,
                countries))
    }
}