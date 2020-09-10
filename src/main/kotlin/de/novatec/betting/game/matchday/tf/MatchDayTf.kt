import de.novatec.betting.game.matchday.model.*
import de.novatec.betting.game.openliga.model.OLMatchDay
import java.time.LocalDateTime
import java.time.ZoneId
import javax.inject.Singleton


@Singleton
class MatchDayTf {

    /**
     *
     */
    fun oLMatchDaysToMatchDayOverview(firstMatch: LocalDateTime, lastMatch: LocalDateTime, matchDayList: List<OLMatchDay>): MatchDay {

        val matchesList = mutableListOf<Match>()

        for (matchDay in matchDayList) {
            matchesList.add(oLMatchDayToMatchDay(matchDay))
        }

        return MatchDay(
            id = matchDayList.first().group?.groupOrderID,
            name = matchDayList.first().group?.groupName,
            firstMatchStartDateTime = firstMatch.atZone(ZoneId.of(ZoneId.systemDefault().toString())),
            lastMatchStartDateTime = lastMatch.atZone(ZoneId.of(ZoneId.systemDefault().toString())),
            matches = matchesList
        )
    }

    /**
     *
     */
    private fun oLMatchDayToMatchDay(matchDay: OLMatchDay): Match {

        val matchResult = if (matchDay.matchResults.isNotEmpty()) {
            Result(
                Final(
                    goalsHome = matchDay.matchResults[1].pointsTeam1,
                    goalsGuest = matchDay.matchResults[1].pointsTeam2
                ),
                Halftime(
                    goalsHome = matchDay.matchResults[0].pointsTeam1,
                    goalsGuest = matchDay.matchResults[0].pointsTeam2
                )
            )
        } else {
            null
        }

        return Match(
            id = matchDay.matchID,
            home = Home(
                id = matchDay.team1.teamId,
                name = matchDay.team1.teamName,
                shortName = matchDay.team1.shortName,
                logo = matchDay.team1.teamIconUrl
            ),
            guest = Guest(
                id = matchDay.team2.teamId,
                name = matchDay.team2.teamName,
                shortName = matchDay.team2.shortName,
                logo = matchDay.team2.teamIconUrl
            ),
            kickOffDateTime = matchDay.matchDateTime?.atZone(ZoneId.of(ZoneId.systemDefault().toString())),
            matchIsFinished = matchDay.matchIsFinished,
            result = matchResult)
    }
}