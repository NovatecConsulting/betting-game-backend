package de.novatec.betting.game.matchday.tf

import de.novatec.betting.game.matchday.model.MatchDay
import de.novatec.betting.game.matchday.model.MatchDayOverview
import de.novatec.betting.game.openliga.model.OLGroup
import de.novatec.betting.game.openliga.model.OLMatchDay
import de.novatec.betting.game.openliga.model.OLTeam
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import utils.classification.UnitTest
import java.time.ZonedDateTime

@UnitTest
class MatchDayTfTest {

    private val cut = MatchDayTf()

    @Test
    fun `transform oLmatches to matchday overview `() {

        val matchDays: List<OLMatchDay> = listOf(
            OLMatchDay(
                matchID = 2L,
                matchDateTimeUTC = ZonedDateTime.parse("2020-09-26T13:30Z"),
                group = OLGroup("2. Spieltag", 2L, 1001L),
                team1 = OLTeam(teamName = "Eintracht Frankfurt"),
                team2 = OLTeam(teamName = "1. FC Köln"),
                matchResults = listOf(),
                matchIsFinished = false
            )
        )

        val result: MatchDay = cut.oLMatchesToMatchDayOverview(matchDays)

        Assertions.assertThat(result.id).isEqualTo(2L)
        Assertions.assertThat(result.name).isEqualTo("2. Spieltag")
        Assertions.assertThat(result.matches?.get(0)?.home?.name).isEqualTo("Eintracht Frankfurt")
        Assertions.assertThat(result.matches?.get(0)?.guest?.name).isEqualTo("1. FC Köln")
        Assertions.assertThat(result.matches?.get(0)?.matchIsFinished).isEqualTo(false)
    }
}