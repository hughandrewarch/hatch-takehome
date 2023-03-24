package com.haa.takehome.repo.adapter.config

import com.haa.takehome.repo.adapter.data.ContinentAdapter
import com.haa.takehome.repo.adapter.data.CountryAdapter
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import


@Configuration
@Import(value = [
    ContinentAdapter::class,
    CountryAdapter::class
])
class ContinentRepoAdapterConfig
