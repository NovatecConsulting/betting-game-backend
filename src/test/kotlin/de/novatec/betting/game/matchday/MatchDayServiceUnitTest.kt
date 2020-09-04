package de.novatec.betting.game.matchday

import de.novatec.betting.game.matchday.model.MatchDayOverview
import de.novatec.betting.game.matchday.tf.MatchDayOverviewTf
import de.novatec.betting.game.openliga.OpenLigaAccessor
import de.novatec.betting.game.openliga.model.OLMatchDay
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkClass
import io.mockk.verify
import org.junit.jupiter.api.Test
import utils.classification.UnitTest

val openLigaAccessor: OpenLigaAccessor = mockk()
val matchDayOverviewTf: MatchDayOverviewTf = mockk()

@UnitTest
class MatchDayServiceUnitTest {

    private val cut = MatchDayService(openLigaAccessor, matchDayOverviewTf)

    @Test
    fun `uses the openliga accessor`() {
        every { openLigaAccessor.getCurrentMatchDay() } returns listOf(mockkClass(OLMatchDay::class))
        cut.getCurrentMatchDay()
        verify { openLigaAccessor.getCurrentMatchDay() }
    }

    @Test
    fun `gets the match day overview for a past season`() {
        val firstMatchOfSpecifiedSeason = mockkClass(OLMatchDay::class)
        val firstMatchOfCurrentMatchDay = mockkClass(OLMatchDay::class)
        val allMatchesOfSpecifiedSeason = listOf(firstMatchOfSpecifiedSeason)
        val currentMatchDayOfCurrentSeason = listOf(firstMatchOfCurrentMatchDay)

        every { openLigaAccessor.getAllMatchesOfSeason("2019") } returns allMatchesOfSpecifiedSeason
        every { openLigaAccessor.getCurrentMatchDay() } returns currentMatchDayOfCurrentSeason
        every { firstMatchOfCurrentMatchDay.leagueName } returns "1. Fußball-Bundesliga 2020/2021"
        every { firstMatchOfSpecifiedSeason.leagueName } returns "1. Fußball-Bundesliga 2019/2020"
        every { firstMatchOfCurrentMatchDay.group?.groupOrderID } returns 17L
        every { matchDayOverviewTf.matchDaysToMatchDayOverview(allMatchesOfSpecifiedSeason) } returns mockkClass(
            MatchDayOverview::class
        )

        cut.getAllMatchesOfSeason("2019")

        verify { matchDayOverviewTf.matchDaysToMatchDayOverview(allMatchesOfSpecifiedSeason) }
    }

    @Test
    fun `gets the match day overview for the current season`() {
        val firstMatchOfSpecifiedSeason = mockkClass(OLMatchDay::class)
        val firstMatchOfCurrentMatchDay = mockkClass(OLMatchDay::class)
        val allMatchesOfSpecifiedSeason = listOf(firstMatchOfSpecifiedSeason)
        val currentMatchDayOfCurrentSeason = listOf(firstMatchOfCurrentMatchDay)

        every { openLigaAccessor.getAllMatchesOfSeason("2019") } returns allMatchesOfSpecifiedSeason
        every { openLigaAccessor.getCurrentMatchDay() } returns currentMatchDayOfCurrentSeason
        every { firstMatchOfCurrentMatchDay.leagueName } returns "1. Fußball-Bundesliga 2019/2020"
        every { firstMatchOfSpecifiedSeason.leagueName } returns "1. Fußball-Bundesliga 2019/2020"
        every { firstMatchOfCurrentMatchDay.group?.groupOrderID } returns 17L
        every {
            matchDayOverviewTf.matchDaysToMatchDayOverview(
                allMatchesOfSpecifiedSeason,
                17L
            )
        } returns mockkClass(MatchDayOverview::class)

        cut.getAllMatchesOfSeason("2019")

        verify { matchDayOverviewTf.matchDaysToMatchDayOverview(allMatchesOfSpecifiedSeason, 17L) }
    }
}
