package com.gopay.mock

import com.gopay.network.PeopleApi
import com.gopay.network.response.PeopleResponse
import com.gopay.network.response.Peoples
import java.io.IOException

class FakeApi : PeopleApi {
    var failureMsg: String? = null
    private val peoplelist = mutableListOf<Peoples>()
    fun addPeople(people: Peoples) {
        peoplelist.add(people)
    }

    override suspend fun getPeopleList(page: Int, name: String): PeopleResponse {
        failureMsg?.let {
            throw IOException(it)
        }
        val next = page + 1
        var previous: Int? = null
        if (page > 1)
            previous = page - 1;

        return PeopleResponse(
            count = 2,
            next = "https://swapi.dev/api/people/?page=$next&name=",
            previous = "https://swapi.dev/api/people/?page=$previous&name=",
            results = peoplelist
            )
    }
}