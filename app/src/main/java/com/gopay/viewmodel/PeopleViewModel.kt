package com.gopay.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gopay.base.BaseViewModel
import com.gopay.network.response.Peoples
import com.gopay.repository.PeopleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(val peopleRepository: PeopleRepository) : BaseViewModel() {


     lateinit var _peopleflow: Flow<PagingData<Peoples>>
     val peopleflow:Flow<PagingData<Peoples>>
         get() = _peopleflow

    fun getPeoples(search:String){
         viewModelScope.launch {
             _peopleflow=peopleRepository.getPeoples(search).cachedIn(viewModelScope)

         }
     }




}