package uz.gita.newsappallayar.data.local.dao

import androidx.room.*
import uz.gita.newsappallayar.data.local.entity.NewsEntity


@Dao
interface NewsDao {

    @Query("SELECT * FROM NewsEntity WHERE NewsEntity.category =  :category ")
    suspend fun getNewsByCategory(category: String) : List<NewsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllNewsData(list : List<NewsEntity>)

    @Query("DELETE FROM NewsEntity WHERE category = :category")
    suspend fun deleteNewsData(category: String)

}