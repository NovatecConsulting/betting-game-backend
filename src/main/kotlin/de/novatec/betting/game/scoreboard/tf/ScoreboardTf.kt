package de.novatec.betting.game.scoreboard.tf

import de.novatec.betting.game.model.ScoreboardTeam
import de.novatec.betting.game.openliga.model.OLScoreboardTeam
import javax.inject.Singleton

/** Transformer class used to generate a list of [ScoreboardTeam]. */
@Singleton
class ScoreboardTf {

    /**
     * Transforms a [List] of all [OLScoreboardTeam]s of a season to a list of [ScoreboardTeam].
     *
     * @param [List] of [OLScoreboardTeam]s
     *
     * @return A list of sorted [ScoreboardTeam]
     */
    fun oLScoreboardToScoreboard(olScoreboardTeam: List<OLScoreboardTeam>): List<ScoreboardTeam> {
        return olScoreboardTeam.map {
            ScoreboardTeam(
                id = it.teamInfoId,
                name = it.teamName,
                shortName = it.shortName,
                logo = it.teamIconUrl,
                points = it.points,
                opponentGoals = it.opponentGoals,
                goals = it.goals,
                matches = it.matches,
                won = it.won,
                lost = it.lost,
                draw = it.draw,
                goalDiff = it.goalDiff
            )
        }.toList()
    }
}

