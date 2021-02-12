package de.novatec.betting.game.redis

import io.quarkus.redis.client.RedisClient

import io.smallrye.mutiny.Uni

import io.quarkus.redis.client.reactive.ReactiveRedisClient
import io.vertx.mutiny.redis.client.Response
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class IncrementService(
    private val redisClient: RedisClient,
    private val reactiveRedisClient: ReactiveRedisClient
) {
//    @Inject
//    var redisClient: RedisClient? = null

//    @Inject
//    var reactiveRedisClient: ReactiveRedisClient? = null

    fun del(key: String?): Uni<Void?> {
        return reactiveRedisClient.del(listOf(key))
            .map { response: Response? -> null }
    }

    operator fun get(key: String?): String {
        return redisClient.get(key).toString()
    }

    operator fun set(key: String?, value: Int) {
        println("in service set")
        redisClient.setex(key, "30", value.toString())
//        redisClient.set(listOf(key, value.toString()))
    }

    fun increment(key: String?, incrementBy: Int) {
        redisClient.incrby(key, incrementBy.toString())!!
    }

    fun keys(): Uni<List<String>> {
        return reactiveRedisClient
            .keys("*")
            .map { response: Response ->
                val result: MutableList<String> = ArrayList()
                for (r in response) {
                    result.add(r.toString())
                }
                result
            }
    }
}
