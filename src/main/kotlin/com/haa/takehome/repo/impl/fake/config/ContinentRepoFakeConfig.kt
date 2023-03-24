package com.haa.takehome.repo.impl.fake.config

import com.haa.takehome.repo.adapter.config.ContinentRepoAdapterConfig
import com.haa.takehome.repo.adapter.data.ContinentAdapter
import com.haa.takehome.repo.impl.fake.ContinentRepoFake
import com.haa.takehome.repo.impl.fake.Data
import com.haa.takehome.repo.impl.fake.mapper.RepoCountryDataMapper
import com.haa.takehome.repo.ports.ContinentRepo
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(value = [
    ContinentRepoAdapterConfig::class,
    RepoCountryDataMapper::class
])
class ContinentRepoFakeConfig {

    @Bean
    fun continentRepo(
        continentAdapter: ContinentAdapter,
        repoCountryDataMapper: RepoCountryDataMapper
    ): ContinentRepo {
        val continentRepo = ContinentRepoFake(
            continentAdapter,
            repoCountryDataMapper)
        continentRepo.initData(Data.countries)
        return continentRepo
    }
}
