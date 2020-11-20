package de.novatec.betting.game.matchday.intTest;

import de.novatec.betting.game.matchday.MatchDayService;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@QuarkusTest
public class MatchDayJavaIntTest {

    @Inject
    MatchDayService service;

    @Test
    public void testGreetingService() {
        service.abc();
        Assertions.assertEquals("hello Quarkus",service.abc());
    }

}
