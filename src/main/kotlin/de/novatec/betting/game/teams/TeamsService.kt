package de.novatec.betting.game.teams

import de.novatec.betting.game.model.Team
import de.novatec.betting.game.model.Teams
import de.novatec.betting.game.openliga.OpenLigaAccessor
import de.novatec.betting.game.teams.tf.TeamsTf
import org.eclipse.microprofile.rest.client.inject.RestClient
import javax.inject.Singleton

/** Service class that handles all the teams actions that require the openliga-backend. */
@Singleton
class TeamsService(@RestClient private val openLigaAccessor: OpenLigaAccessor, private val teamsTf: TeamsTf) {

    /**
     * Gets all [Team]s for the specified season
     *
     * @param season The name of the season, e.g. "2020"
     * @return All [Teams] of the specified season
     */
    fun getTeams(season: String): Teams {
        val olTeams = openLigaAccessor.getAllTeams(season)
        return teamsTf.olTeamsToTeams(olTeams)
    }
}