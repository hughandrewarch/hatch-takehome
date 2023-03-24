package com.haa.takehome.api.models

import com.fasterxml.jackson.annotation.JsonProperty

data class GetContinentRequest(

    @JsonProperty("countries") //TODO try without this annotation
    var countries: List<String> //TODO turn into enum
)
