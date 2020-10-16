package de.novatec.betting.game.matchdaybet

import de.novatec.betting.game.matchdaybet.entity.Bet
import de.novatec.betting.game.matchdaybet.tf.MatchDayBetTf
import de.novatec.betting.game.model.MatchDayBet
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkClass
import io.mockk.verify
import org.junit.jupiter.api.Test
import utils.classification.UnitTest

val betRepository: BetRepository = mockk(relaxUnitFun = true)
val matchDayBetTf: MatchDayBetTf = mockk()

@UnitTest
class MatchDayBetServiceUnitTest {

    private val matchDayBetService = MatchDayBetService(betRepository, matchDayBetTf)

    @Test
    fun `get matchday bets for a matchday`() {

        val bets = listOf(mockkClass(Bet::class), mockkClass(Bet::class))
        val matchDayBet = mockkClass(MatchDayBet::class)

        every { betRepository.findByMatchDayIdAndUser(1010, "player") } returns bets
        every { matchDayBetTf.betsToMatchDayBet(bets) } returns matchDayBet
        every { matchDayBet.matchDayId } returns 1010
        every { matchDayBet.userName } returns "player"

        matchDayBetService.getMatchDayBet(1010)

        verify { matchDayBetTf.betsToMatchDayBet(bets) }
    }

    @Test
    fun `add matchday bets for a matchday`() {

        val bets = listOf(mockkClass(Bet::class, relaxed = true), mockkClass(Bet::class, relaxed = true))
        val matchDayBet = mockkClass(MatchDayBet::class, relaxed = true)

        every { betRepository.findByUniqueKey(any(), any(), any()) } returns null
        every { betRepository.findByMatchDayIdAndUser(any(), any()) } returns bets
        every { matchDayBetTf.betsToMatchDayBet(bets) } returns matchDayBet
        every { matchDayBetTf.matchDayBetToBets(matchDayBet) } returns bets

        matchDayBetService.addMatchDayBet(matchDayBet)

        verify(exactly = 2) { betRepository.findByUniqueKey(any(), any(), any()) }
        verify { matchDayBetTf.betsToMatchDayBet(bets) }
        verify { matchDayBetTf.matchDayBetToBets(matchDayBet) }
    }

}