package de.novatec.betting.game.scoreboard

import de.novatec.betting.game.model.ScoreboardTeam
import io.mockk.every
import io.mockk.mockk
import io.quarkus.test.Mock
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.junit.jupiter.api.Test
import utils.JsonMatcher
import utils.classification.IntegrationTest
import javax.enterprise.inject.Produces
import javax.ws.rs.core.MediaType

val scoreboardService: ScoreboardService = mockk()

@IntegrationTest
@QuarkusTest
class ScoreboardRestControllerIntTest {

    @Produces
    @Mock
    fun scoreBoardService(): ScoreboardService = scoreboardService

    @ConfigProperty(name = "openliga.currentSeason")
    lateinit var currentSeason: String

    @Test
    fun `GET - the response contains the specified season scoreboard`() {
        val expectedResponse = """
                        [
                {
                    "id": 40,
                    "name": "FC Bayern",
                    "shortName": "FC Bayern",
                    "logo": "",
                    "points": 78,
                    "opponentGoals": 32,
                    "goals": 88,
                    "matches": 34,
                    "won": 24,
                    "lost": 4,
                    "draw": 6,
                    "goalDiff": 56
                },
                {
                    "id": 7,
                    "name": "Borussia Dortmund",
                    "shortName": "Dortmund",
                    "logo": "",
                    "points": 76,
                    "opponentGoals": 44,
                    "goals": 81,
                    "matches": 34,
                    "won": 23,
                    "lost": 4,
                    "draw": 7,
                    "goalDiff": 37
                }
            ]
        """

        val matchDays: List<ScoreboardTeam> = listOf(
            ScoreboardTeam(
                id = 40,
                name = "FC Bayern",
                shortName = "FC Bayern",
                logo = "",
                points = 78,
                opponentGoals = 32,
                goals = 88,
                matches = 34,
                won = 24,
                lost = 4,
                draw = 6,
                goalDiff = 56
            ),
            ScoreboardTeam(
                id = 7,
                name = "Borussia Dortmund",
                shortName = "Dortmund",
                logo = "",
                points = 76,
                opponentGoals = 44,
                goals = 81,
                matches = 34,
                won = 23,
                lost = 4,
                draw = 7,
                goalDiff = 37
            )
        )

        every { scoreboardService.getScoreboard(2019) } returns matchDays

        given()
            .`when`()["/scoreboard/2019"]
            .then()
            .statusCode(200)
            .contentType(MediaType.APPLICATION_JSON)
            .body(JsonMatcher.jsonEqualTo(expectedResponse))
    }

    @Test
    fun `GET - the response contains the current season scoreboard`() {
        val expectedResponse = """
                        [
                {
                    "id": 40,
                    "name": "FC Bayern",
                    "shortName": "FC Bayern",
                    "logo": "",
                    "points": 78,
                    "opponentGoals": 32,
                    "goals": 88,
                    "matches": 34,
                    "won": 24,
                    "lost": 4,
                    "draw": 6,
                    "goalDiff": 56
                },
                {
                    "id": 7,
                    "name": "Borussia Dortmund",
                    "shortName": "Dortmund",
                    "logo": "",
                    "points": 76,
                    "opponentGoals": 44,
                    "goals": 81,
                    "matches": 34,
                    "won": 23,
                    "lost": 4,
                    "draw": 7,
                    "goalDiff": 37
                }
            ]
        """

        val matchDays: List<ScoreboardTeam> = listOf(
            ScoreboardTeam(
                id = 40,
                name = "FC Bayern",
                shortName = "FC Bayern",
                logo = "",
                points = 78,
                opponentGoals = 32,
                goals = 88,
                matches = 34,
                won = 24,
                lost = 4,
                draw = 6,
                goalDiff = 56
            ),
            ScoreboardTeam(
                id = 7,
                name = "Borussia Dortmund",
                shortName = "Dortmund",
                logo = "",
                points = 76,
                opponentGoals = 44,
                goals = 81,
                matches = 34,
                won = 23,
                lost = 4,
                draw = 7,
                goalDiff = 37
            )
        )

        every { scoreboardService.getScoreboard(currentSeason.toInt()) } returns matchDays

        given()
            .`when`()["/scoreboard/current"]
            .then()
            .statusCode(200)
            .contentType(MediaType.APPLICATION_JSON)
            .body(JsonMatcher.jsonEqualTo(expectedResponse))
    }
}