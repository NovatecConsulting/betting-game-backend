package de.novatec.betting.game.matchday

import de.novatec.betting.game.openliga.OpenLigaAccessor
import de.novatec.betting.game.openliga.model.MatchDay
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkClass
import io.mockk.verify
import org.junit.jupiter.api.Test
import utils.classification.UnitTest

val openLigaAccessor: OpenLigaAccessor = mockk()

@UnitTest
class MatchDayServiceUnitTest {

    private val cut = MatchDayService(openLigaAccessor)

    @Test
    fun `uses the openliga accessor`() {
        every { openLigaAccessor.getCurrentMatchDay() } returns listOf(mockkClass(MatchDay::class))
        cut.getCurrentMatchDay()
        verify { openLigaAccessor.getCurrentMatchDay() }
    }
}
