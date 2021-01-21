package de.novatec.betting.game.scoreboard

<<<<<<< HEAD
import de.novatec.betting.game.model.ScoreboardTeam
import de.novatec.betting.game.openliga.OpenLigaAccessor
import de.novatec.betting.game.openliga.model.OLScoreboardTeam
=======
import de.novatec.betting.game.matchday.matchDayOverviewTf
import de.novatec.betting.game.model.ScoreboardTeam
import de.novatec.betting.game.model.Team
import de.novatec.betting.game.model.Teams
import de.novatec.betting.game.openliga.OpenLigaAccessor
import de.novatec.betting.game.openliga.model.OLScoreboardTeam
import de.novatec.betting.game.openliga.model.OLTeam
>>>>>>> Added tests and documentation
import de.novatec.betting.game.scoreboard.tf.ScoreboardTf
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkClass
import io.mockk.verify
<<<<<<< HEAD
=======
import org.assertj.core.api.Assertions
import org.eclipse.microprofile.config.inject.ConfigProperty
>>>>>>> Added tests and documentation
import org.junit.jupiter.api.Test
import utils.classification.UnitTest

val openLigaAccessor: OpenLigaAccessor = mockk()
val scoreboardTf: ScoreboardTf = mockk()

@UnitTest
class ScoreboardServiceUnitTest {

    private val service = ScoreboardService(openLigaAccessor, scoreboardTf)

    @Test
    fun `get scoreboard for a season`() {

        val olScoreboardTeam = listOf(mockkClass(OLScoreboardTeam::class), mockkClass(OLScoreboardTeam::class))
        val scoreboardTeam = listOf(mockkClass(ScoreboardTeam::class))

        every { openLigaAccessor.getScoreboard("2019") } returns olScoreboardTeam
        every { scoreboardTf.oLScoreboardToScoreboard(olScoreboardTeam) } returns scoreboardTeam

        service.getScoreboard("2019")

        verify { scoreboardTf.oLScoreboardToScoreboard(olScoreboardTeam) }
    }

}
