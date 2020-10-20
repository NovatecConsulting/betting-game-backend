package de.novatec.betting.game.scoreboard.tf

import de.novatec.betting.game.model.Scoreboard
import de.novatec.betting.game.model.Team
import de.novatec.betting.game.openliga.model.OLScoreboard
import javax.inject.Singleton

@Singleton
class ScoreboardTf {

    fun oLScoreboardToScoreboard(olScoreboard: List<OLScoreboard>): List<Scoreboard> {
        return olScoreboard.map {
            Scoreboard(
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