package de.novatec.betting.game.matchday


import de.novatec.betting.game.model.MatchDay
import de.novatec.betting.game.model.MatchDay.Match
import de.novatec.betting.game.model.MatchDay.Result
import de.novatec.betting.game.model.MatchDayOverview
import de.novatec.betting.game.model.Score
import de.novatec.betting.game.model.Team
import io.mockk.every
import io.mockk.mockk
import io.quarkus.test.Mock
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import utils.JsonMatcher
import utils.MutableClock
import utils.classification.IntegrationTest
import java.time.ZonedDateTime
import javax.enterprise.inject.Produces
import javax.inject.Inject
import javax.ws.rs.core.MediaType

val matchDayService: MatchDayService = mockk()

@IntegrationTest
@QuarkusTest
class MatchDayRestControllerIntTest {


    @Inject
    lateinit var clock: MutableClock

    @Produces
    @Mock
    fun matchDayService(): MatchDayService = matchDayService

    @BeforeEach
    fun setTime() {
        clock.setFixedTime("2017-08-20T12:34:56.789Z")
    }

    @Test
    fun `GET - the response contains the current match day`() {
        val expectedResponse = """
                {
                    "id": 1,
                    "name": "1. Spieltag",
                    "firstMatchStartDateTime": "2020-09-18T20:30+02:00[Europe/Berlin]",
                    "lastMatchStartDateTime": "2020-09-20T18:00+02:00[Europe/Berlin]",
                    "matches": [
                        {
                            "id": 58577,
                            "home": {
                                "id": 40,
                                "name": "FC Bayern",
                                "shortName": "FC Bayern",
                                "logo": "logo"
                            },
                            "guest": {
                                "id": 9,
                                "name": "FC Schalke 04",
                                "shortName": "Schalke 04",
                                "logo": "logo"
                            },
                            "kickOffDateTime": "2020-09-18T20:30+02:00[Europe/Berlin]",
                            "matchIsFinished": false,
                            "result": {
                                "final": {
                                    "goalsHome": 3,
                                    "goalsGuest": 0
                                },
                                "halftime": {
                                    "goalsHome": 2,
                                    "goalsGuest": 0
                                }
                            }
                        }
                    ]
                }
        """

        val matchesList = mutableListOf<Match>()
        val match = Match(
            id = 58577,
            home = Team(
                id = 40,
                name = "FC Bayern",
                shortName = "FC Bayern",
                logo = "logo"
            ),
            guest = Team(
                id = 9,
                name = "FC Schalke 04",
                shortName = "Schalke 04",
                logo = "logo"
            ),
            kickOffDateTime = ZonedDateTime.parse("2020-09-18T20:30+02:00[Europe/Berlin]"),
            matchIsFinished = false,
            result = Result(
                final = Score(
                    goalsHome = 3,
                    goalsGuest = 0
                ),
                halftime = Score(
                    goalsHome = 2,
                    goalsGuest = 0
                )
            )
        )

        matchesList.add(match)

        val currentMatchDay = MatchDay(
            id = 1,
            name = "1. Spieltag",
            firstMatchStartDateTime = ZonedDateTime.parse("2020-09-18T20:30+02:00[Europe/Berlin]"),
            lastMatchStartDateTime = ZonedDateTime.parse("2020-09-20T18:00+02:00[Europe/Berlin]"),
            matches = matchesList
        )

        every { matchDayService.getCurrentMatchDay() } returns currentMatchDay

        given()
            .`when`()["/matchdays/current"]
            .then()
            .statusCode(200)
            .contentType(MediaType.APPLICATION_JSON)
            .body(JsonMatcher.jsonEqualTo(expectedResponse))
    }

    @Test
    fun `GET - the response contains the specific match day and season`() {

        val expectedResponse = """
                {
                    "id": 1,
                    "name": "1. Spieltag",
                    "firstMatchStartDateTime": "2019-08-16T20:30+02:00[Europe/Berlin]",
                    "lastMatchStartDateTime": "2019-08-18T18:00+02:00[Europe/Berlin]",
                    "matches": [
                        {
                            "id": 58577,
                            "home": {
                                "id": 40,
                                "name": "FC Bayern",
                                "shortName": "FC Bayern",
                                "logo": "logo"
                            },
                            "guest": {
                                "id": 9,
                                "name": "FC Schalke 04",
                                "shortName": "Schalke 04",
                                "logo": "logo"
                            },
                            "kickOffDateTime": "2019-08-16T20:30+02:00[Europe/Berlin]",
                            "matchIsFinished": false,
                            "result": {
                                "final": {
                                    "goalsHome": 2,
                                    "goalsGuest": 0
                                },
                                "halftime": {
                                    "goalsHome": 3,
                                    "goalsGuest": 0
                                }
                            }
                        }
                    ]
                }
        """

        val matchesList = mutableListOf<Match>()
        val match = Match(
            id = 58577,
            home = Team(
                id = 40,
                name = "FC Bayern",
                shortName = "FC Bayern",
                logo = "logo"
            ),
            guest = Team(
                id = 9,
                name = "FC Schalke 04",
                shortName = "Schalke 04",
                logo = "logo"
            ),
            kickOffDateTime = ZonedDateTime.parse("2019-08-16T20:30+02:00[Europe/Berlin]"),
            matchIsFinished = false,
            result = Result(
                final = Score(
                    goalsHome = 2,
                    goalsGuest = 0
                ),
                halftime = Score(
                    goalsHome = 3,
                    goalsGuest = 0
                )
            )
        )

        matchesList.add(match)

        val specificMatchDay = MatchDay(
            id = 1,
            name = "1. Spieltag",
            firstMatchStartDateTime = ZonedDateTime.parse("2019-08-16T20:30+02:00[Europe/Berlin]"),
            lastMatchStartDateTime = ZonedDateTime.parse("2019-08-18T18:00+02:00[Europe/Berlin]"),
            matches = matchesList
        )

        every { matchDayService.getSpecificMatchDayOfSeason(2019, 1) } returns specificMatchDay

        given()
            .`when`()["/matchdays/2019/1"]
            .then()
            .statusCode(200)
            .contentType(MediaType.APPLICATION_JSON)
            .body(JsonMatcher.jsonEqualTo(expectedResponse))
    }

    @Test
    fun `404  - the response contains the specific match day and malformed season`() {

        val requestBody = """
                """

        given()
            .contentType(MediaType.APPLICATION_JSON).body(requestBody)
            .`when`()["/matchdays/2019/malformed-id"]
            .then()
            .statusCode(404)
    }

    @Test
    fun `404  - the response contains a malformed match day and regular season`() {

        val requestBody = """
                """

        given()
            .contentType(MediaType.APPLICATION_JSON).body(requestBody)
            .`when`()["/matchdays/2019/malformed-id"]
            .then()
            .statusCode(404)
    }


    @Test
    fun `404  - the response contains not valid matchday`() {

        val requestBody = """
                """

        val expectedResponse = """
            {
                "status": 404,
                "error": "No Such Element Exception",
                "timestamp": "2017-08-20T12:34:56.789Z",
                "message": "Year 2000 is not valid."
            }
        """

        given()
            .contentType(MediaType.APPLICATION_JSON).body(requestBody)
            .`when`()["/matchdays/2000/34"]
            .then()
            .statusCode(404)
            .body(JsonMatcher.jsonEqualTo(expectedResponse))
    }

    @Test
    fun `404  - the response contains not valid season`() {

        val requestBody = """
                """

        val expectedResponse = """
            {
                "status": 404,
                "error": "No Such Element Exception",
                "timestamp": "2017-08-20T12:34:56.789Z",
                "message": "Matchday 35 is not valid."
            }
        """

        given()
            .contentType(MediaType.APPLICATION_JSON).body(requestBody)
            .`when`()["/matchdays/2019/35"]
            .then()
            .statusCode(404)
            .body(JsonMatcher.jsonEqualTo(expectedResponse))
    }

    @Test
    fun `GET - the response contains the match day overview of the current season`() {
        val expectedResponse = """
            {
                "current": 1,
                "matchDays": [
                    {
                        "id": 1,
                        "name": "1. Spieltag",
                        "firstMatchStartDateTime": "2020-09-19T13:30Z",
                        "lastMatchStartDateTime": "2020-09-19T13:30Z"
                    },
                    {
                        "id": 2,
                        "name": "2. Spieltag",
                        "firstMatchStartDateTime": "2020-09-26T13:30Z",
                        "lastMatchStartDateTime": "2020-09-26T13:30Z"
                    },
                    {
                        "id": 3,
                        "name": "3. Spieltag",
                        "firstMatchStartDateTime": "2020-10-03T13:30Z",
                        "lastMatchStartDateTime": "2020-10-03T13:30Z"
                    },
                    {
                        "id": 4,
                        "name": "4. Spieltag",
                        "firstMatchStartDateTime": "2020-10-17T13:30Z",
                        "lastMatchStartDateTime": "2020-10-17T13:30Z"
                    }
                ]
            }
        """

        val matchDayOverview = MatchDayOverview(
            current = 1L,
            matchDays = listOf(
                MatchDay(
                    id = 1,
                    name = "1. Spieltag",
                    firstMatchStartDateTime = ZonedDateTime.parse("2020-09-19T13:30Z"),
                    lastMatchStartDateTime = ZonedDateTime.parse("2020-09-19T13:30Z"),
                    matches = null
                ),
                MatchDay(
                    id = 2,
                    name = "2. Spieltag",
                    firstMatchStartDateTime = ZonedDateTime.parse("2020-09-26T13:30Z"),
                    lastMatchStartDateTime = ZonedDateTime.parse("2020-09-26T13:30Z"),
                    matches = null
                ),
                MatchDay(
                    id = 3,
                    name = "3. Spieltag",
                    firstMatchStartDateTime = ZonedDateTime.parse("2020-10-03T13:30Z"),
                    lastMatchStartDateTime = ZonedDateTime.parse("2020-10-03T13:30Z"),
                    matches = null
                ),
                MatchDay(
                    id = 4,
                    name = "4. Spieltag",
                    firstMatchStartDateTime = ZonedDateTime.parse("2020-10-17T13:30Z"),
                    lastMatchStartDateTime = ZonedDateTime.parse("2020-10-17T13:30Z"),
                    matches = null
                )
            )
        )

        every { matchDayService.getAllMatchesOfCurrentSeason() } returns matchDayOverview

        given()
            .`when`()["/matchdays/current-season"]
            .then()
            .statusCode(200)
            .contentType(MediaType.APPLICATION_JSON)
            .body(JsonMatcher.jsonEqualTo(expectedResponse))
    }

    @Test
    fun `GET - the response contains the match day overview of a specific season`() {
        val expectedResponse = """
            {
                "current": 0,
                "matchDays": [
                    {
                        "id": 1,
                        "name": "1. Spieltag",
                        "firstMatchStartDateTime": "2019-08-16T18:30Z",
                        "lastMatchStartDateTime": "2019-08-18T16:00Z"
                    },
                    {
                        "id": 2,
                        "name": "2. Spieltag",
                        "firstMatchStartDateTime": "2019-08-23T18:30Z",
                        "lastMatchStartDateTime": "2019-08-25T16:00Z"
                    },
                    {
                        "id": 3,
                        "name": "3. Spieltag",
                        "firstMatchStartDateTime": "2019-08-30T18:30Z",
                        "lastMatchStartDateTime": "2019-09-01T16:00Z"
                    },
                    {
                        "id": 4,
                        "name": "4. Spieltag",
                        "firstMatchStartDateTime": "2019-09-13T18:30Z",
                        "lastMatchStartDateTime": "2019-09-15T16:00Z"
                    }
                ]
            }
        """

        val matchDayOverview = MatchDayOverview(
            current = 0L,
            matchDays = listOf(
                MatchDay(
                    id = 1,
                    name = "1. Spieltag",
                    firstMatchStartDateTime = ZonedDateTime.parse("2019-08-16T18:30Z"),
                    lastMatchStartDateTime = ZonedDateTime.parse("2019-08-18T16:00Z"),
                    matches = null
                ),
                MatchDay(
                    id = 2,
                    name = "2. Spieltag",
                    firstMatchStartDateTime = ZonedDateTime.parse("2019-08-23T18:30Z"),
                    lastMatchStartDateTime = ZonedDateTime.parse("2019-08-25T16:00Z"),
                    matches = null
                ),
                MatchDay(
                    id = 3,
                    name = "3. Spieltag",
                    firstMatchStartDateTime = ZonedDateTime.parse("2019-08-30T18:30Z"),
                    lastMatchStartDateTime = ZonedDateTime.parse("2019-09-01T16:00Z"),
                    matches = null
                ),
                MatchDay(
                    id = 4,
                    name = "4. Spieltag",
                    firstMatchStartDateTime = ZonedDateTime.parse("2019-09-13T18:30Z"),
                    lastMatchStartDateTime = ZonedDateTime.parse("2019-09-15T16:00Z"),
                    matches = null
                )
            )
        )

        every { matchDayService.getAllMatchesOfSeason(2019) } returns matchDayOverview

        given()
            .`when`()["/matchdays/2019"]
            .then()
            .statusCode(200)
            .contentType(MediaType.APPLICATION_JSON)
            .body(JsonMatcher.jsonEqualTo(expectedResponse))
    }
}
