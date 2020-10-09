package de.novatec.betting.game.teams.tf

import de.novatec.betting.game.model.Team
import de.novatec.betting.game.model.Teams
import de.novatec.betting.game.openliga.model.OLTeam
import javax.inject.Singleton

/** Transformer class that maps [OLTeam] to the internal [Team] model. */
@Singleton
class TeamsTf {

    /** Maps a [List] of [OLTeam]s to a [Teams] instance.
     *
     * @param olTeams [List] of [OLTeam]s
     * @return a [Teams] instance containing [Team]s
     */
    fun olTeamsToTeams(olTeams: List<OLTeam>): Teams =
        Teams(olTeams.map { Team(id = it.teamId, name = it.teamName, shortName = it.shortName, logo = it.teamIconUrl) }.toList())
}