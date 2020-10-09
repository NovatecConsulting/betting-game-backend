package de.novatec.betting.game.matchday.tf

import de.novatec.betting.game.model.MatchDayOverview
import de.novatec.betting.game.openliga.model.OLGroup
import de.novatec.betting.game.openliga.model.OLMatchDay
import de.novatec.betting.game.openliga.model.OLTeam
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import utils.classification.UnitTest
import java.time.ZonedDateTime

@UnitTest
class MatchDayOverviewTfUnitTest {

    private val cut = MatchDayOverviewTf()

    @Test
    fun `creates the match day overview`() {
        // List of matches are not in order
        val matchDays: List<OLMatchDay> = listOf(
            OLMatchDay(
                matchID = 2L,
                matchDateTimeUTC = ZonedDateTime.parse("2020-09-26T13:30Z"),
                group = OLGroup("2. Spieltag", 2L, 1001L),
                team1 = OLTeam(teamName = "Eintracht Frankfurt"),
                team2 = OLTeam(teamName = "1. FC Köln"),
                matchResults = listOf(),
                matchIsFinished = false
            ),
            OLMatchDay(
                matchID = 1L,
                matchDateTimeUTC = ZonedDateTime.parse("2020-09-19T13:30Z"),
                group = OLGroup("1. Spieltag", 1L, 1000L),
                team1 = OLTeam(teamName = "FC Bayern München"),
                team2 = OLTeam(teamName = "Borussia Dortmund"),
                matchResults = listOf(),
                matchIsFinished = false
            ),
            OLMatchDay(
                matchID = 2L,
                matchDateTimeUTC = ZonedDateTime.parse("2020-09-28T13:30Z"),
                group = OLGroup("2. Spieltag", 2L, 1001L),
                team1 = OLTeam(teamName = "VfB Stuttgart"),
                team2 = OLTeam(teamName = "SC Freiburg"),
                matchResults = listOf(),
                matchIsFinished = false
            ),
            OLMatchDay(
                matchID = 1L,
                matchDateTimeUTC = ZonedDateTime.parse("2020-09-21T13:30Z"),
                group = OLGroup("1. Spieltag", 1L, 1000L),
                team1 = OLTeam(teamName = "RB Leipzig"),
                team2 = OLTeam(teamName = "1. FSV Mainz 05"),
                matchResults = listOf(),
                matchIsFinished = false
            ),
            OLMatchDay(
                matchID = 1L,
                matchDateTimeUTC = ZonedDateTime.parse("2020-09-20T13:30Z"),
                group = OLGroup("1. Spieltag", 1L, 1000L),
                team1 = OLTeam(teamName = "Borussia Mönchengladbach"),
                team2 = OLTeam(teamName = "FC Schalke 04"),
                matchResults = listOf(),
                matchIsFinished = false
            ),
            OLMatchDay(
                matchID = 2L,
                matchDateTimeUTC = ZonedDateTime.parse("2020-09-27T13:30Z"),
                group = OLGroup("2. Spieltag", 2L, 1001L),
                team1 = OLTeam(teamName = "VfB Stuttgart"),
                team2 = OLTeam(teamName = "SC Freiburg"),
                matchResults = listOf(),
                matchIsFinished = false
            )
        )

        val result: MatchDayOverview = cut.matchDaysToMatchDayOverview(matchDays)

        assertThat(result.current).isEqualTo(0L)
        assertThat(result.matchDays?.size).isEqualTo(2)

        assertThat(result.matchDays?.get(0)?.id).isEqualTo(1L)
        assertThat(result.matchDays?.get(0)?.name).isEqualTo("1. Spieltag")
        assertThat(result.matchDays?.get(0)?.firstMatchStartDateTime).isEqualTo("2020-09-19T13:30Z")
        assertThat(result.matchDays?.get(0)?.lastMatchStartDateTime).isEqualTo("2020-09-21T13:30Z")

        assertThat(result.matchDays?.get(1)?.id).isEqualTo(2L)
        assertThat(result.matchDays?.get(1)?.name).isEqualTo("2. Spieltag")
        assertThat(result.matchDays?.get(1)?.firstMatchStartDateTime).isEqualTo("2020-09-26T13:30Z")
        assertThat(result.matchDays?.get(1)?.lastMatchStartDateTime).isEqualTo("2020-09-28T13:30Z")
    }

    @Test
    fun `can handle an empty list as input`() {
        val result: MatchDayOverview = cut.matchDaysToMatchDayOverview(emptyList())
        assertThat(result.matchDays?.size).isEqualTo(0)
    }

    @Test
    fun `sets the current match day if given`() {
        val result: MatchDayOverview = cut.matchDaysToMatchDayOverview(emptyList(), 17L)
        assertThat(result.current).isEqualTo(17L)
    }
}
