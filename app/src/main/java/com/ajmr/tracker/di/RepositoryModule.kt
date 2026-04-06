package com.ajmr.tracker.di

import com.ajmr.tracker.data.repository.TransactionRepositoryImpl
import com.ajmr.tracker.domain.repository.TransactionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindExpenseRepository(
        impl: TransactionRepositoryImpl
    ): TransactionRepository
}