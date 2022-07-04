package com.gopay.mock

import com.gopay.network.response.Peoples
import java.util.concurrent.atomic.AtomicInteger

class PeopleFactory {

    private val counter=AtomicInteger(0)
    fun createPeopleFactory(): Peoples {
        val id = counter.incrementAndGet()
        val people = Peoples(
            name = "Luke Skywalker_$id",
            height = "172$id",
            mass = "77$id",
            hair_color = "blond$id",
        )

        return people
    }
}