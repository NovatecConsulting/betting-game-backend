package de.novatec.betting.game.betresult

import io.quarkus.scheduler.Scheduled

class BetResultScheduler(
    private val betResultService: BetResultService
) {

    /** starts every 'cron.interval' - see application.yaml */
    @Scheduled(every = "{cron.interval}")
    fun initiateBetResultCalculation() {
        betResultService.determineBetResult()
    }
}
