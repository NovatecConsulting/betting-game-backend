package de.novatec.betting.game.teams.tf

import de.novatec.betting.game.model.Team
import de.novatec.betting.game.openliga.model.OLTeam
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
                OLTeam(
                    teamId = 16,
                    teamName = "VfB Stuttgart",
                    shortName = "Stuttgart",
                    teamIconUrl = "vfb-logo",
                    teamGroupName = "nicht-relevant"
                ),
                OLTeam(teamId = 65, teamName = "1. FC Köln", shortName = "FC Köln", teamIconUrl = null)
            )
        val actual = tf.olTeamsToTeams(olTeams)
        assertThat(actual.teams.size).isEqualTo(2)
        assertThat(actual.teams).containsExactly(
            Team(id = 16, name = "VfB Stuttgart", shortName = "Stuttgart", logo = "vfb-logo"),
            Team(id = 65, shortName = "FC Köln", name = "1. FC Köln", logo = null)
        )
    }

}