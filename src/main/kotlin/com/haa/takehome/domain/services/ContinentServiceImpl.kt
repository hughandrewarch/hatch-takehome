package com.haa.takehome.domain.services

import com.haa.takehome.api.models.GetContinentRequest
import com.haa.takehome.api.models.GetContinentResponse
import com.haa.takehome.domain.adapters.ContinentResponseAdapter
import com.haa.takehome.domain.ports.ContinentService
import com.haa.takehome.repo.ports.ContinentRepo

class ContinentServiceImpl(
    private val continentRepo: ContinentRepo,
    private val adapter: ContinentResponseAdapter
): ContinentService {

    override fun getContinents(request: GetContinentRequest): GetContinentResponse {
        val countryCodes = request.countries

        val continents = continentRepo.getContinentsByCountryCodes(*countryCodes.toTypedArray())

        return adapter.adapt(continents)
    }
}