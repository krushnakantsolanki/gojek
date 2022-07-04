package com.gopay.mock

import com.gopay.network.response.StarWarPeople
import java.util.concurrent.atomic.AtomicInteger

class PeopleFactory {

    private val counter=AtomicInteger(0)
    fun createPeopleFactory():StarWarPeople{
        val id = counter.incrementAndGet()
        val people=StarWarPeople(
          name="Luke Skywalker_$id",
          height = "172$id",
          mass = "77$id",
          hair_color = "blond$id",
          skin_color = "fair$id"
        )

        return people
    }
}