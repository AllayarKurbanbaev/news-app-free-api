//package uz.gita.newsappallayar.domain.repository.impl
//
//import android.util.Log
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.flow
//import kotlinx.coroutines.flow.flowOn
//import uz.gita.newsappallayar.data.local.dao.NewsDao
//import uz.gita.newsappallayar.data.remote.api.Api
//import uz.gita.newsappallayar.data.remote.models.NewsResponse
//import uz.gita.newsappallayar.domain.repository.NewsRepository
//import javax.inject.Inject
//import javax.inject.Singleton
//
//
//@Singleton
//class NewsRepositoryImpl @Inject constructor(
//    private val api: Api,
//    private val database : NewsDao
//) : NewsRepository {
//    override fun getNewsByCategory(category: String): Flow<Result<List<NewsResponse.ArticlesData>>> =
//        flow<Result<List<NewsResponse.ArticlesData>>> {
//            try {
//                val response = api.getNewsByCategory(category)
//                if (response.isSuccessful) {
//                    response.body()?.let { res ->
//                        emit(Result.success(res.articles))
//                    }
//                } else {
//                    emit(Result.failure(Exception("Connection error")))
//                }
//            } catch (e: Exception) {
//                Log.d("TTT", e.message.toString())
//                emit(Result.failure(e))
//            }
//        }.flowOn(Dispatchers.Default)
//}