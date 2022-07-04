package com.gopay.ui.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.gopay.network.PeopleApi
import com.gopay.network.response.Peoples
import com.gopay.repository.PeopleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteDateImpl @Inject constructor(val peopleApi: PeopleApi): PeopleRepository {

    override fun getPeoples(search: String): Flow<PagingData<Peoples>> {
        return Pager(
            // Configure how data is loaded by passing additional properties to
            // PagingConfig, such as prefetchDistance.
            PagingConfig(pageSize = 20)
        ) {
            PeoplePagingSource(peopleApi, search)
        }.flow
    }
}