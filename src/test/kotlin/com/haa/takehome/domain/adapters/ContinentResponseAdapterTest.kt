package com.haa.takehome.domain.adapters

import com.haa.takehome.domain.models.Continent
import com.haa.takehome.domain.models.Country
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ContinentResponseAdapterTest {

    companion object {
        private const val CODE_1 = "CODE_1"
        private const val NAME_1 = "NAME_1"
        private val C_1 = Country(CODE_1, NAME_1)
        private const val CODE_2 = "CODE_2"
        private const val NAME_2 = "NAME_2"
        private val C_2 = Country(CODE_2, NAME_2)
        private const val CODE_3 = "CODE_3"
        private const val NAME_3 = "NAME_3"
        private val C_3 = Country(CODE_3, NAME_3)
        private val C_4 = buildCountry(4)
        private val C_5 = buildCountry(5)
        private val C_6 = buildCountry(6)
        private val C_7 = buildCountry(7)
        private val C_8 = buildCountry(8)
        private val C_9 = buildCountry(9)

        private const val CODE_A = "CODE_A"
        private const val NAME_A = "NAME_A"
        private val CONTINENT_A = Continent(CODE_A, NAME_A, listOf(C_1, C_3, C_5, C_7, C_9))
        private const val CODE_B = "CODE_B"
        private const val NAME_B = "NAME_B"
        private val CONTINENT_B = Continent(CODE_B, NAME_B, listOf(C_2, C_4, C_6, C_8))

        private fun buildCountry(i: Int): Country {
            return Country("CODE_$i", "NAME_$i")
        }
    }

    private val subject = com.haa.takehome.domain.adapters.ContinentResponseAdapter()

    @BeforeEach
    fun setup() {
    }

    @Test
    fun adapt() {
        val input = mapOf<String, Continent>(
            Pair(CODE_1, CONTINENT_A),
            Pair(CODE_3, CONTINENT_A),
            Pair(CODE_2, CONTINENT_B)
        )

        val result = subject.adapt(input)

        assertThat(result.continents).hasSize(2)

        val continent1 = result.continents[0]
        assertThat(continent1.code).isEqualTo(CODE_A)
        assertThat(continent1.name).isEqualTo(NAME_A)
        assertThat(continent1.countries).containsExactlyInAnyOrder(CODE_1, CODE_3)
        assertThat(continent1.otherCountries).containsExactlyInAnyOrder("CODE_5", "CODE_7", "CODE_9")

        val continent2 = result.continents[1]
        assertThat(continent2.code).isEqualTo(CODE_B)
        assertThat(continent2.name).isEqualTo(NAME_B)
        assertThat(continent2.countries).containsExactlyInAnyOrder(CODE_2)
        assertThat(continent2.otherCountries).containsExactlyInAnyOrder("CODE_4", "CODE_6", "CODE_8")
    }
}
