package de.novatec.betting.game.teams

import de.novatec.betting.game.model.Team
import de.novatec.betting.game.model.Teams
import de.novatec.betting.game.openliga.OpenLigaAccessor
import de.novatec.betting.game.openliga.model.OLTeam
import de.novatec.betting.game.teams.tf.TeamsTf
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkClass
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import utils.classification.UnitTest

val openLigaAccessor: OpenLigaAccessor = mockk()
val teamsTf: TeamsTf = mockk()

@UnitTest
class TeamsServiceUnitTest {

    private val service = TeamsService(openLigaAccessor, teamsTf)

    @Test
    fun `get teams for a season`() {

        val olTeams = listOf(mockkClass(OLTeam::class), mockkClass(OLTeam::class))
        val teams = mockkClass(Teams::class)

        every { openLigaAccessor.getAllTeams("2020") } returns olTeams
        every { teamsTf.olTeamsToTeams(olTeams) } returns teams
        every { teams.teams } returns listOf(mockkClass(Team::class), mockkClass(Team::class))
        val actual = service.getTeams("2020")
        assertThat(actual.teams.size).isEqualTo(2)
    }

}


