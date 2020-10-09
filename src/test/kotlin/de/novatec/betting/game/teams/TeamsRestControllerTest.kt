package de.novatec.betting.game.teams

import de.novatec.betting.game.model.Team
import de.novatec.betting.game.model.Teams
import io.mockk.every
import io.mockk.mockk
import io.quarkus.test.Mock
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.junit.jupiter.api.Test
import utils.JsonMatcher
import javax.enterprise.inject.Produces
import javax.ws.rs.core.MediaType

val teamsService: TeamsService = mockk()

@QuarkusTest
class TeamsRestControllerTest {

    @Produces
    @Mock
    fun teamsService(): TeamsService = teamsService

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

        every { teamsService.getTeams("2020") } returns Teams(
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

}