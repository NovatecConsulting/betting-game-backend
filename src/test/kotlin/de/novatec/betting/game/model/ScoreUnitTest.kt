package de.novatec.betting.game.model

import de.novatec.betting.game.exceptions.NotValidException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import utils.classification.UnitTest

@UnitTest
class ScoreUnitTest {
    @Test
    fun `create score with valid amount of goals`() {
        Score(0, 99)
    }

    @Test
    fun `create score with negative amount of goals home`() {
        assertThrows<NotValidException> { Score(-1, 99) }
    }

    @Test
    fun `create score with negative amount of goals guest`() {
        assertThrows<NotValidException> { Score(99, -1) }
    }

    @Test
    fun `create score with too large amount of goals home`() {
        assertThrows<NotValidException> { Score(100, 99) }
    }

    @Test
    fun `create score with too large amount of goals guest`() {
        assertThrows<NotValidException> { Score(99, 100) }
    }
}
