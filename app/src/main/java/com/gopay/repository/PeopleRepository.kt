package com.gopay.repository

import androidx.paging.PagingData
import com.gopay.network.response.Peoples
import kotlinx.coroutines.flow.Flow

interface PeopleRepository {

    fun getPeoples(search:String):Flow<PagingData<Peoples>>
}