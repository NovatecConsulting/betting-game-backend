package de.novatec.betting.game.matchdaybet

import io.quarkus.runtime.StartupEvent
import io.quarkus.scheduler.Scheduled
import org.quartz.Job
import org.quartz.JobBuilder
import org.quartz.JobExecutionContext
import org.quartz.Scheduler
import org.quartz.SimpleScheduleBuilder
import org.quartz.Trigger
import org.quartz.TriggerBuilder
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.event.Observes
import kotlin.random.Random

/**
 * demo scheduler
 *
 * https://quarkus.io/guides/scheduler
 * https://quarkus.io/guides/quartz
 *
 */
@ApplicationScoped
class MatchDayResultScheduler(private val quartz: Scheduler) {

    /** demo constants */
    object DemoConstants {

        /** interval 1 */
        const val intervall1 = 45

        /** interval 2 */
        const val interval2 = 100

        /** from */
        const val r1: Long = 5

        /** to */
        const val r2: Long = 10
    }

    private val groupName = "matchDayResultGroup"
    private val simpleJobName = "getMatchDayResultSimpleJob"
    private val dynamicJobName = "getMatchDayResultDynamicJob"
    private val matchDayResultSimpleTrigger = "matchDayResultSimpleTrigger"
    private val matchDayResultDynamicTrigger = "matchDayResultDynamicTrigger"

    /** starts every 'cron.interval' - see application.yaml */
    @Scheduled(every = "{cron.interval}")
    fun checkResults() {
        val x = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneOffset.UTC).format(Instant.now())
        println("MatchDayBetScheduler: checkResults=$x")
    }


    /** starts all scheduler on startup */
    fun onStart(@Observes event: StartupEvent) {
        // simple job that is triggered every .... 45 seconds
        val simpleJob = JobBuilder.newJob(SimpleMatchDayResultJob::class.java)
            .withIdentity(simpleJobName, groupName)
            .build()
        val simpleTrigger: Trigger = TriggerBuilder.newTrigger()
            .withIdentity(matchDayResultSimpleTrigger, groupName)
            .startNow()
            .withSchedule(
                SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInSeconds(DemoConstants.intervall1)
                    .repeatForever()
            )
            .build()

        val dynamicJob = JobBuilder.newJob(DynamicMatchDayResultJob::class.java)
            .withIdentity(dynamicJobName, groupName)
            .build()
        val dynamicTrigger: Trigger = TriggerBuilder.newTrigger()
            .withIdentity(matchDayResultDynamicTrigger, groupName)
            .startNow()
            .withSchedule(
                SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInSeconds(DemoConstants.interval2)
                    .withRepeatCount(0)
            )
            .build()

        quartz.scheduleJob(simpleJob, simpleTrigger)
        quartz.scheduleJob(dynamicJob, dynamicTrigger)
    }


    /** simple Job demo class */
    class SimpleMatchDayResultJob : Job {
        override fun execute(executionContext: JobExecutionContext?) {
            println("MatchDayResultJob: execute->readResults")
        }
    }

    /** dynamic Job demo class that re-schedules its trigger */
    class DynamicMatchDayResultJob : Job {
        override fun execute(executionContext: JobExecutionContext?) {
            println("${Instant.now()} - $executionContext")

            // here comes the call to the 'readResultService' - the answer should be the a duration
            // when the next execution should take place
            val l: Long = Random.nextLong(DemoConstants.r1, DemoConstants.r2)
            val nextRun = Instant.now().plusSeconds(l)

            executionContext?.apply {
                val oldTrigger = trigger
                val newTrigger = TriggerBuilder.newTrigger()
                    .withIdentity(oldTrigger.key.name, oldTrigger.key.group)
                    .startAt(java.util.Date.from(nextRun))
                    .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInSeconds(DemoConstants.interval2)
                            .withRepeatCount(0)
                    )
                    .build()
                scheduler.rescheduleJob(oldTrigger.key, newTrigger)
            }
        }
    }
}
