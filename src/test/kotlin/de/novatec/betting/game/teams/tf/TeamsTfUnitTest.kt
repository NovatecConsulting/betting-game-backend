package de.novatec.betting.game.teams.tf

import de.novatec.betting.game.openliga.model.OLTeam
import de.novatec.betting.game.teams.model.Team
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import utils.classification.UnitTest

@UnitTest
class TeamsTfUnitTest {

    private val tf = TeamsTf()

    @Test
    fun `map list of teams`() {
        val olTeams: List<OLTeam> =
            listOf(OLTeam(16, "Vfb Stuttgart", "Vfb Stuttgart"), OLTeam(65, "1. FC Köln", "1. FC Köln", null))
        val actual = tf.olTeamsToTeams(olTeams)
        Assertions.assertThat(actual.teams.size)
            .isEqualTo(2)
        Assertions.assertThat(actual.teams)
            .containsExactlyInAnyOrder(
                Team(16, "Vfb Stuttgart", "Vfb Stuttgart", null),
                Team(65, "1. FC Köln", "1. FC Köln", null)
            )
    }

}