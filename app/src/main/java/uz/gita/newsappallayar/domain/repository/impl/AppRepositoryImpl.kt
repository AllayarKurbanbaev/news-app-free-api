package uz.gita.newsappallayar.domain.repository.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.newsappallayar.data.local.dao.NewsDao
import uz.gita.newsappallayar.data.local.entity.toNewsData
import uz.gita.newsappallayar.data.model.NewsData
import uz.gita.newsappallayar.data.remote.api.Api
import uz.gita.newsappallayar.data.remote.models.toNewsData
import uz.gita.newsappallayar.data.remote.models.toNewsEntity
import uz.gita.newsappallayar.domain.repository.AppRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppRepositoryImpl @Inject constructor(
    private val api: Api,
    private val database: NewsDao
) : AppRepository {
    override fun getNewsFromNetwork(category: String): Flow<Result<List<NewsData>>> =
        flow<Result<List<NewsData>>> {
            val response = api.getNewsByCategory(category)
            if (response.isSuccessful) {
                response.body()?.let {
                    database.deleteNewsData(category)
                    database.insertAllNewsData(it.articles.map { data -> data.toNewsEntity(category) })
                    emit(Result.success(it.articles.map { data -> data.toNewsData() }))
                }
            } else emit(Result.failure(Exception("Error")))
        }.catch {
            emit(Result.failure(Exception(it)))
        }.flowOn(Dispatchers.IO)

    override fun getNewsFromRoom(category: String): Flow<Result<List<NewsData>>> =
        flow<Result<List<NewsData>>> {
            val data = database.getNewsByCategory(category)
            emit(Result.success(data.map { model -> model.toNewsData() }))
        }.catch {
            emit(Result.failure(Exception(it)))
        }.flowOn(Dispatchers.IO)
}