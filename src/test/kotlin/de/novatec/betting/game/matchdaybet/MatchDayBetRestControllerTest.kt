package de.novatec.betting.game.matchdaybet

import de.novatec.betting.game.model.MatchBet
import de.novatec.betting.game.model.MatchDayBet
import de.novatec.betting.game.model.Score
import io.mockk.every
import io.mockk.mockk
import io.quarkus.test.Mock
import io.quarkus.test.junit.QuarkusTest
import io.quarkus.test.security.TestSecurity
import io.restassured.RestAssured.given
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import utils.JsonMatcher
import utils.MutableClock
import utils.classification.IntegrationTest
import javax.inject.Inject
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

val matchDayBetService: MatchDayBetService = mockk()

@IntegrationTest
@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MatchDayBetRestControllerTest {

    @Produces
    @Mock
    fun matchDayBetService() = matchDayBetService

    @Inject
    lateinit var clock: MutableClock

    @BeforeAll
    fun setupMocks() {
        clock.setFixedTime("2021-01-01T01:01:00Z")
    }

    @Test
    @TestSecurity(user = "player")
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

        every { matchDayBetService.getMatchDayBet(any(), any()) } returns matchDayBet

        given().`when`()["/matchdaybets/1010"].then()
            .statusCode(200)
            .contentType(MediaType.APPLICATION_JSON)
            .body(JsonMatcher.jsonEqualTo(expectedResponse))
    }

    @Test
    @TestSecurity(user = "player")
    fun `POST - add a match day bet`() {

        val request = """
            {
                "matchDayId" : 1010,
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
                .body(request)
                .`when`()
                .post("/matchdaybets/")
                .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_JSON)
                .body(JsonMatcher.jsonEqualTo(expectedResponse))
    }

    @Test
    @TestSecurity(user = "player")
    fun `POST - add a match day bet - invalid value`() {

        val request = """
            {
                "matchDayId" : 1010,
                "matchBets" : [ 
                    { 
                        "matchId" : 111,
                        "result" : {
                            "goalsHome" : 100,
                            "goalsGuest" : 1
                        }
                    }
                ]
            }
          """

        val matchBets: List<MatchBet> = listOf(MatchBet(111, Score(3, 1)), MatchBet(112, Score(1, 2)))
        val matchDayBet = MatchDayBet(1010, "player", matchBets)

        every { matchDayBetService.addMatchDayBet(matchDayBet) } returns matchDayBet

        given().contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .`when`()
                .post("/matchdaybets/")
                .then()
                .statusCode(400)
                .contentType(MediaType.APPLICATION_JSON)
                .body(JsonMatcher.jsonEqualTo("""
                    {
                        "status":400,
                        "error":"NotValidException",
                        "timestamp":"2021-01-01T01:01:00Z",
                        "message":"Goals 100 is not valid."
                    }
                """.trimIndent()))
    }
}
