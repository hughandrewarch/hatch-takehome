package com.haa.takehome.domain.adapters

import com.haa.takehome.api.models.ContinentResponse
import com.haa.takehome.api.models.GetContinentResponse
import com.haa.takehome.domain.models.Continent
import com.haa.takehome.domain.models.Country

class ContinentResponseAdapter {

    fun adapt(
        countryToContinentMap: Map<String, Continent>
    ) : GetContinentResponse {

        countryToContinentMap.values
            .map { it.code to it.countries.toTypedArray() }

        val continents: MutableMap<String, ContinentResponse> = hashMapOf()
        countryToContinentMap.forEach {
            val code = it.value.code
            if (!continents.containsKey(code)) {
                continents[code] = ContinentResponse(
                    code,
                    it.value.name,
                    emptyList(),
                    it.value.countries.map(Country::code)
                )
            }
            continents[code] = ContinentResponse(
                code,
                it.value.name,
                continents[code]!!.countries.plus(it.key),
                continents[code]!!.otherCountries.minus(it.key)
            )
        }

        return GetContinentResponse(
            continents.values.toList()
        )
    }
}