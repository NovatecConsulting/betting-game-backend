package de.novatec.betting.game.scoreboard

import de.novatec.betting.game.model.Scoreboard
import de.novatec.betting.game.openliga.OpenLigaAccessor
import de.novatec.betting.game.openliga.model.OLScoreboard
import de.novatec.betting.game.scoreboard.tf.ScoreboardTf
import de.novatec.betting.game.teams.tf.TeamsTf
import org.eclipse.microprofile.rest.client.inject.RestClient
import javax.inject.Singleton

@Singleton
class ScoreboardService(@RestClient private val openLigaAccessor: OpenLigaAccessor,
                        private val scoreboardTf: ScoreboardTf
) {

    fun getScoreboard(season: String): List<Scoreboard> {
        val olScoreboard = openLigaAccessor.getScoreboard(season)
        return scoreboardTf.oLScoreboardToScoreboard(olScoreboard);
    }

}
