package com.haa.takehome.domain.config

import com.haa.takehome.domain.adapters.ContinentResponseAdapter
import com.haa.takehome.domain.ports.ContinentService
import com.haa.takehome.domain.services.ContinentServiceImpl
import com.haa.takehome.repo.impl.fake.config.ContinentRepoFakeConfig
import com.haa.takehome.repo.ports.ContinentRepo
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(value = [
    ContinentRepoFakeConfig::class,
    ContinentResponseAdapter::class
])
class DomainServiceConfig {

    @Bean
    fun configService(
        continentRepo: ContinentRepo,
        adapter: ContinentResponseAdapter
    ): ContinentService {
        return ContinentServiceImpl(continentRepo, adapter)
    }
}
