package com.haa.takehome.domain.ports

import com.haa.takehome.api.models.GetContinentRequest
import com.haa.takehome.api.models.GetContinentResponse

interface ContinentService {
    fun getContinents(request: GetContinentRequest): GetContinentResponse
}