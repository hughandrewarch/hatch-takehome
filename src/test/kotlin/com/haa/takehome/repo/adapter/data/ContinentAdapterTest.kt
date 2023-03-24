package com.haa.takehome.repo.adapter.data

import com.haa.takehome.domain.models.Country
import com.haa.takehome.repo.models.RepoContinent
import com.haa.takehome.repo.models.RepoCountry
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class ContinentAdapterTest {
    companion object {
        private const val CODE_A = "CODE_A"
        private const val NAME_A = "NAME_A"

        private const val CODE_1 = "CODE_1"
        private const val NAME_1 = "NAME_1"
        private val REPO_COUNTRY_1 = RepoCountry(CODE_1, NAME_1)
        private val COUNTRY_1 = Country(CODE_1, NAME_1)
        private const val CODE_2 = "CODE_2"
        private const val NAME_2 = "NAME_2"
        private val REPO_COUNTRY_2 = RepoCountry(CODE_2, NAME_2)
        private val COUNTRY_2 = Country(CODE_2, NAME_2)
    }

    private val countryAdapter = mock<CountryAdapter>()
    private val subject = ContinentAdapter(countryAdapter)

    @BeforeEach
    fun setup() {
        whenever(countryAdapter.adapt(REPO_COUNTRY_1)).thenReturn(COUNTRY_1)
        whenever(countryAdapter.adapt(REPO_COUNTRY_2)).thenReturn(COUNTRY_2)
    }

    @Test
    fun adapt_withEmptyList() {
        val continent = RepoContinent(CODE_A, NAME_A, emptyList())

        val result = subject.adapt(continent)

        assertThat(result.code).isEqualTo(CODE_A)
        assertThat(result.name).isEqualTo(NAME_A)
        assertThat(result.countries).isEmpty()

        verify(countryAdapter, never()).adapt(any())
    }

    @Test
    fun adapt() {
        val continent = RepoContinent(CODE_A, NAME_A, listOf(REPO_COUNTRY_1, REPO_COUNTRY_2))

        val result = subject.adapt(continent)

        assertThat(result.code).isEqualTo(CODE_A)
        assertThat(result.name).isEqualTo(NAME_A)
        assertThat(result.countries).containsExactly(
            COUNTRY_1,
            COUNTRY_2)

        verify(countryAdapter).adapt(REPO_COUNTRY_1)
        verify(countryAdapter).adapt(REPO_COUNTRY_2)
    }
}