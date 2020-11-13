package de.novatec.betting.game

import io.quarkus.runtime.Quarkus
import io.quarkus.runtime.annotations.QuarkusMain
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition
import org.eclipse.microprofile.openapi.annotations.info.Info
import javax.ws.rs.core.Application


/** The Bundesliga Betting Game Backend Application. */
@OpenAPIDefinition(
    info = Info(
        title = "Betting Game Backend",
        version = "0.0.1",
        description = "Betting Game Backend for showcasing different frontend technologies"
    )
)
@Suppress("SpreadOperator")
@QuarkusMain
class BettingGameApplication : Application() {
    companion object {

        /**
         * main function is only needed to run/debug application in IntelliJ.
         */
        @JvmStatic
        fun main(args: Array<String>) {
            Quarkus.run(*args)
        }
    }
}
