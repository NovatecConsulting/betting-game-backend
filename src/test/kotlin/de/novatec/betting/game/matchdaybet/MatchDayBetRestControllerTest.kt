package de.novatec.betting.game.matchdaybet

import de.novatec.betting.game.model.MatchBet
import de.novatec.betting.game.model.MatchDayBet
import de.novatec.betting.game.model.Score
import io.mockk.every
import io.mockk.mockk
import io.quarkus.test.Mock
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.junit.jupiter.api.Test
import utils.JsonMatcher
import utils.classification.IntegrationTest
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

val matchDayBetService: MatchDayBetService = mockk()

@IntegrationTest
@QuarkusTest
class MatchDayBetRestControllerTest {

    @Produces
    @Mock
    fun matchDayBetService() = matchDayBetService

    @Test
    fun `GET - the response contains a match day bet`() {
        val expectedResponse = """
            {
                "matchDayId" : 1010,
                "userName" : "player",
                "matchBets" : [ 
                    { 
                        "matchId" : 111,
                        "result" : {
                            "goalsHome" : 3,
                            "goalsGuest" : 1
                        }
                    },
                   { 
                        "matchId" : 112,
                        "result" : {
                            "goalsHome" : 1,
                            "goalsGuest" : 2
                        }
                    } 
                ]
            }
          """
        val matchBets: List<MatchBet> = listOf(MatchBet(111, Score(3, 1)), MatchBet(112, Score(1, 2)))
        val matchDayBet = MatchDayBet(1010, "player", matchBets)

        every { matchDayBetService.getMatchDayBet(any()) } returns matchDayBet

        given().`when`()["/matchdaybets/1010"].then()
            .statusCode(200)
            .contentType(MediaType.APPLICATION_JSON)
            .body(JsonMatcher.jsonEqualTo(expectedResponse))
    }

    @Test
    fun `POST - add a match day bet`() {

        val expectedResponse = """
            {
                "matchDayId" : 1010,
                "userName" : "player",
                "matchBets" : [ 
                    { 
                        "matchId" : 111,
                        "result" : {
                            "goalsHome" : 3,
                            "goalsGuest" : 1
                        }
                    },
                   { 
                        "matchId" : 112,
                        "result" : {
                            "goalsHome" : 1,
                            "goalsGuest" : 2
                        }
                    } 
                ]
            }
          """
        val matchBets: List<MatchBet> = listOf(MatchBet(111, Score(3, 1)), MatchBet(112, Score(1, 2)))
        val matchDayBet = MatchDayBet(1010, "player", matchBets)

        every { matchDayBetService.addMatchDayBet(matchDayBet) } returns matchDayBet

        given().contentType(MediaType.APPLICATION_JSON)
            .body(matchDayBet)
            .`when`()
            .post("/matchdaybets/")
            .then()
            .statusCode(200)
            .contentType(MediaType.APPLICATION_JSON)
            .body(JsonMatcher.jsonEqualTo(expectedResponse))
    }

}