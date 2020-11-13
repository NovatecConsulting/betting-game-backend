package de.novatec.betting.game

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
class BettingGameApplication : Application()
