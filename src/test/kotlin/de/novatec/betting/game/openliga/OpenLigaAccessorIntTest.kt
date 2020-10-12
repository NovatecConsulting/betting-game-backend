package de.novatec.betting.game.openliga

import io.quarkus.test.common.QuarkusTestResource
import io.quarkus.test.junit.QuarkusTest
import org.assertj.core.api.Assertions.assertThat
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.junit.jupiter.api.Test
import utils.classification.IntegrationTest
import javax.inject.Inject

@IntegrationTest
@QuarkusTest
@QuarkusTestResource(WiremockMatchDay::class)
class OpenLigaAccessorIntTest {

    @RestClient
    @Inject
    lateinit var openLigaAccessor: OpenLigaAccessor

    @Test
    fun `GET - requests the current match day from the openliga api`() {
        val result = openLigaAccessor.getOLMatchesOfCurrentMatchday()
        assertThat(result.size).isEqualTo(1)
        assertThat(result[0].matchID).isEqualTo(55574L)
    }

    @Test
    fun `GET - requests all matches of a specified season from the openliga api`() {
        val result = openLigaAccessor.getAllMatchesOfSeason("2019")
        assertThat(result.size).isEqualTo(1)
        assertThat(result[0].matchID).isEqualTo(55277L)
    }

    @Test
    fun `GET - request all teams of a specified season from the openliga api`() {
        val result = openLigaAccessor.getAllTeams("2020")
        assertThat(result.size).isEqualTo(2)
        assertThat(result.map { it.teamName }).containsExactly("1. FC Köln", "VfB Stuttgart")
    }
}
