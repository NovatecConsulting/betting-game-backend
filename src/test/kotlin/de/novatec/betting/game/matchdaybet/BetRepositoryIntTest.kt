package de.novatec.betting.game.matchdaybet

import de.novatec.betting.game.matchdaybet.entity.Bet
import io.quarkus.test.common.QuarkusTestResource
import io.quarkus.test.h2.H2DatabaseTestResource
import io.quarkus.test.junit.QuarkusTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import utils.classification.IntegrationTest
import javax.inject.Inject
import javax.transaction.Transactional

@Transactional
@IntegrationTest
@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource::class)
internal class BetRepositoryIntTest {

    @Inject
    private lateinit var repository: BetRepository

    @AfterEach
    fun cleanUp() {
        repository.deleteAll()
    }

    @Test
    fun `find By MatchDayId`() {
        val betOnDay1 = Bet(matchDayId = 1, matchId = 1, userName = "user1", goalsHome = 3, goalsGuest = 0)
        val betOnDay2 = Bet(matchDayId = 2, matchId = 1, userName = "user2", goalsHome = 3, goalsGuest = 0)
        repository.persist(betOnDay1, betOnDay2)

        val bet = repository.findByMatchDayId(1)

        assertThat(bet).hasSize(1).contains(betOnDay1)
    }

    @Test
    fun `find by MatchDayId and User`() {
        val bet1 = Bet(matchDayId = 1, matchId = 1, userName = "user1", goalsHome = 3, goalsGuest = 0)
        val bet2 = Bet(matchDayId = 1, matchId = 1, userName = "user2", goalsHome = 3, goalsGuest = 0)
        repository.persist(bet1, bet2)

        val bet = repository.findByMatchDayIdAndUser(1, "user1")

        assertThat(bet).hasSize(1).contains(bet1)
    }

    @Test
    fun `find by unique key`() {
        val bet1 = Bet(matchDayId = 1, matchId = 1, userName = "user1", goalsHome = 3, goalsGuest = 0)
        val bet2 = Bet(matchDayId = 1, matchId = 1, userName = "user2", goalsHome = 3, goalsGuest = 0)
        val bet3 = Bet(matchDayId = 1, matchId = 2, userName = "user1", goalsHome = 3, goalsGuest = 0)
        repository.persist(bet1, bet2, bet3)

        val bet = repository.findByUniqueKey(1, "user1", 1)

        assertThat(bet).isEqualTo(bet1)
    }

}