package de.novatec.betting.game.redis

class Increment {
    var key: String? = null
    var value = 0

    constructor(key: String?, value: Int) {
        this.key = key
        this.value = value
    }

    constructor() {}
}
