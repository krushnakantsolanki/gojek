package com.gopay.dependencies

import com.gopay.BuildConfig
import com.gopay.repository.PeopleRepository
import com.gopay.ui.paging.RemoteDateImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindPeopleRepo(
        remoteDateImpl: RemoteDateImpl
    ): PeopleRepository
}

