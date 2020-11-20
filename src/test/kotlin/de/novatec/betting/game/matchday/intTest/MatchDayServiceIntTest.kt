package de.novatec.betting.game.matchday.intTest

import de.novatec.betting.game.matchday.MatchDayService
import de.novatec.betting.game.matchday.tf.MatchDayOverviewTf
import de.novatec.betting.game.matchday.tf.MatchDayTf
import de.novatec.betting.game.openliga.OpenLigaAccessor
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.mockk
import io.quarkus.test.Mock
import io.quarkus.test.junit.QuarkusTest
import org.junit.jupiter.api.Test
import javax.enterprise.inject.Default
import javax.enterprise.inject.Produces
import javax.inject.Inject

val openLigaAccessor: OpenLigaAccessor = mockk()
val matchDayOverviewTf: MatchDayOverviewTf = mockk()
val matchDayTf: MatchDayTf = mockk()

//@IntegrationTest
@QuarkusTest
class MatchDayServiceIntTest {


//    val matchDayService: MatchDayService = MatchDayService(openLigaAccessor, matchDayOverviewTf, matchDayTf)

//    @InjectMockKs
//    private val matchDayService: MatchDayService(openLigaAccessor, matchDayOverviewTf, matchDayTf)

    @Produces
    @Mock
    fun openLigaAccessor(): OpenLigaAccessor = openLigaAccessor

    @Produces
    @Mock
    fun matchDayOverviewTf(): MatchDayOverviewTf = matchDayOverviewTf

    @Produces
    @Mock
    fun matchDayTf(): MatchDayTf = matchDayTf

    @InjectMockKs
    @field: Default
    lateinit var matchDayService: MatchDayService

//    @Mock
//    @Produces
//    val openLigaAccessor: OpenLigaAccessor = mockk()
//
//    @Mock()
//    @Produces
//    val matchDayOverviewTf: MatchDayOverviewTf = mockk()
//
//    @Mock
//    @Produces
//    val matchDayTf: MatchDayTf = mockk()

    @Test
    fun `chache works`() {
//        val olMatches = listOf<OLMatchDay>(mockk())
//
//        every { openLigaAccessor.getOLMatchesOfCurrentMatchday() } returns olMatches
//        every { matchDayTf.oLMatchesToMatchDayOverview(olMatches) } returns mockk()
//        every { matchDayService.getCurrentMatchDay() } returns mockk()


        matchDayService.abc()
//        matchDayService.getCurrentMatchDay()
//
//        verify(exactly = 2) { openLigaAccessor.getOLMatchesOfCurrentMatchday() }
    }
}
