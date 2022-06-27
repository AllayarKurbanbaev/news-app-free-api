package uz.gita.newsappallayar.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.newsappallayar.data.model.NewsData

interface AppRepository {
    fun getNewsFromNetwork(category: String): Flow<Result<List<NewsData>>>

    fun getNewsFromRoom(category: String) : Flow<Result<List<NewsData>>>
}