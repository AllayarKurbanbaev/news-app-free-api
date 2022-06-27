package uz.gita.newsappallayar.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.gita.newsappallayar.data.local.Database
import uz.gita.newsappallayar.data.local.dao.NewsDao
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @[Provides Singleton]
    fun getDatabase(@ApplicationContext context : Context) : Database
            = Room.databaseBuilder(context, Database::class.java, "NewsDatabase").build()

    @[Provides Singleton]
    fun getNewsDao(database : Database) : NewsDao = database.getNewsDao()
}