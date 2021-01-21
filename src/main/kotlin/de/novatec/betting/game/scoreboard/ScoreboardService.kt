package de.novatec.betting.game.scoreboard

import de.novatec.betting.game.model.ScoreboardTeam
import de.novatec.betting.game.openliga.OpenLigaAccessor
import de.novatec.betting.game.scoreboard.tf.ScoreboardTf
import org.eclipse.microprofile.rest.client.inject.RestClient
import javax.inject.Singleton

/** Service class that handles all the scoreboard actions that require the openliga-backend. */
@Singleton
class ScoreboardService(
    @RestClient private val openLigaAccessor: OpenLigaAccessor,
    private val scoreboardTf: ScoreboardTf
) {

    /**
     * Gets the [ScoreboardTeam] for the specified season
     *
     * @param season The name of the season, e.g. "2020"
     * @return [ScoreboardTeam] of the specified season
     */
    fun getScoreboard(season: Int): List<ScoreboardTeam> {
        val olScoreboard = openLigaAccessor.getScoreboard(season)
        return scoreboardTf.oLScoreboardToScoreboard(olScoreboard)
    }
}
