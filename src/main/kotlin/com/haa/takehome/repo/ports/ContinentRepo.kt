package com.haa.takehome.repo.ports

import com.haa.takehome.domain.models.Continent

interface ContinentRepo {
    fun getContinentsByCountryCodes(vararg countryCodes: String): Map<String, Continent>
}