package de.novatec.betting.game.scoreboard.tf

import de.novatec.betting.game.model.ScoreboardTeam
import de.novatec.betting.game.openliga.model.OLScoreboardTeam
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import utils.classification.UnitTest

@UnitTest
class ScoreboardTfUnitTest {

    private val cut = ScoreboardTf()

    @Test
    fun `transform a list of olScoreboardTeam to a list of scoreboardTeam`() {

        val matchDays: List<OLScoreboardTeam> = listOf(
            OLScoreboardTeam(
                teamInfoId = 40,
                teamName = "FC Bayern",
                shortName = "FC Bayern",
                teamIconUrl = "",
                points = 78,
                opponentGoals = 32,
                goals = 88,
                matches = 34,
                won = 24,
                lost = 4,
                draw = 6,
                goalDiff = 56
            ),
            OLScoreboardTeam(
                teamInfoId = 7,
                teamName = "Borussia Dortmund",
                shortName = "Dortmund",
                teamIconUrl = "",
                points = 76,
                opponentGoals = 44,
                goals = 81,
                matches = 34,
                won = 23,
                lost = 4,
                draw = 7,
                goalDiff = 37
            )
        )

        val result: List<ScoreboardTeam> = cut.oLScoreboardToScoreboard(matchDays)

        Assertions.assertThat(result[0].id).isEqualTo(40)
        Assertions.assertThat(result[0].name).isEqualTo("FC Bayern")
        Assertions.assertThat(result[0].shortName).isEqualTo("FC Bayern")
        Assertions.assertThat(result[0].logo).isEqualTo("")
        Assertions.assertThat(result[0].points).isEqualTo(78)
        Assertions.assertThat(result[0].opponentGoals).isEqualTo(32)
        Assertions.assertThat(result[0].goals).isEqualTo(88)
        Assertions.assertThat(result[0].matches).isEqualTo(34)
        Assertions.assertThat(result[0].won).isEqualTo(24)
        Assertions.assertThat(result[0].lost).isEqualTo(4)
        Assertions.assertThat(result[0].draw).isEqualTo(6)
        Assertions.assertThat(result[0].goalDiff).isEqualTo(56)

        Assertions.assertThat(result[1].id).isEqualTo(7)
        Assertions.assertThat(result[1].name).isEqualTo("Borussia Dortmund")
        Assertions.assertThat(result[1].shortName).isEqualTo("Dortmund")
        Assertions.assertThat(result[1].logo).isEqualTo("")
        Assertions.assertThat(result[1].points).isEqualTo(76)
        Assertions.assertThat(result[1].opponentGoals).isEqualTo(44)
        Assertions.assertThat(result[1].goals).isEqualTo(81)
        Assertions.assertThat(result[1].matches).isEqualTo(34)
        Assertions.assertThat(result[1].won).isEqualTo(23)
        Assertions.assertThat(result[1].lost).isEqualTo(4)
        Assertions.assertThat(result[1].draw).isEqualTo(7)
        Assertions.assertThat(result[1].goalDiff).isEqualTo(37)

    }
}