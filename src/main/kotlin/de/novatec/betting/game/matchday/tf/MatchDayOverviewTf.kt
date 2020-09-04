package de.novatec.betting.game.matchday.tf

import de.novatec.betting.game.matchday.model.MatchDay
import de.novatec.betting.game.matchday.model.MatchDayOverview
import de.novatec.betting.game.openliga.model.OLMatchDay
import javax.inject.Singleton

/** Transformer class used to generate the [MatchDayOverview]. */
@Singleton
class MatchDayOverviewTf {

    /**
     * Transforms a [List] of all [OLMatchDay]s of a season to a [MatchDayOverview].
     *
     * @param allMatchDaysOfSeason All [OLMatchDay]s of a season.
     * @param currentMatchDay The current match day, e.g. 17.
     *
     * @return The [MatchDayOverview] for the given [OLMatchDay]s.
     */
    fun matchDaysToMatchDayOverview(
        allMatchDaysOfSeason: List<OLMatchDay>,
        currentMatchDay: Long? = 0
    ): MatchDayOverview {
        // Generate map that holds all 34 match days of a season [matchDay, list of matches]
        val matchDayMap: MutableMap<Long, MutableList<OLMatchDay>> = HashMap()
        allMatchDaysOfSeason.forEach {
            if (matchDayMap.containsKey(it.group?.groupOrderID)) {
                // If an entry for a match day (e.g. 17) exists, add that match to the list
                matchDayMap[it.group?.groupOrderID]?.add(it)
            } else {
                // If the entry does not exist, create a new list containing that match
                it.group?.groupOrderID?.let { groupOrderID -> matchDayMap.put(groupOrderID, mutableListOf(it)) }
            }
        }

        // Create the list of match days and add an element for each unique match day given (should be 34 for a season).
        val overviewList = mutableListOf<MatchDay>()
        matchDayMap.forEach { (_, v) ->
            v.sortBy { it.matchDateTimeUTC } // sort by date in case it is not sorted already
            // Create the match day and fill it with the first and last match start times of the sorted list.
            overviewList.add(MatchDay(
                    id = v.first().group?.groupOrderID,
                    name = v.first().group?.groupName,
                    firstMatchStartDateTime = v.first().matchDateTimeUTC,
                    lastMatchStartDateTime = v.last().matchDateTimeUTC
                )
            )
        }

        return MatchDayOverview(
            current = currentMatchDay,
            matchDays = overviewList
        )
    }
}
