package com.example.litlens.di

import com.example.litlens.data.repository.BookRepositoryContractImpl
import com.example.litlens.domain.repository.BookRepositoryContract
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, ConfigModule::class])
@InstallIn(SingletonComponent::class)
interface MainModule

@Module
@InstallIn(SingletonComponent::class)
internal interface InternalMainModule {

    @Binds
    fun bindBookRepositoryContract(impl: BookRepositoryContractImpl): BookRepositoryContract

}