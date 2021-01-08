package de.novatec.betting.game

import io.quarkus.arc.DefaultBean
import io.quarkus.runtime.Startup
import java.time.Clock
import java.time.ZoneId
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Produces

/**
 * Class for bean creation and management
 */
@Startup
@ApplicationScoped
class BeanConfiguration {

    /**
     * UTC Clock Bean
     */
    @Produces
    @DefaultBean
    fun utcClock(): Clock = Clock.system(ZoneId.of("UTC"))
}
