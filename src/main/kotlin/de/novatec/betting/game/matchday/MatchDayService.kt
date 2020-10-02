package de.novatec.betting.game.matchday

import de.novatec.betting.game.matchday.model.MatchDay
import de.novatec.betting.game.matchday.model.MatchDayOverview
import de.novatec.betting.game.matchday.tf.MatchDayOverviewTf
import de.novatec.betting.game.matchday.tf.MatchDayTf
import de.novatec.betting.game.openliga.OpenLigaAccessor
import de.novatec.betting.game.openliga.model.OLMatchDay
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.eclipse.microprofile.rest.client.inject.RestClient
import javax.inject.Singleton

/** Service class that handles all the business actions that require the openliga-backend. */
@Singleton
class MatchDayService(
    @RestClient private val openLigaAccessor: OpenLigaAccessor,
    private val matchDayOverviewTf: MatchDayOverviewTf,
    private val matchDayTf: MatchDayTf
) {

    @ConfigProperty(name = "openliga.currentSeason")
    lateinit var currentSeason: String


    /**
     * Gets all [OLMatchDay]s of the current season. The current season is managed in the application properties.
     *
     * @return A [List] of [OLMatchDay]s containing all pairings of the current season.
     */
    fun getAllOLMatchesOfCurrentSeason(): MatchDayOverview = getAllOLMatchesOfSeason(currentSeason)

    /**
     * Gets all [OLMatchDay]s of a specific season.
     *
     * @param season The name of the season, e.g. "2019"
     *
     * @return A [List] of [OLMatchDay]s containing all pairings of the specific season.
     */
    fun getAllOLMatchesOfSeason(season: String): MatchDayOverview {
        val matchDays: List<OLMatchDay> = openLigaAccessor.getAllMatchesOfSeason(season)
        val currentMatchDays: List<OLMatchDay> = openLigaAccessor.getOLMatchesOfCurrentMatchday()

        // Check if the season name of the specified and current season is the same.
        return if (currentMatchDays.first().leagueName.equals(matchDays.first().leagueName)) {
            // If the the specified season is the same as the current season, provide the current match day (e.g. 16).
            matchDayOverviewTf.matchDaysToMatchDayOverview(matchDays, currentMatchDays.first().group?.groupOrderID)
        } else {
            // If the specified season is not the same as the current season, use default value (0L).
            matchDayOverviewTf.matchDaysToMatchDayOverview(matchDays)
        }
    }

    /**
     * Gets all MatchDays of a specific season.
     *
     * @param season The name of the season, e.g. "2019"
     * @param matchDay The name of the matchday, e.g. "7"
     *
     * @return [MatchDay]s of the specific season
     */
    fun getSpecificMatchDayOfSeason(season: String, matchDay: String): MatchDay? {
        val matchDays: List<OLMatchDay> = openLigaAccessor.getAllMatchesOfSeason(season)
        val specificMatchDays = matchDays.filter { it.group?.groupOrderID == matchDay.toLong() }
        return matchDayTf.oLMatchesToMatchDayOverview(specificMatchDays)
    }


    /**
     * Gets all [MatchDay]s of the current season.
     *
     * @return [MatchDay]s of the current season
     */
    fun getCurrentMatchDay(): MatchDay? {
        val allOLMatches = openLigaAccessor.getOLMatchesOfCurrentMatchday()
        return matchDayTf.oLMatchesToMatchDayOverview(allOLMatches)
    }
}
