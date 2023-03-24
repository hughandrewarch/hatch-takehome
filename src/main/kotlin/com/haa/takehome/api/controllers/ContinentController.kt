package com.haa.takehome.api.controllers

import com.haa.takehome.api.models.GetContinentRequest
import com.haa.takehome.api.models.GetContinentResponse
import com.haa.takehome.domain.ports.ContinentService
import org.springframework.web.bind.annotation.*

@RestController
class ContinentController(
    val continentService: ContinentService) {

    @GetMapping("/continent")
    fun continent(@RequestBody countries: GetContinentRequest): GetContinentResponse {
        return continentService.getContinents(countries)
    }
}
