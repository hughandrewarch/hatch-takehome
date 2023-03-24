package com.haa.takehome.repo.impl.fake.mapper

import com.haa.takehome.repo.impl.fake.DataCountry
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class RepoCountryDataMapperTest {

    companion object {
        const val CODE_1 = "CODE_1"
        const val NAME_1 = "NAME_1"
        const val CODE_2 = "CODE_2"
        const val NAME_2 = "NAME_2"
    }

    private val subject = RepoCountryDataMapper()

    @Test
    fun map() {
        val country = DataCountry(CODE_1, NAME_1, CODE_2, NAME_2)

        val result = subject.map(country)

        Assertions.assertThat(result.code).isEqualTo(CODE_2)
        Assertions.assertThat(result.name).isEqualTo(NAME_2)
    }
}