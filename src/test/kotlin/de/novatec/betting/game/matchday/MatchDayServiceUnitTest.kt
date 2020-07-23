package de.novatec.betting.game.matchday

import de.novatec.betting.game.openliga.OpenLigaAccessor
import de.novatec.betting.game.openliga.data.MatchDay
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkClass
import io.mockk.verify
import io.quarkus.test.junit.QuarkusTest
import org.junit.jupiter.api.Test
import utils.classification.UnitTest

val openLigaAccessor = mockk<OpenLigaAccessor>()

@UnitTest
@QuarkusTest
class MatchDayServiceUnitTest {

    val cut = MatchDayService(openLigaAccessor)

    @Test
    fun `Uses the openliga accessor`() {
        every { openLigaAccessor.getCurrentMatchDay() } returns listOf(mockkClass(MatchDay::class))
        cut.getCurrentMatchDay()
        verify { openLigaAccessor.getCurrentMatchDay() }
    }
}
