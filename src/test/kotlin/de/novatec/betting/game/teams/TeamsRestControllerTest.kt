package de.novatec.betting.game.teams

import de.novatec.betting.game.model.Team
import de.novatec.betting.game.model.Teams
import io.mockk.every
import io.mockk.mockk
import io.quarkus.test.Mock
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.`when`
import io.restassured.RestAssured.given
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import utils.JsonMatcher
import utils.MutableClock
import javax.enterprise.inject.Produces
import javax.inject.Inject
import javax.ws.rs.core.MediaType

val teamsService: TeamsService = mockk()

@QuarkusTest
class TeamsRestControllerTest {

    @Produces
    @Mock
    fun teamsService(): TeamsService = teamsService

    @Inject
    lateinit var clock: MutableClock

    @BeforeEach
    fun setTime() {
        clock.setFixedTime("2017-08-20T12:34:56.789Z")
    }

    @Test
    fun `GET - all teams for a given season`() {

        val expectedResponse = """
            {
                "teams" : [
                    { 
                        "id": 16,
                        "name": "VfB Stuttgart",
                        "shortName": "Stuttgart",
                        "logo": "logo-vfb"
                    }, 
                    {
                        "id": 65,
                        "name": "1. FC Köln",
                        "shortName": "FC Köln",
                        "logo": "logo-köln"                        
                    }
                 ]
            }
        """

        every { teamsService.getTeams(2020) } returns Teams(
            listOf(
                Team(16, "VfB Stuttgart", "Stuttgart", "logo-vfb"),
                Team(65, "1. FC Köln", "FC Köln", "logo-köln")
            )
        )

        given().`when`()["/teams/2020"].then()
            .statusCode(200)
            .contentType(MediaType.APPLICATION_JSON)
            .body(JsonMatcher.jsonEqualTo(expectedResponse))
    }

    @Test
    fun `404 - team not available for invalid season`() {

        val requestBody = """
                """

        val expectedResponse = """
            {
                "status": 404,
                "error": "No Such Element Exception",
                "timestamp": "2017-08-20T12:34:56.789Z",
                "message": "Year 2022 is not valid."
            }
        """

        given().contentType(MediaType.APPLICATION_JSON).body(requestBody)
            `when`()["/teams/2022"].then()
            .statusCode(404)
            .body(JsonMatcher.jsonEqualTo(expectedResponse))
    }

    @Test
    fun `404 - team not available for malformed id`() {

        val requestBody = """
                """

        given().contentType(MediaType.APPLICATION_JSON).body(requestBody)
        `when`()["/teams/malformed-id"].then()
            .statusCode(404)
    }

}
