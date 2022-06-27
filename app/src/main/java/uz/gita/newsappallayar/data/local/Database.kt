package uz.gita.newsappallayar.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.gita.newsappallayar.data.local.dao.NewsDao
import uz.gita.newsappallayar.data.local.entity.NewsEntity


@Database(entities = [NewsEntity::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun getNewsDao(): NewsDao
}