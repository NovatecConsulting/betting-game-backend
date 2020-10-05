package de.novatec.betting.game.teams.tf

import de.novatec.betting.game.openliga.model.OLTeam
import de.novatec.betting.game.teams.model.Team
import de.novatec.betting.game.teams.model.Teams
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
        Teams(olTeams.map { Team(it.teamId, it.teamName, it.shortName, it.teamIconUrl) }.toList())
}