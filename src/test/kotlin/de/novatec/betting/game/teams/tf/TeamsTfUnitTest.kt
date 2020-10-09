package de.novatec.betting.game.teams.tf

import de.novatec.betting.game.openliga.model.OLTeam
import de.novatec.betting.game.teams.model.Team
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import utils.classification.UnitTest

@UnitTest
class TeamsTfUnitTest {

    private val tf = TeamsTf()

    @Test
    fun `map list of teams`() {
        val olTeams: List<OLTeam> =
            listOf(
                OLTeam(teamId = 16, teamName = "VfB Stuttgart", shortName = "Stuttgart"),
                OLTeam(teamId = 65, teamName = "1. FC Köln", shortName = "FC Köln", teamIconUrl = null)
            )
        val actual = tf.olTeamsToTeams(olTeams)
        assertThat(actual.teams.size).isEqualTo(2)
        assertThat(actual.teams).containsExactly(
            Team(16, "VfB Stuttgart", "Stuttgart", null),
            Team(65, "1. FC Köln", "FC Köln", null)
        )
    }

}