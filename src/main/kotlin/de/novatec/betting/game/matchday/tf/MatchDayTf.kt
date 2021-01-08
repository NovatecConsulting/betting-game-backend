package de.novatec.betting.game.matchday.tf

import de.novatec.betting.game.model.MatchDay
import de.novatec.betting.game.model.MatchDay.Match
import de.novatec.betting.game.model.MatchDay.Result
import de.novatec.betting.game.model.Score
import de.novatec.betting.game.model.Team
import de.novatec.betting.game.openliga.model.OLMatchDay
import java.time.ZoneId
import javax.inject.Singleton

/** Transformer class used to generate the [MatchDay] including respective [Match]es. */
@Singleton
class MatchDayTf {

    /**
     * Transforms a [List] of all [OLMatchDay]s of a season to a [MatchDay] including respective [Match]es.
     *
     * @param oLMatches [List] of [OLMatchDay]s
     *
     * @return The [MatchDay] for the given [OLMatchDay]s including respective [Match]es.
     */
    fun oLMatchesToMatchDayOverview(oLMatches: List<OLMatchDay>) =
        MatchDay(
            id = getMatchDayId(oLMatches.first()),
            name = getMatchDayName(oLMatches.first()),
            firstMatchStartDateTime = firstMatchStartDateTime(oLMatches),
            lastMatchStartDateTime = lastMatchStartDateTime(oLMatches),
            matches = oLMatches.map {
                oLMatchToMatch(it)
            }
        )

    private fun getMatchDayName(matchDay: OLMatchDay) = matchDay.group?.groupName

    private fun getMatchDayId(matchDay: OLMatchDay) = matchDay.group?.groupOrderID

    private fun firstMatchStartDateTime(olMatches: List<OLMatchDay>) =
        olMatches.sortedWith(compareBy { it.matchDateTime })
            .first().matchDateTimeUTC

    private fun lastMatchStartDateTime(olMatches: List<OLMatchDay>) =
        olMatches.sortedWith(compareBy { it.matchDateTime })
            .last().matchDateTimeUTC

    /**
     * Transforms a [OLMatchDay] Match into a [Match].
     */
    private fun oLMatchToMatch(matchDay: OLMatchDay): Match {

        val matchResult = if (matchDay.matchResults.isNotEmpty()) {
            Result(
                Score(
                    goalsHome = matchDay.matchResults[1].pointsTeam1,
                    goalsGuest = matchDay.matchResults[1].pointsTeam2
                ),
                Score(
                    goalsHome = matchDay.matchResults[0].pointsTeam1,
                    goalsGuest = matchDay.matchResults[0].pointsTeam2
                )
            )
        } else {
            null
        }

        return Match(
            id = matchDay.matchID,
            home = Team(
                id = matchDay.team1.teamId,
                name = matchDay.team1.teamName,
                shortName = matchDay.team1.shortName,
                logo = matchDay.team1.teamIconUrl
            ),
            guest = Team(
                id = matchDay.team2.teamId,
                name = matchDay.team2.teamName,
                shortName = matchDay.team2.shortName,
                logo = matchDay.team2.teamIconUrl
            ),
            kickOffDateTime = matchDay.matchDateTimeUTC,
            matchIsFinished = matchDay.matchIsFinished,
            result = matchResult
        )
    }
}
