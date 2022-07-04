package com.gopay

import androidx.paging.PagingSource
import com.gopay.mock.FakeApi
import com.gopay.mock.PeopleFactory
import com.gopay.ui.paging.PeoplePagingSource
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
@Config(sdk = intArrayOf(28))
class PeoplePagingSourceTest {

    private val peopleFactory = PeopleFactory()

    private val fakepeoplelist= listOf(
        peopleFactory.createPeopleFactory(),
        peopleFactory.createPeopleFactory(),
        peopleFactory.createPeopleFactory()
        )
    private val fakeApi= FakeApi().apply {
      fakepeoplelist.forEach{people -> addPeople(people)}
    }

    @Test
    fun peoplePagingSourceTest()=runBlockingTest {

       val pagingsource=PeoplePagingSource(fakeApi,"")
        assertEquals(
          expected = PagingSource.LoadResult.Page(
               data = listOf(fakepeoplelist[0],fakepeoplelist[1],fakepeoplelist[2]),
               prevKey =null,
               nextKey =2
          ),
          actual = pagingsource.load(
              PagingSource.LoadParams.Refresh(
                  key = null,
                  loadSize = 3,
                  placeholdersEnabled = false
              )
          )
        )
    }

}