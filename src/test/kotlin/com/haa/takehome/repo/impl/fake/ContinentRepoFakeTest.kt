package com.haa.takehome.repo.impl.fake

import com.haa.takehome.domain.models.Continent
import com.haa.takehome.domain.models.Country
import com.haa.takehome.repo.adapter.data.ContinentAdapter
import com.haa.takehome.repo.impl.fake.exception.CountryNotFoundException
import com.haa.takehome.repo.impl.fake.exception.MultipleCountriesFoundException
import com.haa.takehome.repo.impl.fake.mapper.RepoCountryDataMapper
import com.haa.takehome.repo.models.RepoContinent
import com.haa.takehome.repo.models.RepoCountry
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*

class ContinentRepoFakeTest {
    companion object {
        private const val CODE_X = "CODE_X"
        private const val CODE_1 = "CODE_1"
        private const val NAME_1 = "NAME_1"
        private val REPO_COUNTRY_1 = RepoCountry(CODE_1, NAME_1)
        private val COUNTRY_1 = Country(CODE_1, NAME_1)
        private const val CODE_2 = "CODE_2"
        private const val NAME_2 = "NAME_2"
        private val REPO_COUNTRY_2 = RepoCountry(CODE_2, NAME_2)
        private val COUNTRY_2 = Country(CODE_2, NAME_2)
        private const val CODE_3 = "CODE_3"
        private const val NAME_3 = "NAME_3"
        private val REPO_COUNTRY_3 = RepoCountry(CODE_3, NAME_3)
        private val COUNTRY_3 = Country(CODE_3, NAME_3)

        private const val CODE_A = "CODE_A"
        private const val NAME_A = "NAME_A"
        private val REPO_CONTINENT_A = RepoContinent(CODE_A, NAME_A, listOf(REPO_COUNTRY_1, REPO_COUNTRY_2))
        private val CONTINENT_A = Continent(CODE_A, NAME_A, listOf(COUNTRY_1, COUNTRY_2))
        private const val CODE_B = "CODE_B"
        private const val NAME_B = "NAME_B"
        private val REPO_CONTINENT_B = RepoContinent(CODE_B, NAME_B, listOf(REPO_COUNTRY_3))
        private val CONTINENT_B = Continent(CODE_B, NAME_B, listOf(COUNTRY_3))

        private val DATA_COUNTRY_1 = DataCountry(CODE_A, NAME_A, CODE_1, NAME_1)
        private val DATA_COUNTRY_2 = DataCountry(CODE_A, NAME_A, CODE_2, NAME_2)
        private val DATA_COUNTRY_3 = DataCountry(CODE_B, NAME_B, CODE_3, NAME_3)
        private val DATA_COUNTRY_4 = DataCountry(CODE_B, NAME_B, CODE_1, NAME_1)
    }

    private val continentAdapter = mock<ContinentAdapter>()
    private val repoCountryDataMapper = mock<RepoCountryDataMapper>()
    private val subject = ContinentRepoFake(continentAdapter,  repoCountryDataMapper)

    @BeforeEach
    fun setup() {
        subject.initData(
            listOf(
                DATA_COUNTRY_1,
                DATA_COUNTRY_2,
                DATA_COUNTRY_3))

        whenever(continentAdapter.adapt(REPO_CONTINENT_A)).thenReturn(CONTINENT_A)
        whenever(continentAdapter.adapt(REPO_CONTINENT_B)).thenReturn(CONTINENT_B)

        whenever(repoCountryDataMapper.map(DATA_COUNTRY_1)).thenReturn(REPO_COUNTRY_1)
        whenever(repoCountryDataMapper.map(DATA_COUNTRY_2)).thenReturn(REPO_COUNTRY_2)
        whenever(repoCountryDataMapper.map(DATA_COUNTRY_3)).thenReturn(REPO_COUNTRY_3)
    }

    @Test
    fun getContinentByCountryCode_whenCountryCodeNotFound_shouldThrowException() {
        assertThrows<CountryNotFoundException> {
            subject.getContinentsByCountryCodes(CODE_X)
        }

        verify(repoCountryDataMapper, never()).map(any())
        verify(continentAdapter, never()).adapt(any())
    }

    @Test
    fun getContinentByCountryCode_whenCountryCodeFoundMultipleTimes_shouldThrowException() {
        subject.initData(
            listOf(
                DATA_COUNTRY_1,
                DATA_COUNTRY_2,
                DATA_COUNTRY_3,
                DATA_COUNTRY_4))

        assertThrows<MultipleCountriesFoundException> {
            subject.getContinentsByCountryCodes(CODE_1)
        }

        verify(repoCountryDataMapper, never()).map(any())
        verify(continentAdapter, never()).adapt(any())
    }

    @Test
    fun getContinentByCountryCode_ok() {
        val resultMap = subject.getContinentsByCountryCodes(CODE_1)

        assertThat(resultMap).hasSize(1)

        val result = resultMap[CODE_1]!!

        assertThat(result.code).isEqualTo(CODE_A)
        assertThat(result.name).isEqualTo(NAME_A)
        assertThat(result.countries)
            .containsExactlyInAnyOrder(
                COUNTRY_1,
                COUNTRY_2)

        verify(repoCountryDataMapper).map(DATA_COUNTRY_1)
        verify(repoCountryDataMapper).map(DATA_COUNTRY_2)

        verify(continentAdapter).adapt(REPO_CONTINENT_A)
    }

    @Test
    fun getContinentsByCountryCodes_whenCountriesInSameContinent() {
        val resultMap = subject.getContinentsByCountryCodes(CODE_1, CODE_2)

        assertThat(resultMap).hasSize(2)

        val result1 = resultMap[CODE_1]!!
        val result2 = resultMap[CODE_2]!!

        assertThat(result1).isEqualTo(result2)
        assertThat(result1.name).isEqualTo(NAME_A)
        assertThat(result1.countries)
            .containsExactlyInAnyOrder(
                COUNTRY_1,
                COUNTRY_2)

        verify(repoCountryDataMapper).map(DATA_COUNTRY_1)
        verify(repoCountryDataMapper).map(DATA_COUNTRY_2)

        verify(continentAdapter).adapt(REPO_CONTINENT_A)
    }

    @Test
    fun getContinentsByCountryCodes_whenCountriesInDifferentContinents() {
        val resultMap = subject.getContinentsByCountryCodes(CODE_1, CODE_3)

        assertThat(resultMap).hasSize(2)

        val result1 = resultMap[CODE_1]!!
        assertThat(result1.code).isEqualTo(CODE_A)
        assertThat(result1.name).isEqualTo(NAME_A)
        assertThat(result1.countries)
            .containsExactlyInAnyOrder(
                COUNTRY_1,
                COUNTRY_2)

        val result3 = resultMap[CODE_3]!!
        assertThat(result3.code).isEqualTo(CODE_B)
        assertThat(result3.name).isEqualTo(NAME_B)
        assertThat(result3.countries)
            .containsExactlyInAnyOrder(
                COUNTRY_3)

        verify(repoCountryDataMapper).map(DATA_COUNTRY_1)
        verify(repoCountryDataMapper).map(DATA_COUNTRY_2)
        verify(repoCountryDataMapper).map(DATA_COUNTRY_3)

        verify(continentAdapter).adapt(REPO_CONTINENT_A)
        verify(continentAdapter).adapt(REPO_CONTINENT_B)
    }
}
