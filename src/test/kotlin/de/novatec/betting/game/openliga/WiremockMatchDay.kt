package de.novatec.betting.game.openliga

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.*
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager
import java.util.*

@Suppress("MaxLineLength")
class WiremockMatchDay : QuarkusTestResourceLifecycleManager {

    private val wireMockServer = WireMockServer()

    override fun start(): MutableMap<String, String> {
        wireMockServer.start()

        stubFor(
            get(urlEqualTo("/getmatchdata/bl1")).willReturn(
                aResponse().withHeader("Content-Type", "application/json")
                    .withBody(
                        """
						[
                            {
                                "MatchID": 55574,
                                "MatchDateTimeUTC": 1593264600000,
                                "Team1": {
                                    "TeamName": "Borussia Dortmund"
                                },
                                "Team2": {
                                    "TeamName": "TSG 1899 Hoffenheim"
                                },
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
                                ]
                            }
                        ]
						"""
                    )
            )
        )

        stubFor(
            get(urlEqualTo("/getmatchdata/bl1/2019")).willReturn(
                aResponse().withHeader("Content-Type", "application/json")
                    .withBody(
                        """
                        [
                            {
                                "MatchID": 55277,
                                "MatchDateTime": "2019-08-16T20:30:00",
                                "TimeZoneID": "W. Europe Standard Time",
                                "LeagueId": 4362,
                                "LeagueName": "1. Fußball-Bundesliga 2019/2020",
                                "MatchDateTimeUTC": "2019-08-16T18:30:00Z",
                                "Group": {
                                    "GroupName": "1. Spieltag",
                                    "GroupOrderID": 1,
                                    "GroupID": 34200
                                },
                                "Team1": {
                                    "TeamId": 40,
                                    "TeamName": "FC Bayern",
                                    "ShortName": "FC Bayern",
                                    "TeamIconUrl": "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1f/Logo_FC_Bayern_M%C3%BCnchen_%282002%E2%80%932017%29.svg/240px-Logo_FC_Bayern_M%C3%BCnchen_%282002%E2%80%932017%29.svg.png",
                                    "TeamGroupName": null
                                },
                                "Team2": {
                                    "TeamId": 54,
                                    "TeamName": "Hertha BSC",
                                    "ShortName": "Hertha BSC",
                                    "TeamIconUrl": "https://upload.wikimedia.org/wikipedia/commons/thumb/8/81/Hertha_BSC_Logo_2012.svg/2858px-Hertha_BSC_Logo_2012.svg.png",
                                    "TeamGroupName": null
                                },
                                "LastUpdateDateTime": "2020-08-09T22:27:13.397",
                                "MatchIsFinished": true,
                                "MatchResults": [
                                    {
                                        "ResultID": 89318,
                                        "ResultName": "Endergebnis",
                                        "PointsTeam1": 2,
                                        "PointsTeam2": 2,
                                        "ResultOrderID": 2,
                                        "ResultTypeID": 2,
                                        "ResultDescription": "Ergebnis nach Ende der offiziellen Spielzeit"
                                    },
                                    {
                                        "ResultID": 89319,
                                        "ResultName": "Halbzeit",
                                        "PointsTeam1": 1,
                                        "PointsTeam2": 2,
                                        "ResultOrderID": 1,
                                        "ResultTypeID": 1,
                                        "ResultDescription": "Zwischenstand zur Halbzeit"
                                    }
                                ],
                                "Goals": [
                                    {
                                        "GoalID": 80231,
                                        "ScoreTeam1": 1,
                                        "ScoreTeam2": 0,
                                        "MatchMinute": 24,
                                        "GoalGetterID": 14563,
                                        "GoalGetterName": "Robert Lewandowski",
                                        "IsPenalty": false,
                                        "IsOwnGoal": false,
                                        "IsOvertime": false,
                                        "Comment": null
                                    },
                                    {
                                        "GoalID": 80232,
                                        "ScoreTeam1": 1,
                                        "ScoreTeam2": 1,
                                        "MatchMinute": 36,
                                        "GoalGetterID": 16913,
                                        "GoalGetterName": "Lukebakio",
                                        "IsPenalty": false,
                                        "IsOwnGoal": false,
                                        "IsOvertime": false,
                                        "Comment": null
                                    },
                                    {
                                        "GoalID": 80233,
                                        "ScoreTeam1": 1,
                                        "ScoreTeam2": 2,
                                        "MatchMinute": 39,
                                        "GoalGetterID": 17002,
                                        "GoalGetterName": "Grujic, M.",
                                        "IsPenalty": false,
                                        "IsOwnGoal": false,
                                        "IsOvertime": false,
                                        "Comment": null
                                    },
                                    {
                                        "GoalID": 80234,
                                        "ScoreTeam1": 2,
                                        "ScoreTeam2": 2,
                                        "MatchMinute": 60,
                                        "GoalGetterID": 14563,
                                        "GoalGetterName": "Robert Lewandowski",
                                        "IsPenalty": true,
                                        "IsOwnGoal": false,
                                        "IsOvertime": false,
                                        "Comment": null
                                    }
                                ],
                                "Location": {
                                    "LocationID": 491,
                                    "LocationCity": "München",
                                    "LocationStadium": "Alianz Arena"
                                },
                                "NumberOfViewers": null
                                }
                            ]
						"""
                    )
            )
        )

        stubFor(
            get(urlEqualTo("/getAvailableTeams/bl1/2020")).willReturn(
                aResponse().withHeader("Content-Type", "application/json")
                    .withBody(
                        """
                            [
                                {
                                    "TeamId": 65,
                                    "TeamName": "1. FC Köln",
                                    "ShortName": "FC Köln",
                                    "TeamIconUrl": "https://upload.wikimedia.org/wikipedia/en/thumb/5/53/FC_Cologne_logo.svg/901px-FC_Cologne_logo.svg.png",
                                    "TeamGroupName": null
                                },                        
                                {
                                    "TeamId": 16,
                                    "TeamName": "VfB Stuttgart",
                                    "ShortName": "Stuttgart",
                                    "TeamIconUrl": "https://upload.wikimedia.org/wikipedia/commons/thumb/e/eb/VfB_Stuttgart_1893_Logo.svg/921px-VfB_Stuttgart_1893_Logo.svg.png",
                                    "TeamGroupName": null
                                }
                                    ]
						"""
                    )
            )
        )

        return Collections.singletonMap("openliga.restclient.OpenLigaAccessor/mp-rest/url", wireMockServer.baseUrl())
    }

    override fun stop() {
        wireMockServer.stop()
    }
}
