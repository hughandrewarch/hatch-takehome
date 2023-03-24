package com.haa.takehome.repo.adapter.data

import com.haa.takehome.repo.models.RepoCountry
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CountryAdapterTest {

    companion object {
        const val CODE = "CODE"
        const val NAME = "NAME"
    }

    private val subject = CountryAdapter()

    @Test
    fun adapt() {
        val country = RepoCountry(CODE, NAME)

        val result = subject.adapt(country)

        assertThat(result.code).isEqualTo(CODE)
        assertThat(result.name).isEqualTo(NAME)
    }
}