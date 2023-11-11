package com.saiyoggie.themoviedbapp.di


import android.content.Context
import com.saiyoggie.themoviedbapp.data.remote.HomeRepository
import com.saiyoggie.themoviedbapp.data.remote.RepositoryImpl
import com.saiyoggie.themoviedbapp.data.remote.network.APIService
import com.saiyoggie.themoviedbapp.data.remote.network.RetrofitInstance
import com.saiyoggie.themoviedbapp.utils.NetworkHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Provides
    @Singleton
    fun provideRetrofitInstance(): RetrofitInstance = RetrofitInstance

    @Provides
    @Singleton
    fun provideNetworkHelper(@ApplicationContext appContext: Context): NetworkHelper =
        NetworkHelper(appContext)

    @Provides
    fun provideAPIService(retrofit: Retrofit): APIService =
        retrofit.create(APIService::class.java)


    @Singleton
    @Provides
    fun provideRepository(
        networkHelper: NetworkHelper,
        apiHelper: RetrofitInstance
    ): HomeRepository = RepositoryImpl(networkHelper, apiHelper)

}