package de.novatec.betting.game.openliga

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.*
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager
import java.util.*


class WiremockMatchDay: QuarkusTestResourceLifecycleManager {

    private val wireMockServer = WireMockServer()

    override fun start(): MutableMap<String, String> {
        wireMockServer.start()

        stubFor(get(urlEqualTo("/getmatchdata/bl1"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
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
                )))

        return Collections.singletonMap("openliga.restclient.OpenLigaAccessor/mp-rest/url", wireMockServer.baseUrl())
    }

    override fun stop() {
        wireMockServer.stop()
    }
}
