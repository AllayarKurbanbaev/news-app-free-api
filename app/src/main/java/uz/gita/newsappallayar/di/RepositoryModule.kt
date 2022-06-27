package uz.gita.newsappallayar.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.newsappallayar.domain.repository.AppRepository
import uz.gita.newsappallayar.domain.repository.impl.AppRepositoryImpl
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @[Binds Singleton]
    fun getNewsRepository(impl : AppRepositoryImpl) :AppRepository
}