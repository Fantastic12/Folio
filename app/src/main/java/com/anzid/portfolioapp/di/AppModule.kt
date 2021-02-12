package com.anzid.portfolioapp.di

import android.content.Context
import com.anzid.folio.storage.datasource.StorageDataSource
import com.anzid.folio.storage.datasource.StorageDataSourceProvider
import com.anzid.portfolioapp.ResumeRepository
import com.anzid.portfolioapp.ResumeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideStorage(@ApplicationContext context: Context) = StorageDataSourceProvider.provideEnvironmentDataSource(context)

    @Provides
    fun provideResumeRepo(storageDataSource: StorageDataSource): ResumeRepository = ResumeRepositoryImpl(storageDataSource)
}