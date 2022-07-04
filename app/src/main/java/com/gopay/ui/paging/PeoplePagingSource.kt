package com.gopay.ui.paging

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gopay.network.PeopleApi
import com.gopay.network.response.Peoples

class PeoplePagingSource constructor(val peopleApi: PeopleApi,val search:String): PagingSource<Int, Peoples>()  {

    override fun getRefreshKey(state: PagingState<Int, Peoples>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Peoples> {
        try {
            // Start refresh at page 1 if undefined.
            val nextPageNumber = params.key ?: 1
            val response = peopleApi.getPeopleList(nextPageNumber, search)

            val peoples=response.results
            var nextpage:Int?=null
            if(response.next!=null){
                val uri=Uri.parse(response.next)
                val page=uri.getQueryParameter("page")
                nextpage=page.toInt()
            }


            return LoadResult.Page(
                data = peoples,
                prevKey = null, // Only paging forward.
                nextKey = nextpage
            )
        } catch (e: Exception) {
            // Handle errors in this block and return LoadResult.Error if it is an
            // expected error (such as a network failure).
            return  LoadResult.Error(e)
        }
    }
}