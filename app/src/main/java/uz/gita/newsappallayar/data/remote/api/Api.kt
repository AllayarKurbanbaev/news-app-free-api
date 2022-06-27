package uz.gita.newsappallayar.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.gita.newsappallayar.data.remote.models.NewsResponse


interface Api {
    @GET("news")
    suspend fun getNewsByCategory(
        @Query("category") category: String
    ): Response<NewsResponse.MainResponse>
}

