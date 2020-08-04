package de.novatec.betting.game.matchday


import de.novatec.betting.game.openliga.model.*
import io.mockk.every
import io.mockk.mockk
import io.quarkus.test.Mock
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.junit.jupiter.api.Test
import utils.JsonMatcher
import utils.classification.IntegrationTest
import java.util.*
import javax.enterprise.inject.Produces
import javax.ws.rs.core.MediaType

val matchDayService: MatchDayService = mockk()

@IntegrationTest
@QuarkusTest
class MatchDayRestControllerIntTest {

    @Produces
    @Mock
    fun matchDayService(): MatchDayService = matchDayService

    @Test
    fun `GET - the response contains the current match day`() {
        val expectedResponse = """
            [
                {
                    "MatchID": 55574,
                    "MatchDateTime": 1593271800000,
                    "TimeZoneID": "W. Europe Standard Time",
                    "LeagueId": 4362,
                    "LeagueName": "1. Fußball-Bundesliga 2019/2020",
                    "MatchDateTimeUTC": 1593264600000,
                    "Group": {
                        "GroupName": "34. Spieltag",
                        "GroupOrderID": 34,
                        "GroupID": 34233
                    },
                    "Team1": {
                        "TeamId": 7,
                        "TeamName": "Borussia Dortmund",
                        "ShortName": "Dortmund",
                        "TeamIconUrl": "https://upload.wikimedia.org/wikipedia/commons/thumb/6/67/Borussia_Dortmund_logo.svg/240px-Borussia_Dortmund_logo.svg.png",
                        "TeamGroupName": null
                    },
                    "Team2": {
                        "TeamId": 123,
                        "TeamName": "TSG 1899 Hoffenheim",
                        "ShortName": "Hoffenheim",
                        "TeamIconUrl": "https://upload.wikimedia.org/wikipedia/commons/6/64/TSG_Logo-Standard_4c.png",
                        "TeamGroupName": null
                    },
                    "LastUpdateDateTime": 1593278560530,
                    "MatchIsFinished": true,
                    "MatchResults": [
                        {
                            "ResultID": 91963,
                            "ResultName": "Endergebnis",
                            "PointsTeam1": 0,
                            "PointsTeam2": 4,
                            "ResultOrderID": 2,
                            "ResultTypeID": 2,
                            "ResultDescription": "Ergebnis nach Ende der offiziellen Spielzeit"
                        },
                        {
                            "ResultID": 91964,
                            "ResultName": "Halbzeit",
                            "PointsTeam1": 0,
                            "PointsTeam2": 2,
                            "ResultOrderID": 1,
                            "ResultTypeID": 1,
                            "ResultDescription": "Zwischenstand zur Halbzeit"
                        }
                    ],
                    "Goals": [
                        {
                            "Comment": null,
                            "GoalID": 84278,
                            "ScoreTeam1": 0,
                            "ScoreTeam2": 1,
                            "MatchMinute": 8,
                            "GoalGetterID": 16104,
                            "GoalGetterName": "Andrej Kramaric",
                            "IsPenalty": false,
                            "IsOwnGoal": false,
                            "IsOvertime": false
                        },
                        {
                            "Comment": null,
                            "GoalID": 84293,
                            "ScoreTeam1": 0,
                            "ScoreTeam2": 2,
                            "MatchMinute": 30,
                            "GoalGetterID": 16104,
                            "GoalGetterName": "Andrej Kramaric",
                            "IsPenalty": false,
                            "IsOwnGoal": false,
                            "IsOvertime": false
                        },
                        {
                            "Comment": null,
                            "GoalID": 84298,
                            "ScoreTeam1": 0,
                            "ScoreTeam2": 3,
                            "MatchMinute": 48,
                            "GoalGetterID": 16104,
                            "GoalGetterName": "Andrej Kramaric",
                            "IsPenalty": false,
                            "IsOwnGoal": false,
                            "IsOvertime": false
                        },
                        {
                            "Comment": null,
                            "GoalID": 84299,
                            "ScoreTeam1": 0,
                            "ScoreTeam2": 4,
                            "MatchMinute": 50,
                            "GoalGetterID": 16104,
                            "GoalGetterName": "Andrej Kramaric",
                            "IsPenalty": true,
                            "IsOwnGoal": false,
                            "IsOvertime": false
                        }
                    ],
                    "Location": null,
                    "NumberOfViewers": null
                }
            ]
        """

        val currentMatchDay = MatchDay(
            matchID = 55574,
            matchDateTime = Date(1593271800000),
            timeZoneID = "W. Europe Standard Time",
            leagueId = 4362,
            leagueName = "1. Fußball-Bundesliga 2019/2020",
            matchDateTimeUTC = Date(1593264600000),
            group = Group(
                groupName = "34. Spieltag",
                groupOrderID = 34,
                groupID = 34233
            ),
            team1 = Team(
                teamId = 7,
                shortName = "Dortmund",
                teamName = "Borussia Dortmund",
                teamIconUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/67/Borussia_Dortmund_logo.svg/240px-Borussia_Dortmund_logo.svg.png",
                teamGroupName = null
            ),
            team2 = Team(
                teamId = 123,
                shortName = "Hoffenheim",
                teamName = "TSG 1899 Hoffenheim",
                teamIconUrl = "https://upload.wikimedia.org/wikipedia/commons/6/64/TSG_Logo-Standard_4c.png",
                teamGroupName = null
            ),
            lastUpdateDateTime = Date(1593278560530),
            matchIsFinished = true,
            matchResults = listOf(
                MatchResult(
                    resultID = 91963,
                    resultName = "Endergebnis",
                    resultDescription = "Ergebnis nach Ende der offiziellen Spielzeit",
                    pointsTeam1 = 0,
                    pointsTeam2 = 4,
                    resultOrderID = 2,
                    resultTypeID = 2
                ),
                MatchResult(
                    resultID = 91964,
                    resultName = "Halbzeit",
                    resultDescription = "Zwischenstand zur Halbzeit",
                    pointsTeam1 = 0,
                    pointsTeam2 = 2,
                    resultOrderID = 1,
                    resultTypeID = 1
                )
            ),
            goals = listOf(
                Goal(
                    goalID = 84278,
                    scoreTeam1 = 0,
                    scoreTeam2 = 1,
                    matchMinute = 8,
                    goalGetterID = 16104,
                    goalGetterName = "Andrej Kramaric",
                    isPenalty = false,
                    isOwnGoal = false,
                    isOvertime = false,
                    comment = null
                ),
                Goal(
                    goalID = 84293,
                    scoreTeam1 = 0,
                    scoreTeam2 = 2,
                    matchMinute = 30,
                    goalGetterID = 16104,
                    goalGetterName = "Andrej Kramaric",
                    isPenalty = false,
                    isOwnGoal = false,
                    isOvertime = false,
                    comment = null
                ),
                Goal(
                    goalID = 84298,
                    scoreTeam1 = 0,
                    scoreTeam2 = 3,
                    matchMinute = 48,
                    goalGetterID = 16104,
                    goalGetterName = "Andrej Kramaric",
                    isPenalty = false,
                    isOwnGoal = false,
                    isOvertime = false,
                    comment = null
                ),
                Goal(
                    goalID = 84299,
                    scoreTeam1 = 0,
                    scoreTeam2 = 4,
                    matchMinute = 50,
                    goalGetterID = 16104,
                    goalGetterName = "Andrej Kramaric",
                    isPenalty = true,
                    isOwnGoal = false,
                    isOvertime = false,
                    comment = null
                )
            ),
            location = null,
            numberOfViewers = null
        )

        every { matchDayService.getCurrentMatchDay() } returns listOf(currentMatchDay)

        given()
            .`when`()["/matchday/current"]
            .then()
            .statusCode(200)
            .contentType(MediaType.APPLICATION_JSON)
            .body(JsonMatcher.jsonEqualTo(expectedResponse))
    }
}
